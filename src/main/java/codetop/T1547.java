package codetop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 切棍子的最小成本
 */
public class T1547 {
    public int minCost(int n, int[] cuts) {
        for (int i : cuts)
            set.add(i);
        memo = new int[n + 1][n + 1];
        for (int[] ints : memo)
            Arrays.fill(ints, Integer.MIN_VALUE);
        try {
            return dfs(0, n);
        } finally {
            for (int[] ints : memo) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }

    Set<Integer> set = new HashSet<>();
    int[][] memo;
    int dfs(int i, int j) {
        if (i >= j || !check(i, j)) return 0;
        if (memo[i][j] != Integer.MIN_VALUE) return memo[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            if (!set.contains(k)) continue;
            min = Math.min(
                    (j - i) + dfs(i, k - 1) + dfs(k + 1, j),
                    min
            );
        }
        memo[i][j] = min;
        return min;
    }

    boolean check(int i, int j) {
        for (int k = i; k <= j; k++)
            if (set.contains(k))
                return true;
        return false;
    }

    public static void main(String[] args) {
        T1547 solution = new T1547();
        int[] cuts = {1,3,4,5};
        System.out.println(solution.minCost(7, cuts));
    }
}
