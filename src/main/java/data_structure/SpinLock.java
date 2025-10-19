package data_structure;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class SpinLock {
    // 这里 volatile 不能去掉, 不然即使 cas 会刷新 store buffer, 另一个 core
    // 也会从自己的 store buffer 读取 而不是 memory
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
    }
}
