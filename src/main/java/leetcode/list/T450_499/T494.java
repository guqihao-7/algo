package leetcode.list.T450_499;

public class T494 {
    public static void main(String[] args) {
        T494 solution = new T494();
        int[] nums = new int[]{
                1, 1, 1, 1, 1,
        };
        int target = 3;
        System.out.println(solution.findTargetSumWays(nums, target));
    }

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    int ans;

    void dfs(int[] nums, int target, int idx, int sum) {
        if (idx == nums.length) {
            if (sum == target) {
                ans++;
            }
            return;
        }

        sum += nums[idx];
        dfs(nums, target, idx + 1, sum);

        sum -= nums[idx] * 2;
        dfs(nums, target, idx + 1, sum);
    }
}
