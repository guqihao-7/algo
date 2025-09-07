package leetcode.weekly.w466;

import java.util.Arrays;

public class T1 {
    public static void main(String[] args) {
        T1 solution = new T1();
        System.out.println(solution.minOperations(new int[]{1, 2}));
        System.out.println(solution.minOperations(new int[]{5, 5, 5}));
        System.out.println(solution.minOperations(new int[]{18,95,77}));
    }

    public int minOperations(int[] nums) {
        long count = Arrays.stream(nums)
                .distinct()
                .count();
        if (count == 1) return 0;
        return 1;
    }
}
