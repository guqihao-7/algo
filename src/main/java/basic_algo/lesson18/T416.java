package basic_algo.lesson18;

public class T416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 == 1)
            return false;
        int n = nums.length;
        memo = new Boolean[n][sum / 2 + 1];
        return dfs(n - 1, sum / 2, nums);
    }

    Boolean[][] memo;

    boolean dfs(int i, int bag, int[] nums) {
        if (i < 0)
            return bag == 0;
        if (memo[i][bag] != null)
            return memo[i][bag];
        if (bag < nums[i])
            memo[i][bag] = dfs(i - 1, bag, nums);
        else
            memo[i][bag] = dfs(i - 1, bag, nums) || dfs(i - 1, bag - nums[i], nums);
        return memo[i][bag];
    }
}
