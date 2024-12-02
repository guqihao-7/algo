package leetcode.list.T1_49;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T18 {
    public List<List<Integer>> fourSum(int[] nums, long target) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a < len - 3; a++) {
            if (a > 0 && nums[a - 1] == nums[a])
                continue;
            for (int b = a + 1; b < len - 2; b++) {
                if (b > a + 1 && nums[b - 1] == nums[b])
                    continue;
                int c = b + 1, d = len - 1;
                long need = target - (nums[a] + nums[b]);
                while (c < d) {
                    long sum = nums[c] + nums[d];
                    if (sum == need) {
                        ans.add(List.of(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c + 1] == nums[c]) {
                            c++;
                        }
                        c++;
                    }
                    else if (sum > need) {
                        while (d > c && nums[d - 1] == nums[d]) {
                            d--;
                        }
                        d--;
                    }
                    else {
                        while (c < d && nums[c + 1] == nums[c]) {
                            c++;
                        }
                        c++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        T18 solution = new T18();
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        System.out.println(solution.fourSum(nums, target));
    }
}
