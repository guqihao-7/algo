package leetcode.list.T200_249;

import java.util.ArrayList;
import java.util.List;

public class T228 {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length, i = 0, j = 0;
        List<String> res = new ArrayList<>();
        while (j < n) {
            while (j + 1 < n && nums[j + 1] == nums[j] + 1)
                j++;
            if (i == j) res.add(String.valueOf(nums[j]));
            else res.add(nums[i] + "->" + nums[j]);
            i = ++j;
        }
        return res;
    }

    public static void main(String[] args) {
        T228 solution = new T228();
        int[] nums = {0, 2, 3, 4, 6, 8, 9};
        System.out.println(solution.summaryRanges(nums));
    }
}
