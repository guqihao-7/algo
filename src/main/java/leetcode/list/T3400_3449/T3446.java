package leetcode.list.T3400_3449;

import java.util.*;

public class T3446 {
    public static void main(String[] args) {
        T3446 solution = new T3446();
        int[][] ints = solution.sortMatrix(new int[][]{
                {1, 7, 3},
                {9, 8, 2},
                {4, 5, 6},
        });
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    public int[][] sortMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, n - 1});
        res[0][n - 1] = grid[0][n - 1];
        boolean[][] visit = new boolean[m][n];
        visit[0][n - 1] = true;
        int round = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> layer = new ArrayList<>();
            while (sz-- > 0) {
                int[] head = q.removeFirst();
                int i = head[0], j = head[1];
                if (j - 1 >= 0 && !visit[i][j - 1]) {
                    q.addLast(new int[]{i, j - 1});
                    layer.add(grid[i][j - 1]);
                    visit[i][j - 1] = true;
                }

                if (i + 1 < m && j > 0 && !visit[i + 1][j]) {
                    q.addLast(new int[]{i + 1, j});
                    layer.add(grid[i + 1][j]);
                    visit[i + 1][j] = true;
                }
            }

            if (round < n - 1) {
                layer.sort(Comparator.comparingInt(o -> o));
                int curI = 0, curJ = n - round - 1;
                for (Integer integer : layer) {
                    res[curI][curJ] = integer;
                    curI++;
                    curJ++;
                }
            }
            else {
                layer.sort((o1, o2) -> o2 - o1);
                int curI = round - n + 1, curJ = 0;
                for (Integer integer : layer) {
                    res[curI][curJ] = integer;
                    curI++;
                    curJ++;
                }
            }
            round++;
        }
        return res;
    }
}
