package codetop;

import java.util.Arrays;

public class T1312 {
    public int minInsertions(String s) {
        int n = s.length();
        memo = new int[n][n];
        for (int[] ints : memo)
            Arrays.fill(ints, -1);
        return s.length() - dfs(s, n - 1, new StringBuilder(s).reverse().toString(), n - 1);
    }

    int[][] memo;

    int dfs(String a, int i, String b, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (a.charAt(i) == b.charAt(j))
            memo[i][j] = dfs(a, i - 1, b, j - 1) + 1;
        else
            memo[i][j] = Math.max(
                    dfs(a, i - 1, b, j),
                    dfs(a, i, b, j - 1)
            );
        return memo[i][j];
    }

    int dptable(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        return dp[m][n];
    }
}
