package leetcode.list.T2250_2299;

import java.util.ArrayList;
import java.util.List;

public class T2258 {
    public int maximumMinutes(int[][] grid) {
        /*
            1. 找到左上角到右下角的所有路径
            2. 对待每条路径需要判断
                a. 是否会烧到该路径，如果不会，返回 10^9
                b. 如果会， 分两种情况，完全不能通过，则返回 -1，否则返回可以待得最长时间
            3. 取所有答案最大值，若 ans=10^9，则返回 -1

            每走一步，看下最近的火离多远，取最小值
         */

        dfsForPaths(grid, 0, 0, new ArrayList<>());
        initCopy(grid);
        int size = paths.size();
        for (int i = size - 1; i >= 0; i--) {

        }
        return 0;
    }

    List<List<int[]>> paths = new ArrayList<>();

    int[][] copy;

    void initCopy(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, n);
        }
    }

    void dfsForPaths(int[][] grid, int i, int j, List<int[]> curPath) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }

        if (grid[i][j] != 0) {
            return;
        }

        int[] point = new int[2];
        point[0] = i;
        point[1] = j;
        curPath.add(point);

        if (i == m - 1 && j == n - 1) {
            paths.add(new ArrayList<>(curPath));
            curPath.remove(curPath.size() - 1);
            return;
        }

        point[0] = i - 1;
        dfsForPaths(grid, i - 1, j, curPath);

        point[0] = i + 1;
        point[1] = j;
        dfsForPaths(grid, i + 1, j, curPath);

        point[0] = i;
        point[1] = j - 1;
        dfsForPaths(grid, i, j - 1, curPath);

        point[0] = i;
        point[1] = j + 1;
        dfsForPaths(grid, i, j + 1, curPath);
        curPath.remove(curPath.size() - 1);
    }

    int maxLiveTime(List<int[]> path, int[][] grid) {
        int time = path.size();
        for (int i = 0; i < time; i++) {
            int[] curPos = path.get(i);
        }
        return 0;
    }
}
