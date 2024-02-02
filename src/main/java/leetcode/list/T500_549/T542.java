package leetcode.list.T500_549;


import java.util.Arrays;

/**
 * not done
 */
public class T542 {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] res = new int[m][n];
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) res[i][j] = 0;
                else res[i][j] = dfs(mat, i, j, visit, 0);
                for (boolean[] arr : visit) Arrays.fill(arr, false);
            }
        }
        return res;
    }

    public int dfs(int[][] mat, int x, int y, boolean[][] visit, int dis) {
        int m = mat.length, n = mat[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) return -1;
        if (visit[x][y]) return -1;
        if (mat[x][y] == 0) return dis;

        visit[x][y] = true;

        int left = dfs(mat, x, y - 1, visit, dis + 1);
        int right = dfs(mat, x, y + 1, visit, dis + 1);
        int up = dfs(mat, x - 1, y, visit, dis + 1);
        int down = dfs(mat, x + 1, y, visit, dis + 1);

        int min = Integer.MAX_VALUE;
        if (left != -1) min = left;
        if (right != -1) min = Math.min(min, right);
        if (up != -1) min = Math.min(min, up);
        if (down != -1) min = Math.min(min, down);
        return min;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0,0,0},
                {0,1,0},
                {1,1,1},
        };
        T542 solution = new T542();
        int[][] ints = solution.updateMatrix(mat);
        for (int[] arr : ints) System.out.println(Arrays.toString(arr));
    }
}
