package leetcode.weekly.w468;

import java.util.*;

public class T2 {
    public static void main(String[] args) {
        T2 solution = new T2();
        System.out.println(solution.maxTotalValue(new int[]{1, 3, 2}, 2));
        System.out.println(solution.maxTotalValue(new int[]{4, 2, 5, 1}, 3));
    }

    public long maxTotalValue(int[] nums, int k) {
        int max = nums[0], min  = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        return (long) k * (long) (max - min);
    }
}
