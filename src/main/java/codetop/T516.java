package codetop;

import java.util.Arrays;

/**
 * 最长回文子序列
 */
public class T516 {
    public int longestPalindromeSubseq(String s) {
        return lcs(s, new StringBuilder(s).reverse().toString());
    }

    int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] ints : memo)
            Arrays.fill(ints, -1);
        return dfs(s1, m - 1, s2, n - 1);
    }
    int[][] memo;
    int dfs(String s1, int i, String s2, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (s1.charAt(i) == s2.charAt(j))
            memo[i][j] = dfs(s1, i - 1, s2, j - 1) + 1;
        else
            memo[i][j] = Math.max(
                    dfs(s1, i - 1, s2, j),
                    dfs(s1, i, s2, j - 1)
            );
        return memo[i][j];
    }
}
