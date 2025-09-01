package leetcode.weekly.w465;

import java.util.HashSet;
import java.util.Set;

public class T1 {
    public static void main(String[] args) {
        T1 solution = new T1();
    }

    public int[] recoverOrder(int[] order, int[] friends) {
        Set<Integer> set = new HashSet<>();
        for (int friend : friends) {
            set.add(friend);
        }

        int[] res = new int[friends.length];
        int idx = 0;

        for (int i : order) {
            if (set.contains(i)) {
                res[idx++] = i;
            }
        }
        return res;
    }
}
