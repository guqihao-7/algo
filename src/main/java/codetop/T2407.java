package codetop;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长上升子序列 II
 */
public class T2407 {
    public int lengthOfLIS(int[] nums, int k) {
        List<Integer> g = new ArrayList<>();
        for (int num : nums) {
            int i = lower_bound(g, num);
            if (i == g.size()) {
                if (g.size() == 0) {
                    g.add(num);
                } else {
                    if (g.get(i - 1) + k >= num)
                        g.add(num);
                }
            } else {
                if (hasPre(i) && hasSucc(i, g)) {
                    if (g.get(i - 1) + k >= num && num + k >= g.get(i + 1))
                        g.set(i, num);
                } else if (hasPre(i)) {
                    if (g.get(i - 1) + k >= num)
                        g.set(i, num);
                } else if (hasSucc(i, g)) {
                    if (num + k >= g.get(i + 1))
                        g.set(i, num);
                } else {
                    g.set(i, num);
                }
            }
        }
        return g.size();
    }

    boolean hasSucc(int i, List<Integer> g) {
        return i <= (g.size() - 2);
    }

    boolean hasPre(int i) {
        return i >= 1;
    }

    // 找到第一个大于等于 x 的数的下标, 都小于 x 则返回 list 长度
    int lower_bound(List<Integer> list, int x) {
        int n = list.size(), l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (list.get(m) < x) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    public static void main(String[] args) {
        T2407 solution = new T2407();
        int[] nums = {7,4,5,1,8,12,4,7};
        int k = 5;
        System.out.println(solution.lengthOfLIS(nums, k));
    }
}
