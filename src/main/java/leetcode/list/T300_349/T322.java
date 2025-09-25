package leetcode.list.T300_349;

import java.util.Arrays;

public class T322 {
    public static void main(String[] args) {
        T322 solution = new T322();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
    }

    int[] memo;

    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MIN_VALUE);
        return dfs(coins, amount);
    }

    public int dfs(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (memo[amount] != Integer.MIN_VALUE)
            return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dfs(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(res, sub + 1);
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }
}
