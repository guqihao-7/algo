package leetcode.list.T50_99;

import java.util.Arrays;

public class T53 {
    public static void main(String[] args) {
        T53 solution = new T53();
        System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length, dp[] = new int[len], max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) dp[i] = dp[i - 1] + nums[i];
            else dp[i] = nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
