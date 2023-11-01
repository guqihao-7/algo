package leetcode.list.T1_49;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T46 {
    public List<List<Integer>> permute(int[] nums) {
        perm(0, nums.length - 1, nums);
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    void perm(int begin, int end, int[] nums) {
        if (begin == end) {
            List<Integer> tmp = new ArrayList<>();
            for (int num : nums) tmp.add(num);
            res.add(tmp);
            return;
        }

        for (int i = begin; i <= end; i++) {
            swap(nums, begin, i);
            perm(begin + 1, end, nums);
            swap(nums, begin, i);
        }
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
