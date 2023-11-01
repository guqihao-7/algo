package basic_algo.lesson18;

import codetop.T1547;

import java.util.Arrays;

/**
 * 完全平方数：
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
public class T279 {
    public int numSquares(int n) {
        return dp(n);
    }

    public int dp(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        //初始化
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }
        //当和为0时，组合的个数为0
        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i * i <= n; i++) {
            // 遍历背包
            for (int j = i * i; j <= n; j++) {
                if (dp[j - i * i] != max) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        T279 solution = new T279();
        System.out.println(solution.numSquares(12));
    }
}
