package leetcode.list.T1_49;

public class T35 {
    public int searchInsert(int[] nums, int target) {
        return lower_bound(nums, target);
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
