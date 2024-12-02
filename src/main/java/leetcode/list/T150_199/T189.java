package leetcode.list.T150_199;

import java.util.Arrays;
import java.util.Random;

public class T189 {
    public void rotate(int[] nums, int k) {
        if (k == 0) return;
        int len = nums.length;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    /**
     * 反转 nums, [i, j]
     */
    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        T189 solution = new T189();
        solution.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
