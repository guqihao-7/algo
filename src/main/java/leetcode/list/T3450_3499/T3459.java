package leetcode.list.T3450_3499;

import java.util.Arrays;

public class T3459 {
    public static void main(String[] args) {
        T3459 solution = new T3459();
        int[][] grid = new int[][]{
                {2, 2, 1, 2, 2},
                {2, 0, 2, 2, 0},
                {2, 0, 1, 1, 0},
                {1, 0, 2, 2, 2},
                {2, 0, 0, 2, 2}
                // {1,2,3},
                // {3,1,2}
        };
        System.out.println(solution.lenOfVDiagonal(grid));
    }

    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] newGrid = new int[3 * m - 2][3 * n - 2];
        fillNewGrid(newGrid, grid);

        int res = -1;

        for (int i = m - 1; i < 2 * m - 1; i++) {
            for (int j = n - 1; j < 2 * n - 1; j++) {
                if (newGrid[i][j] != 1) continue;
                int leftUp = leftUp(newGrid, i, j);
                int leftDown = leftDown(newGrid, i, j);
                int rightUp = rightUp(newGrid, i, j);
                int rightDown = rightDown(newGrid, i, j);
                res = Math.max(res, max4(
                        leftUp, leftDown, rightUp, rightDown
                ));
            }
        }

        return res;
    }

    private int max4(int a, int b, int c, int d) {
        return Math.max(a, Math.max(Math.max(b, c), d));
    }

    private int leftUp(int[][] newGrid, int i, int j) {
        int res = 1, round = 1;
        while (i - 1 > 0 && j - 1 > 0) {
            i--; j--;
            if (round % 2 == 1) {
                if (newGrid[i][j] == 2) res++;
                else return res;
            }
            else {
                if (newGrid[i][j] == 0) res++;
                else return res;
            }
            round++;
        }
        return res;
    }

    private int leftDown(int[][] newGrid, int i, int j) {
        int res = 1, round = 1;
        while (i + 1 < newGrid.length && j - 1 > 0) {
            i++; j--;
            if (round % 2 == 1) {
                if (newGrid[i][j] == 2) res++;
                else return res;
            }
            else {
                if (newGrid[i][j] == 0) res++;
                else return res;
            }
            round++;
        }
        return res;
    }

    private int rightUp(int[][] newGrid, int i, int j) {
        int res = 1, round = 1;
        while (i - 1 > 0 && j + 1 < newGrid[0].length) {
            i--; j++;
            if (round % 2 == 1) {
                if (newGrid[i][j] == 2) res++;
                else return res;
            }
            else {
                if (newGrid[i][j] == 0) res++;
                else return res;
            }
            round++;
        }
        return res;
    }

    private int rightDown(int[][] newGrid, int i, int j) {
        int res = 1, round = 1;
        while (i + 1 < newGrid.length && j + 1 < newGrid[0].length) {
            i++; j++;
            if (round % 2 == 1) {
                if (newGrid[i][j] == 2) res++;
                else return res;
            }
            else {
                if (newGrid[i][j] == 0) res++;
                else return res;
            }
            round++;
        }
        return res;
    }

    private void fillNewGrid(int[][] newGrid, int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for (int[] ints : newGrid) {
            Arrays.fill(ints, Integer.MIN_VALUE);
        }

        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, newGrid[m - 1 + i], n - 1, n);
        }

        // 上面的
        int curI = m - 1, curJ = n - 1;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= m - 1; i++) {
                newGrid[curI - i][curJ] = newGrid[curI + i][curJ];
            }
            curJ += 1;
        }

        // 下面的
        curI = 2 * m - 2; curJ = n - 1;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= m - 1; i++) {
                newGrid[curI + i][curJ] = newGrid[curI - i][curJ];
            }
            curJ += 1;
        }

        // 左面
        curI = m - 1; curJ = n - 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n - 1; j++) {
                newGrid[curI][curJ - j] = newGrid[curI][curJ + j];
            }
            curI += 1;
        }

        // 右面
        curI = m - 1; curJ = 2 * n - 2;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n - 1; j++) {
                newGrid[curI][curJ + j] = newGrid[curI][curJ - j];
            }
            curI += 1;
        }
    }
}
