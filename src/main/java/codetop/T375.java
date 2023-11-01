package codetop;

import java.util.Arrays;

public class T375 {
    public int getMoneyAmount(int n) {
        memo = new int[n + 1][n + 1];
        for (int[] ints : memo)
            Arrays.fill(ints, -1);
        return dfs(1, n);
    }
    int[][] memo;
    int dfs(int i, int j) {
        if (i >= j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(
                    Math.max(dfs(i, k - 1), dfs(k + 1, j)) + k,
                    min
            );
        }
        memo[i][j] = min;
        return min;
    }

    public static void main(String[] args) {
        T375 solution = new T375();
        System.out.println(solution.getMoneyAmount(10));
    }
}
