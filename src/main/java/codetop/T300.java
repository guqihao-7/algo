package codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最长上升子序列
 */
public class T300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length, dp[] = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++)
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
        }
        int max = dp[0];
        for (int i = 0; i < n; i++)
            max = Math.max(max, dp[i]);
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for (int num : nums) {
            int i = lower_bound(g, num);
            if (i == g.size())
                g.add(num);
            else
                g.set(i, num);
        }
        return g.size();
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
}
