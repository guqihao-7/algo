package leetcode.biweekly.b111;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T6941 {
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        List<Integer> group3 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums.get(i) == 1)
                group1.add(i);
            else if (nums.get(i) == 2)
                group2.add(i);
            else if (nums.get(i) == 3)
                group3.add(i);
        }

        int[] a = new int[n];
        int idx = 0;
        n = group1.size();
        for (int i = 0; i < n; i++)
            a[idx++] = group1.get(i);
        n = group2.size();
        for (int i = 0; i < n; i++)
            a[idx++] = group2.get(i);
        n = group3.size();
        for (int i = 0; i < n; i++)
            a[idx++] = group3.get(i);

        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);

        Arrays.sort(b);
        memo = new int[105][105];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        int ans = dfs(a, a.length - 1, b, a.length - 1);
        return a.length - ans;
    }

    int[][] memo;

    // 其实这里求 LIS 也可以
    int dfs(int[] a, int i, int[] b, int j) {
        if (i == -1 || j == -1) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (a[i] == b[j])
            memo[i][j] = dfs(a, i - 1, b, j - 1) + 1;
        else memo[i][j] = Math.max(
                dfs(a, i - 1, b, j),
                dfs(a, i, b, j - 1)
        );
        return memo[i][j];
    }

    public static void main(String[] args) {
        T6941 solution = new T6941();
        int[] a = {1, 4, 0, 3, 2};
        int[] b = {0, 1, 2, 3, 4};

        System.out.println(solution.dfs(a, 4, b, 4));
    }
}
