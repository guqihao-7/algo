package leetcode.list.T1_49;

import java.util.Arrays;

/**
 * 将矩阵 90° 反转等价于：
 * 1. 先将矩阵沿对角线对称
 * 2. 每一行 reverse
 */
public class T48 {
    public void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }

        for (int[] ints : matrix) {
            reverse(ints);
        }
    }

    public void reverse(int[] ints) {
        int l = 0, r = ints.length - 1;
        while (l < r) {
            swap(ints, l, r);
            l++;
            r--;
        }
    }

    public void swap(int[] ints, int i, int j) {
        int t = ints[i];
        ints[i] = ints[j];
        ints[j] = t;
    }

    public void swap(int[][] mat,
                     int x1, int y1,
                     int x2, int y2) {
        int t = mat[x1][y1];
        mat[x1][y1] = mat[x2][y2];
        mat[x2][y2] = t;
    }

    public static void main(String[] args) {
        T48 solution = new T48();
        int[][] mat = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        solution.rotate(mat);
        for (int[] ints : mat) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
