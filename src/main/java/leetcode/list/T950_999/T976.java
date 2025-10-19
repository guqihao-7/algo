package leetcode.list.T950_999;

import data_structure.BinarySearch;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class T976 {
    public static void main(String[] args) {
        T976 solution = new T976();
        // System.out.println(solution.largestPerimeter(new int[]{2, 1, 2}));
        // System.out.println(solution.largestPerimeter2(new int[]{2, 1, 2}));
        // System.out.println(solution.largestPerimeter(new int[]{1, 2, 1, 10}));
        // System.out.println(solution.largestPerimeter2(new int[]{1, 2, 1, 10}));
        // System.out.println(solution.largestPerimeter(new int[]{1, 4, 18, 3, 8, 4, 4}));
        System.out.println(solution.largestPerimeter2(new int[]{1, 4, 18, 3, 8, 4, 4}));
    }

    public int largestPerimeter(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int idx = BinarySearch.lower_bound(nums, nums[i] + nums[j]) - 1;
                if (idx > j && idx <= nums.length - 1 && nums[i] + nums[j] > nums[idx])
                    res = Math.max(nums[i] + nums[j] + nums[idx], res);
                else break;
            }
        }
        return res;
    }

    public int largestPerimeter2(int[] nums) {
        int res = 0, len = nums.length;
        if (len < 3) return 0;
        Arrays.sort(nums);
        int i = nums.length - 3, j = nums.length - 2, k = nums.length - 1;
        while (i >= 0) {
            if (nums[i] + nums[j] > nums[k])
                return nums[i] + nums[j] + nums[k];
            i--;
            j--;
            k--;
        }
        return res;
    }

}
