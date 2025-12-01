package data_structure.lock;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MCSLock {
    private final AtomicReference<WaitNode> tail = new AtomicReference<>(null);
    private final ThreadLocal<WaitNode> self = ThreadLocal.withInitial(WaitNode::new);

    public void lock() {
        WaitNode waitNode = self.get();
        WaitNode oldTail = tail.getAndSet(waitNode);
        if (oldTail != null) {
            waitNode.locked = true;
            oldTail.next = waitNode;
            while (waitNode.locked) {
            }
            ;
        }
    }

    public void unlock() {
        WaitNode waitNode = self.get();
        if (waitNode.next == null) {
            if (tail.compareAndSet(waitNode, null))
                return;
            while (waitNode.next == null) {
            }
        }
        waitNode.next.locked = false;
        waitNode.next = null;
    }

    static class WaitNode {
        volatile boolean locked = false;
        volatile WaitNode next = null;
    }


    private static int a = 0;
    private static final AtomicInteger activeThreads = new AtomicInteger(0);
    private static boolean errorDetected = false;
    private static final MCSLock lock = new MCSLock();


    // private static Semaphore s1 = new Semaphore(1);
    // private static Semaphore s2 = new Semaphore(0);
    // private static Semaphore s3 = new Semaphore(0);

    private static Lock l = new ReentrantLock();
    private static Condition c1 = l.newCondition();
    private static Condition c2 = l.newCondition();
    private static Condition c3 = l.newCondition();

    public static void main(String[] args) throws InterruptedException {
        int[] nums = {1};
        Thread t1 = new Thread(() -> {
            while (true) {
                l.lock();
                try {
                    c1.wait();
                    Thread.sleep(500);
                }
                catch (InterruptedException ignored) {
                }
                System.out.println("t1: " + nums[0]++);
                // s2.release();
                c2.notify();
                l.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                l.lock();
                try {
                    // s2.acquire();
                    c2.wait();
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t2: " + nums[0]++);
                // s3.release();
                c3.notify();
                l.unlock();
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            while (true) {
                l.lock();
                try {
                    // s3.acquire();
                    c3.wait();
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t3: " + nums[0]++);
                // s1.release();
                c1.notify();
                l.unlock();
            }
        }, "t3");


        t1.start();
        t2.start();
        t3.start();

        c1.notify();

        // Lock lock = new ReentrantLock();
        // lock.lock();;
        // lock.unlock();
        // Runnable task = () -> {
        //     for (int i = 0; i < 10000000; i++) {
        //         lock.lock();
        //         int count = activeThreads.incrementAndGet();
        //
        //         // 核心验证：如果计数器大于1，说明锁失效了！
        //         if (count > 1) {
        //             // 打印错误信息，我们抓到“凶手”了！
        //             System.out.println("致命错误：有 " + count + " 个线程同时进入了临界区！");
        //             errorDetected = true;
        //         }
        //         a++;
        //
        //         if (a % 100000 == 0) System.out.println("a = " + a);
        //
        //         activeThreads.decrementAndGet();
        //         lock.unlock();
        //     }
        // };
        //
        // Thread t1 = new Thread(task);
        // Thread t2 = new Thread(task);
        // Thread t3 = new Thread(task);
        // t1.start();
        // t2.start();
        // t3.start();
        // t1.join();
        // t2.join();
        // t3.join();
        //
        // System.out.println("a = " + a);
        // if (errorDetected) {
        //     System.out.println("测试失败：检测到互斥锁失效。");
        // }
        // else {
        //     System.out.println("测试在本次运行中未检测到错误（但这不代表代码是正确的）。");
        // }
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.get(0);

    }
}
