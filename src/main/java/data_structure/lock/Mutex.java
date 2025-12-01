package data_structure.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

public class Mutex {
    private volatile long owner = 0;
    private volatile int flag = 0;
    private final Queue<WaitNode> waitQueue = new ConcurrentLinkedQueue<>();

    private static final Unsafe U;
    private static final long FLAG_OFFSET;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
            FLAG_OFFSET = U.objectFieldOffset(Mutex.class.getDeclaredField("flag"));
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    public void lock() {
        while (!U.compareAndSwapInt(this, FLAG_OFFSET, 0, 1)) ;
        if (owner == 0) {
            owner = Thread.currentThread().getId();
            flag = 0;
        }
        else {
            Thread thread = Thread.currentThread();
            WaitNode waitNode = new WaitNode(thread);
            waitQueue.add(waitNode);
            flag = 0;
            while (!waitNode.shouldWakeUp) {
                LockSupport.park(this);
            }
        }
    }

    public void unlock() {
        while (!U.compareAndSwapInt(this, FLAG_OFFSET, 0, 1)) ;
        if (waitQueue.isEmpty()) {
            owner = 0;
        }
        else {
            WaitNode waitNode = waitQueue.remove();
            waitNode.shouldWakeUp = true;
            LockSupport.unpark(waitNode.thread);
        }
        flag = 0;
    }

    static class WaitNode {
        final Thread thread;
        volatile boolean shouldWakeUp = false;

        public WaitNode(Thread thread) {
            this.thread = thread;
        }
    }
}
