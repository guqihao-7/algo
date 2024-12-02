package leetcode.list;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class SimpleLockQueue {
    private static Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            UNSAFE = null;
        }
    }

    public boolean acquire() {
        // 先尝试获取锁
        if (tryAcquire()) return true;

        // 抢锁失败，thread 封装成 node 进队
        Node node = new Node();
        node.waiter = Thread.currentThread();

        while (true) {
            Node t = tail;
            node.prev = t;
            // t 先将引用指向自己，只是为了调用一下 CAS
            if (t.casNext(null, t)) {
                // CAS 成功，其他的线程 CAS 时 expected 都是 null，只会失败
                t.next = node;
                tail = node;
                break;
            }
        }

        // 此时入队成功，但并不放弃，查看自己是不是 head 的下一个，是的话仍尝试抢
        // try again, 在队中成功一个是自己是 head 的下一个，另一个是 tryAcquire成功
        while (true) {
            final Node p = Objects.requireNonNull(node).prev;
            if (p == head && tryAcquire()) {    // try again
                // 是 head 的下一个表示自己就该执行了，然后将自己置为 head，等到自己执行完，唤醒自己的后继
                head = node;
                node.waiter = null;
                node.prev = null;
                p.next = null;
                return true;
            }
            else {
                LockSupport.park(this); // retry 依然失败就要 park，等待唤醒，唤醒之后就一直在判断自己是不是 head 的下一个
            }
        }
    }

    public boolean release() {
        return tryRelease();
    }

    public boolean tryAcquire() {
        final Thread current = Thread.currentThread();
        final AtomicInteger c = getState();

        // 当前没有持有者
        if (c.get() == 0) {
            if (c.compareAndSet(0, 1)) {
                setExclusiveOwner(current);
                return true;
            }
        }

        // 持有者就是自己，重入
        else if (current == exclusiveOwnerThread) {
            c.incrementAndGet();
            return true;
        }

        // 当前有持有者且不是自己，就返回 false
        return false;
    }

    public boolean tryRelease() {
        // 不是本人不能释放
        if (exclusiveOwnerThread != Thread.currentThread()) {
            return false;
        }

        // todo 这里没有考虑重入？
        // 可以释放了，去找下一个被唤醒的，这里是从后往前找的，主要是因为 双链+cas 的问题
        Node s = head.next;
        if (head != null) {
            s = null;
            if (head.next == null) return false;
            for (Node t = tail; t != null && t != head; t = t.prev) {
                s = t;
            }
        }

        if (s != null) {
            LockSupport.unpark(s.waiter);
        }
        return true;
    }

    volatile Node head;
    volatile Node tail;
    AtomicInteger state;
    private Thread exclusiveOwnerThread;

    private AtomicInteger getState() {
        return state;
    }

    private void setExclusiveOwner(Thread t) {
        exclusiveOwnerThread = t;
    }

    public SimpleLockQueue() {
        head = new Node();
        tail = head;
        state = new AtomicInteger(0);
    }

    static class Node {
        volatile Node prev;       // initially attached via casTail
        volatile Node next;       // visibly nonnull when signallable
        Thread waiter;            // visibly nonnull when enqueued

        // methods for atomic operations
        final boolean casPrev(Node c, Node v) {  // for cleanQueue
            return UNSAFE.compareAndSwapObject(this, PREV, c, v);
        }

        final boolean casNext(Node c, Node v) {  // for cleanQueue
            return UNSAFE.compareAndSwapObject(this, NEXT, c, v);
        }

        private static final long NEXT;
        private static final long PREV;

        static {
            try {
                NEXT = UNSAFE.objectFieldOffset(Node.class.getDeclaredField("next"));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }


        static {
            try {
                PREV = UNSAFE.objectFieldOffset(Node.class.getDeclaredField("prev"));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static volatile int a = 100;

    public static void main(String[] args) {
        SimpleLockQueue lock = new SimpleLockQueue();
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (a > 0) {
                    lock.acquire();
                    try {
                        System.out.println(a);
                        a--;
                    } finally {
                        lock.release();
                    }
                }
                System.out.println();
            });
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}
