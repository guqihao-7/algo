package codetop;

import ds.BinarySearch;

/**
 * 搜索旋转排序数组
 */
public class T33 {
    public int search(int[] nums, int target) {
        int n = nums.length, l = 0, r = n - 2;
        if (nums[n - 1] > nums[0]) {
            int t = lower_bound(nums, target);
            if (nums[t] == target) return t;
            return -1;
        }

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[n - 1]) l = m + 1;
            else r = m - 1;
        }
        int minIdx = l;

        if (target > nums[n - 1]) {
            l = 0;
            r = minIdx - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] < target) l = m + 1;
                else r = m - 1;
            }
            return nums[l] == target ? l : -1;
        } else if (target < nums[n - 1]) {
            l = minIdx;
            r = n - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] < target) l = m + 1;
                else r = m - 1;
            }
            return nums[l] == target ? l : -1;
        } else return minIdx;
    }

    int lower_bound(int[] nums, int x) {
        int n = nums.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] < x) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        new T33().search(nums, 3);
    }
}
