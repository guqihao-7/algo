package leetcode.list.T250_299;

import java.util.Arrays;
import java.util.BitSet;

/*
    根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。

    给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：
    1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

    如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    如果死细胞周围 正好 有三个活细胞，则该位置死细胞复活；
    下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 */
public class T289 {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        BitSet bitSet = new BitSet(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int survivors = survivors(board, i, j);
                if (board[i][j] == 1) {
                    if (survivors < 2) bitSet.set(n * i + j, false);
                    else bitSet.set(n * i + j, survivors == 2 || survivors == 3);
                }
                else if (board[i][j] == 0) {
                    bitSet.set(n * i + j, survivors == 3);
                }
            }
        }
        for (int[] ints : board) {
            Arrays.fill(ints, 0);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (bitSet.get(n * i + j)) {
                    board[i][j] = 1;
                }
            }
        }
    }

    public int survivors(int[][] board, int i, int j) {
        int ans = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x == i && j == y) continue;
                ans += dfs(board, x, y);
            }
        }
        return ans;
    }

    public int dfs(int[][] board, int i, int j) {
        if (i < 0 || j < 0
                || i >= board.length
                || j >= board[0].length) return 0;
        return board[i][j];
    }

    public static void main(String[] args) {
        T289 solution = new T289();
        int[][] board = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        board = new int[][]{
                {1,1,},
                {1,0}
        };

        solution.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}
