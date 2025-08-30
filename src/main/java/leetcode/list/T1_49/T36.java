package leetcode.list.T1_49;

public class T36 {
    public static void main(String[] args) {
        T36 solution = new T36();
        System.out.println(solution.isValidSudoku(
                new char[][]{
                          {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                }
        ));
    }

    public boolean isValidSudoku(char[][] board) {
        return checkRow(board) && checkColumn(board) && checkSmallBox(board);
    }

    private boolean checkRow(char[][] board) {
        for (char[] row : board) {
            int[] cnt = new int[9];
            for (char c : row) {
                if (c == '.') continue;
                cnt[c - '1']++;
            }
            for (int j : cnt) {
                if (j > 1)
                    return false;
            }
        }
        return true;
    }

    private boolean checkColumn(char[][] board) {
        char[][] newBoard = new char[board.length][board[0].length];
        transpose(board, newBoard);
        return checkRow(newBoard);
    }

    private boolean checkSmallBox(char[][] board) {
        int[][] points = new int[][]{
                {0, 0}, {0, 3}, {0, 6},
                {3, 0}, {3, 3}, {3, 6},
                {6, 0}, {6, 3}, {6, 6},
        };

        for (int[] point : points) {
            int[] cnt = new int[10];
            for (int i = point[0]; i < point[0] + 3; i++) {
                for (int j = point[1]; j < point[1] + 3; j++) {
                    if (board[i][j] == '.') continue;
                    cnt[board[i][j] - '1']++;
                }
            }

            for (int j : cnt) {
                if (j > 1) return false;
            }
        }
        return true;
    }

    private void transpose(char[][] a, char[][] b) {
        int m = a.length, n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[j][i];
            }
        }
    }
}
