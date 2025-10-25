package codecrush;

import data_structure.Mutex;

import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {
    private static volatile int a = 0;
    private static final AtomicInteger activeThreads = new AtomicInteger(0);
    private static volatile boolean errorDetected = false;
    private static final Mutex lock = new Mutex();

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
                activeThreads.decrementAndGet();
                lock.unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("a = " + a);
        if (errorDetected) {
            System.out.println("测试失败：检测到互斥锁失效。");
        }
        else {
            System.out.println("测试在本次运行中未检测到错误（但这不代表代码是正确的）。");
        }
    }
}