package leetcode.list.T200_249;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class T215 {
    public static void main(String[] args) throws Exception {
    }

    public int findKthLargest(int[] nums, int k) {
        // 第 1 小 = 第 n - 1 小
        return qmin(nums, 0, nums.length - 1, nums.length - k);
    }

    // 第 k 小
    static int qmin(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];
        int i = l - 1, j = r + 1, x = nums[l + (r - l) / 2];
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(nums, i, j);
        }
        return k <= j ? qmin(nums, l, j, k) :
                qmin(nums, j + 1, r, k);
    }

    static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
