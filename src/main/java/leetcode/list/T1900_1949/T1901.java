package leetcode.list.T1900_1949;

import java.util.Arrays;

public class T1901 {
    public int[] findPeakGrid(int[][] mat) {
        int left = 0, right = mat.length - 2;
        while (left <= right) {
            int i = (left + right) >>> 1;
            int j = indexOfMax(mat[i]);
            if (mat[i][j] > mat[i + 1][j]) {
                right = i - 1;
            }
            else {
                left = i + 1;
            }
        }
        return new int[]{left, indexOfMax(mat[left])};
    }

    private int indexOfMax(int[] a) {
        int idx = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > a[idx]) {
                idx = i;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        T1901 solution = new T1901();
        int[][] mat = {{1, 4}, {3, 2}};
        System.out.println(Arrays.toString(solution.findPeakGrid(mat)));
    }
}
