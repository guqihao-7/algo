package leetcode.list.T200_249;

import java.util.ArrayDeque;
import java.util.Deque;

public class T200 {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                }
                dfs(grid, i, j);
            }
        }
        return ans;
    }

    void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n ||
                grid[i][j] == '0' || grid[i][j] == '2') return;

        grid[i][j] = '2';
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
    }

    void bfs(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        vis[0][0] = true;
        while (!q.isEmpty()) {
            int[] head = q.pop();
            int i = head[0], j = head[1];
            if (grid[i][j] == '1' && !vis[i][j]) {
                vis[i][j] = true;
                if (check(grid, i - 1, j))
                    q.add(new int[]{i - 1, j});
                if (check(grid, i + 1, j))
                    q.add(new int[]{i + 1, j});
                if (check(grid, i, j + 1))
                    q.add(new int[]{i, j + 1});
                if (check(grid, i, j - 1))
                    q.add(new int[]{i, j - 1});
            }
        }
    }

    boolean check(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        return true;
    }

    public static void main(String[] args) {
        T200 solution = new T200();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(solution.numIslands(grid));
    }
}
