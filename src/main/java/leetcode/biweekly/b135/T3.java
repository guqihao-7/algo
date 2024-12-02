package leetcode.biweekly.b135;

import java.util.Arrays;

public class T3 {
    public int minChanges(int[] nums, int k) {
        int len = nums.length;
        int[] diffs = new int[len / 2];
        int idx = 0;
        for (int l = 0, r = len - 1; l < r; l++, r--) {
            diffs[idx++] = Math.abs(nums[l] - nums[r]);
        }

        int ans = Integer.MAX_VALUE;
        for (int diff : diffs) {
            int t = 0;
            for (int i = 0; i < nums.length; i++) {
                int max = Math.max(
                        Math.max(Math.abs(nums[i]),
                                Math.abs(nums[len - i - 1])),
                        Math.max(Math.abs(k - nums[i]),
                                Math.abs(k - nums[len - i - 1])));
                if (diff > 0 && diff <= Math.abs(nums[i] - nums[len - i - 1])) {
                    t += 1;
                }
                else if (diff > Math.abs(nums[i] - nums[len - i - 1]) && diff <= max) {
                    t += 1;
                }
                else if (diff > max) {
                    t += 2;
                }
            }
            if (t != 0) ans = Math.min(ans, t);
        }
        return ans;
    }

    public static void main(String[] args) {
        T3 solution = new T3();
        int[] nums = {0, 1, 2, 3, 3, 6, 5, 4};
        int k = 6;
        System.out.println(solution.minChanges(nums, k));
    }
}