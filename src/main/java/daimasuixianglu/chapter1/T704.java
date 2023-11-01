package daimasuixianglu.chapter1;

public class T704 {
    int lower_bound(int[] a, int target) {
        int n = a.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    public int search(int[] nums, int target) {
        int ans = lower_bound(nums, target);
        return ans < nums.length && nums[ans] == target ?
                ans : -1;
    }
}
