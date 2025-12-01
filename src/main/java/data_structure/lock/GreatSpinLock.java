package data_structure.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Spin lock 的增强版本
 */
public class GreatSpinLock {
    private volatile int flag;

    private static final Unsafe U;
    private static final long VALUE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
            VALUE = U.objectFieldOffset(GreatSpinLock.class.getDeclaredField("flag"));
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    public void lock() {
        while (true) {
            while (flag == 1) {}
            if (U.compareAndSwapInt(this, VALUE, 0, 1))
                return;
        }
    }

    public void unlock() {
        flag = 0;
    }
}
