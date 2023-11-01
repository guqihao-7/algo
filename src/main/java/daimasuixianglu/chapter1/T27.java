package daimasuixianglu.chapter1;

public class T27 {
    public int removeElement(int[] nums, int val) {
        int n = nums.length, l = 0, r = n - 1;
        while (l <= r) {
            while (r >= 0 && nums[r] == val) r--;
            while (l < n && nums[l] != val) l++;
            if (l < r) swap(nums, l, r);
        }
        return l;
    }

    void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
