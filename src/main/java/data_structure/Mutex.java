package data_structure;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

public class Mutex {
    private volatile int flag;
    private volatile int guard;
    private final Queue<Thread> waitQueue;

    private static final Unsafe U;

    private static final long VALUE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
            VALUE = U.objectFieldOffset(Mutex.class.getDeclaredField("guard"));
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    public Mutex() {
        waitQueue = new ConcurrentLinkedQueue<>();
    }

    public void lock() {
        while (!U.compareAndSwapInt(this, VALUE, 0, 1)) ;
        if (flag == 0) {
            flag = 1;
            guard = 0;
        }
        else {
            waitQueue.add(Thread.currentThread());
            guard = 0;
            LockSupport.park();
        }
    }

    public void unlock() {
        while (!U.compareAndSwapInt(this, VALUE, 0, 1)) ;
        if (waitQueue.isEmpty()) {
            flag = 0;
        }
        else {
            Thread head = waitQueue.remove();
            LockSupport.unpark(head);
        }
        guard = 0;
    }
}
