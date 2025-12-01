package data_structure.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CLHLock {
    private final AtomicReference<WaitNode> tail = new AtomicReference<>(new WaitNode());
    private final ThreadLocal<WaitNode> self = ThreadLocal.withInitial(WaitNode::new);
    private final ThreadLocal<WaitNode> prev = new ThreadLocal<>();

    public void lock() {
        WaitNode waitNode = self.get();
        waitNode.waiting = true;
        WaitNode oldTail = tail.getAndSet(waitNode);
        prev.set(oldTail);
        while (oldTail.waiting) {};
    }

    public void unlock() {
        self.get().waiting = false;
        self.set(prev.get());
    }

    static class WaitNode {
        volatile boolean waiting = false;
    }

    private static int a = 0;
    private static final AtomicInteger activeThreads = new AtomicInteger(0);
    private static boolean errorDetected = false;
    private static final CLHLock lock = new CLHLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000000; i++) {
                lock.lock();
                int count = activeThreads.incrementAndGet();

                // 核心验证：如果计数器大于1，说明锁失效了！
                if (count > 1) {
                    // 打印错误信息，我们抓到“凶手”了！
                    System.out.println("致命错误：有 " + count + " 个线程同时进入了临界区！");
                    errorDetected = true;
                }
                a++;

                if (a % 100000 == 0) System.out.println("a = " + a);

                activeThreads.decrementAndGet();
                lock.unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println("a = " + a);
        if (errorDetected) {
            System.out.println("测试失败：检测到互斥锁失效。");
        }
        else {
            System.out.println("测试在本次运行中未检测到错误（但这不代表代码是正确的）。");
        }
    }
}
