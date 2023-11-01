package codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T15 {
    public static void main(String[] args) {
        T15 s = new T15();
        int[] nums = {0, 0, 0, 0};
        System.out.println(s.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i <= n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break; // 优化 1, nums[i] 加上最小的两个大于0, 后面不可能再有大于0的了, 因为已经排过序了
            if (nums[i] + nums[n - 1] + nums[n - 2] < 0) continue; // 优化2, nums[i] 加上最大的两个小于 0, 后面 nums[i] 会变大, 还有机会等于 0, 因此是 continue
            int need = -nums[i];
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum > need) k--;
                else if (sum < need) j++;
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (k > j && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return res;
    }
}
