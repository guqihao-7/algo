package leetcode.list.T450_499;

import java.util.*;

/**
 * bfs
 * 元素加到 q 中的顺序不变，就是 1 4 2 7 5 3 8 6 9 这样的顺序加到队列
 * 只不过保存答案时需要拐弯，reverse
 */
public class T498 {
    public int[] findDiagonalOrder(int[][] mat) {
        Deque<int[]> q = new ArrayDeque<>();
        int m = mat.length, n = mat[0].length;
        boolean[][] visit = new boolean[m][n];

        int[] ans = new int[m * n];
        int idx = 0;
        ans[idx++] = mat[0][0];

        q.add(new int[]{0, 0});
        visit[0][0] = true;

        int round = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            List<int[]> layer = new ArrayList<>();
            while (size-- > 0) {
                int[] head = q.remove();
                if (head[0] + 1 < m && head[1] < n
                        && !visit[head[0] + 1][head[1]]) {
                    layer.add(new int[]{head[0] + 1, head[1]});
                    visit[head[0] + 1][head[1]] = true;
                }

                if (head[1] + 1 < n && !visit[head[0]][head[1] + 1]) {
                    layer.add(new int[]{head[0], head[1] + 1});
                    visit[head[0]][head[1] + 1] = true;
                }
            }
            if (round % 2 == 1) Collections.reverse(layer);
            for (int[] ints : layer) ans[idx++] = mat[ints[0]][ints[1]];
            if (round % 2 == 1) Collections.reverse(layer);
            round++;
            q.addAll(layer);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        T498 solution = new T498();
        int[] ans = solution.findDiagonalOrder(mat);
        System.out.println(Arrays.toString(ans));
    }
}
