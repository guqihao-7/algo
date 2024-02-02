package leetcode.list;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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
        Node node = null;
        if (tryAcquire()) return true;
        // 抢锁失败，thread 封装成 node 进队
        node = new Node();
        node.waiter = Thread.currentThread();
        while (true) {
            Node t = tail;
            node.prev = t;
            if (t.casNext(null, t)) {
                t.next = node;
                break;
            }
        }

        // try again, 在队中成功一个是自己是 head 的下一个，另一个是 tryAcquire成功
        while (true) {
            final Node p = Objects.requireNonNull(node).prev;
            if (p == head && tryAcquire()) {    // try again
                head = node;
                node.waiter = null;
                head.prev = null;
                p.next = null;
                return true;
            }
            else {
                LockSupport.park(this); // retry 依然失败就要 park，等待唤醒
            }
        }
    }

    public boolean release() {
        return tryRelease();
    }

    public boolean tryAcquire() {
        final Thread current = Thread.currentThread();
        final AtomicInteger c = getState();
        if (c.get() == 0) {
            if (c.compareAndSet(0, 1)) {
                setExclusiveOwner(current);
                return true;
            }
        }
        else if (current == exclusiveOwnerThread) {
            c.incrementAndGet();
            return true;
        }
        return false;
    }

    public boolean tryRelease() {
        if (exclusiveOwnerThread != Thread.currentThread()) {
            return false;
        }

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
        tail = new Node();
        head.next = tail;
        tail.prev = head;

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

    public static int a = 1;

    public static void main(String[] args) {
        SimpleLockQueue lock = new SimpleLockQueue();
        Lock rLock = new ReentrantLock();
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    lock.acquire();
                    try {
                        System.out.println(a);
                        a++;
                    } finally {
                        lock.release();
                    }
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}
