package leetcode.list.T950_999;

import java.util.Arrays;

public class T977 {
    public static void main(String[] args) {
        T977 solution = new T977();
        int[] a = {0, 2};
        System.out.println(Arrays.toString(solution.sortedSquares(a)));
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        int p = n - 1;
        int[] res = new int[n];
        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                res[p] = nums[i] * nums[i];
                i++;
            } else {
                res[p] = nums[j] * nums[j];
                j--;
            }
            p--;
        }
        return res;
    }


}
