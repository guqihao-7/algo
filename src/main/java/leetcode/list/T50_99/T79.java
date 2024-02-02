package leetcode.list.T50_99;


public class T79 {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (search(board, i, j, word, 0, visit)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean search(char[][] board, int x, int y, String word, int idx, boolean[][] visit) {
        int m = board.length, n = board[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visit[x][y])
            return false;
        visit[x][y] = true;
        try {
            if (board[x][y] != word.charAt(idx))
                return false;
            if (idx == word.length() - 1)
                return word.charAt(idx) == board[x][y];
            return search(board, x + 1, y, word, idx + 1, visit) ||
                    search(board, x - 1, y, word, idx + 1, visit) ||
                    search(board, x, y + 1, word, idx + 1, visit) ||
                    search(board, x, y - 1, word, idx + 1, visit);
        } finally {
            // 这里只需要保证一条路上不重复即可
            visit[x][y] = false;
        }
    }

    public static void main(String[] args) {
        T79 solution = new T79();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        // board = new char[][]{
        //         {'a', 'b'}
        // };

        String word = "ABCB";
        // word = "ba";

        System.out.println(solution.exist(board, word));
    }
}
