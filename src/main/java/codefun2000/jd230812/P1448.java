package codefun2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class P1448 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int m = Integer.parseInt(String.valueOf(line.charAt(0))),
                n = Integer.parseInt(String.valueOf(line.charAt(2)));

        char[][] board = new char[m][n];
        for (int i = 0; i < m; i++) {
            line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int ans = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (board[i][j] == 'X') {
                    int t1 = j;
                    int t2 = j;
                    while (board[i][++t2] != 'X') ;

                    int diff = t2 - t1;
                    int k = i + diff;
                    if (k < m && board[k][t1] == 'X' && board[k][t2] == 'X')
                        ans++;
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}
