package leetcode.list.T2650_2699;


import java.util.Arrays;

public class T2670 {
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n];
        int[] tmp = new int[51];
        for (int i = 0; i < n; i++) {
            for (int p = 0; p <= i; p++) {
                tmp[nums[p]]++;
            }
            int l = 0, r = 0;
            for (int k : tmp) {
                if (k != 0) l++;
            }
            Arrays.fill(tmp, 0);
            for (int j = i + 1; j < n; j++) {
                tmp[nums[j]]++;
            }
            for (int j : tmp) {
                if (j != 0) r++;
            }
            Arrays.fill(tmp, 0);
            diff[i] = l - r;
        }
        return diff;
    }

    public static void main(String[] args) {
        T2670 solution = new T2670();
        int[] a = new int[] {
                3,2,3,4,2
        };
        System.out.println(Arrays.toString(solution.distinctDifferenceArray(a)));
    }
}
