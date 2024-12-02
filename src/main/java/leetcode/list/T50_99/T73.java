package leetcode.list.T50_99;

import java.util.Arrays;

public class T73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rowZerofill = new boolean[m],
                columnZerofill = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowZerofill[i] = true;
                    columnZerofill[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (rowZerofill[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }

        for (int j = 0; j < n; j++) {
            if (columnZerofill[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        T73 solution = new T73();
        int[][] matrix = {
                {0, 1, 0},
                {1, 1, 1},
                {1, 1, 1}
        };
        solution.setZeroes(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
