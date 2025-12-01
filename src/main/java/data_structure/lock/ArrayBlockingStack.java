package data_structure.lock;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unchecked")
public class ArrayBlockingStack<E> {
    private int count;
    private final Object[] items;
    private final int capacity;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;


    public ArrayBlockingStack(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void push(E e) throws InterruptedException {
        lock.lock();
        try {
            while (count == capacity)
                notFull.wait();
            items[++count] = e;
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public E pop() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.wait();
            E e = (E) items[count--];
            notFull.signal();
            return e;
        }
        finally {
            lock.unlock();
        }
    }

    public E peek() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.wait();
            E e = (E) items[count];
            notFull.signal();
            return e;
        }
        finally {
            lock.unlock();
        }
    }

    // public static void main(String[] args) {
    //     ThreadPoolExecutor executor = new ThreadPoolExecutor(
    //             1, 30, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), new ThreadFactory() {
    //
    //                 AtomicInteger cnt = new AtomicInteger(0);
    //
    //                 @Override
    //         public Thread newThread(Runnable r) {
    //             // throw new RuntimeException("hello");
    //             // return null;
    //
    //                     int i = cnt.incrementAndGet();
    //                     System.out.println("cnt = " + cnt);
    //                     if (i > 3) {
    //                         throw new RuntimeException("2g3812e1");
    //                     }
    //
    //                     Thread thread = new Thread(r);
    //                 thread.setName("test");
    //                 return thread;
    //         }
    //     }
    //     );
    //
    //     for (int i = 0; i < 1000; i++) {
    //         try {
    //             executor.execute(() -> {
    //                 // throw new RuntimeException("hello");
    //                 System.out.println("hello, from thread pool executor~");
    //             });
    //         }
    //         catch (Exception e) {
    //             System.out.println("=======================");
    //             e.printStackTrace();
    //         }
    //     }
    //
    //
    //     // executor.shutdown();
    //     try {
    //         Thread.sleep(1000 * 1000);
    //     }
    //     catch (InterruptedException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.allowCoreThreadTimeOut(true);
        executor.execute(() -> {
            try {
                Thread.sleep(5 * 1000L);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
