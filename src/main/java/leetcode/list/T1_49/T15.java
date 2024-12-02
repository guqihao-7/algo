package leetcode.list.T1_49;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1, k = len - 1;
            int target = -nums[i];
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    j++;
                }
                else if (sum > target) {
                    while (k > j && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    k--;
                }
                else {
                    while (j < k && nums[j + 1] == nums[j]) {
                        j++;
                    }
                    j++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        T15 solution = new T15();
        int[] nums = {0,0,0,0};
        System.out.println(solution.threeSum(nums));
    }
}
