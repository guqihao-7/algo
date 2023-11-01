package basic_algo.lesson18;

import java.util.Arrays;

public class T494 {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        sum += target;
        if (sum < 0 || sum % 2 == 1)
            return 0;
        int bag = sum >> 1;
        memo = new int[n][bag + 1];
        for (int[] ints : memo)
            Arrays.fill(ints, -1);
        return dfs(n - 1, bag, nums);
    }
    int[][] memo;
    // 只有体积, 求恰好装满背包的方案总数
    int dfs(int i, int bag, int[] nums) {
        if (i < 0)
            return bag == 0 ? 1 : 0;
        if (memo[i][bag] != -1)
            return memo[i][bag];
        if (bag < nums[i])
            memo[i][bag] = dfs(i - 1, bag, nums);
        else
            memo[i][bag] = dfs(i - 1, bag, nums) + dfs(i - 1, bag - nums[i], nums);
        return memo[i][bag];
    }
}
