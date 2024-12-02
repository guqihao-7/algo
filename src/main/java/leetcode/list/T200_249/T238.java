package leetcode.list.T200_249;

public class T238 {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] prefix = new int[len],
                suffix = new int[len];
        prefix[0] = nums[0];
        suffix[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++)
            prefix[i] = nums[i] * prefix[i - 1];
        for (int i = len - 2; i >= 0; i--)
            suffix[i] = nums[i] * suffix[i + 1];
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) ans[i] = suffix[1];
            else if (i == len - 1) ans[i] = prefix[len - 2];
            else ans[i] = prefix[i - 1] * suffix[i + 1];
        }
        return ans;
    }
}
