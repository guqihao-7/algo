package leetcode.list.T1300_1349;

import java.util.Arrays;

public class T1334 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        dp = new int[n][n];
        for (int[] ints : dp)
            Arrays.fill(ints, Integer.MAX_VALUE);
        this.n = n;
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            // 无向图
            dp[from][to] = weight;
            dp[to][from] = weight;
        }
        floyd();
        int min = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < dp.length; i++) {
            int[] neighbors = dp[i];
            int cnt = 0;
            for (int j = 0; j < neighbors.length; j++) {
                if (i != j && neighbors[j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= min) {
                ans = i;
                min = cnt;
            }
        }
        return ans;
    }

    int[][] dp;
    int n;

    void floyd() {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (i != j && dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
    }
}
