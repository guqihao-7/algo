package basic_algo.lesson18;

import java.util.Arrays;

/**
 * 完全背包的代表
 *  零钱兑换
 */
public class T322 {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        memo = new int[n][amount + 1];
        for (int[] ints : memo)
            Arrays.fill(ints, -1);
        int ans = dfs(n - 1, amount, coins);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int[][] memo;

    int dfs(int i, int amount, int[] coins) {
        if (i < 0)
            return amount == 0 ? 0 : Integer.MAX_VALUE;
        if (memo[i][amount] != -1)
            return memo[i][amount];
        if (amount < coins[i])
            memo[i][amount] = dfs(i - 1, amount, coins);
        else
            memo[i][amount] = Math.min(
                    dfs(i - 1, amount, coins),
                    dfs(i, amount - coins[i], coins) + 1
            );
        return memo[i][amount];
    }
}
