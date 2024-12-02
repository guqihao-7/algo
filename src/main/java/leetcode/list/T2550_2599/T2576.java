package leetcode.list.T2550_2599;

import java.util.Arrays;

public class T2576 {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        for (int j = (n + 1) / 2; j < n; j++) {
            if (nums[i] * 2 <= nums[j]) { // 找到一个匹配
                i++;
            }
        }
        return i * 2;
    }

    public static void main(String[] args) {
        T2576 solution = new T2576();
        System.out.println(solution.maxNumOfMarkedIndices(new int[]{3, 5, 2, 4}));
        System.out.println(solution.maxNumOfMarkedIndices(new int[]{9, 5, 2, 4}));
        System.out.println(solution.maxNumOfMarkedIndices(new int[]{6, 7, 8}));
    }
}
