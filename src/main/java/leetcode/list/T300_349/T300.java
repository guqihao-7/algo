package leetcode.list.T300_349;

import java.util.Arrays;

public class T300 {
    public int lengthOfLIS(int[] nums) {
        var len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (var i = 0; i < len; i++) {
            for (var j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) continue;
                dp[i] = Math.max(dp[j] + 1, dp[i]);
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        T300 solution = new T300();
        int ans = solution.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7});
        System.out.println(ans);
    }
}
