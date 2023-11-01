package leetcode.list.T1_49;

public class T34 {
    public int[] searchRange(int[] nums, int target) {
        int n;
        if ((n = nums.length) <= 0) return new int[]{-1, -1};
        int l = lower_bound(nums, target);
        if (l >= n || nums[l] != target) return new int[]{-1, -1};
        else return new int[]{l, lower_bound(nums, target + 1) - 1};
    }

    int lower_bound(int[] nums, int t) {
        int n = nums.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] < t) l = m + 1;
            else r = m - 1;
        }
        return l;
    }
}
