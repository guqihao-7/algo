package leetcode.weekly.w467;

public class T4 {
    public static void main(String[] args) {

    }

    public int countStableSubsequences(int[] nums) {
        int n = nums.length;
        long MOD = 1000000007L;
        long[][] dp = new long[2][5];
        int cur = 0;
        dp[cur][0] = 1;
        for (int i = 1; i <= n; i++) {
            int prev = cur;
            cur = 1 - cur;
            for (int s = 0; s < 5; s++) {
                dp[cur][s] = 0;
            }
            int par = nums[i - 1] % 2;
            if (par < 0) par += 2;
            for (int s = 0; s < 5; s++) {
                dp[cur][s] = (dp[cur][s] + dp[prev][s]) % MOD;
            }
            long add = dp[prev][0];
            int new_s = (par == 0) ? 1 : 3;
            dp[cur][new_s] = (dp[cur][new_s] + add) % MOD;
            add = dp[prev][1];
            if (add > 0) {
                if (par == 0) {
                    dp[cur][2] = (dp[cur][2] + add) % MOD;
                } else {
                    dp[cur][3] = (dp[cur][3] + add) % MOD;
                }
            }
            add = dp[prev][2];
            if (add > 0) {
                if (par != 0) {
                    dp[cur][3] = (dp[cur][3] + add) % MOD;
                }
            }
            add = dp[prev][3];
            if (add > 0) {
                if (par == 1) {
                    dp[cur][4] = (dp[cur][4] + add) % MOD;
                } else {
                    dp[cur][1] = (dp[cur][1] + add) % MOD;
                }
            }
            add = dp[prev][4];
            if (add > 0) {
                if (par != 1) {
                    dp[cur][1] = (dp[cur][1] + add) % MOD;
                }
            }
        }
        long ans = 0;
        for (int s = 1; s < 5; s++) {
            ans = (ans + dp[cur][s]) % MOD;
        }
        return (int) ans;
    }
}
