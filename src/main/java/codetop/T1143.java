package codetop;

import java.util.Arrays;

public class T1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        memo = new int[m][n];
        for (int[] ints : memo)
            Arrays.fill(ints, -1);
        return dfs(text1, m - 1, text2, n - 1);
    }
    int[][] memo;
    int dfs(String text1, int i, String text2, int j) {
        if (i == -1 || j == -1) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (text1.charAt(i) == text2.charAt(j))
            memo[i][j] = dfs(text1, i - 1, text2, j - 1) + 1;
        else
            memo[i][j] = Math.max(
                    dfs(text1, i - 1, text2, j),
                    dfs(text1, i, text2, j - 1)
            );
        return memo[i][j];
    }
}
