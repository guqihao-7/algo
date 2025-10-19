package data_structure;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class SpinLock {
    /*
        需不需要加 volatile 是根据 unlock 怎么做决定的
        如果 unlock 用的 cas, 则这里不需要 volatile
        如果 unlock 用的不是 cas, 这里需要 volatile
     */
    private volatile int flag;

    private static final Unsafe U;

    private static final long VALUE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
            VALUE = U.objectFieldOffset(SpinLock.class.getDeclaredField("flag"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void lock() {
        while (!U.compareAndSwapInt(this, VALUE, 0, 1));
    }

    public void lockHeavier() {
        while (!U.compareAndSwapInt(this, VALUE, 0, 1))
            Thread.yield();
    }

    public void unlock() {
        // unlock 不需要 cas 保证原子性, 没有 race condition
        flag = 0;
        // U.compareAndSwapInt(this, VALUE, 1, 0);
    }
}
