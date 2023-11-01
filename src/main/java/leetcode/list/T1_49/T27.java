package leetcode.list.T1_49;

public class T27 {
    public int removeElement(int[] nums, int val) {
        int l = 0, n = nums.length, r = n - 1;
        while (l <= r) {
            while (l < n && nums[l] != val) l++;
            while (r >= 0 && nums[r] == val) r--;
            if (l < r) swap(nums, l, r);
        }
        return l;
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
