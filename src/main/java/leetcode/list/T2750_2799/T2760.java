package leetcode.list.T2750_2799;

public class T2760 {
    /*
        给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。

        请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length) 且满足以下条件的 最长子数组 ：

        nums[l] % 2 == 0
        对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
        对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
        以整数形式返回满足题目要求的最长子数组的长度。

        注意：子数组 是数组中的一个连续非空元素序列。
     */
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int res = 0, n = nums.length;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (isSatisfied(nums, l, r, threshold))  {
                    res = Math.max(res, r - l + 1);
                }
            }
        }
        return res;
    }

    public boolean isSatisfied(int[] nums, int l, int r, int threshold) {
        if (nums[l] % 2 != 0) {
            return false;
        }
        for (int i = l; i <= r; i++) {
            if (nums[i] > threshold || (i < r && nums[i] % 2 == nums[i + 1] % 2)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        T2760 solution = new T2760();
        int[] a = {2, 3, 4, 5};
        System.out.println(solution.longestAlternatingSubarray(a, 4));
    }
}
