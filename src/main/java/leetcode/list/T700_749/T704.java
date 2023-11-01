package leetcode.list.T700_749;

public class T704 {
    public int search(int[] nums, int target) {
        return lower_bound(nums, target);
    }

    int lower_bound(int[] nums, int t) {
        int l = 0, n = nums.length, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < t) l = m + 1;
            else r = m - 1;
        }
        return l < n && nums[l] == t ? l : -1;
    }
}
