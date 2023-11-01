package codefun2000.bytedance230820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class T4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = Integer.parseInt(line);

        char[] chars = new char[n];
        line = br.readLine();
        for (int i = 0; i < n; i++) {
            chars[i] = line.charAt(i);
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (chars[j] == chars[j - 1])
                    dp[i][j] = dp[i][j - 1];
                else
                    dp[i][j] = dp[i][j - 1] + 1;
            }
        }

        int sum = 0;
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                sum += get(chars, i, i + len);
            }
        }
        System.out.println(sum);


        br.close();
    }


    static public int get(char[] chars, int i, int j) {
        int l = i, r = i;
        int sum = 0;
        while (r <= j) {
            while (r <= j && chars[r] == chars[l]) r++;
            sum += 1;
            l = r;
        }
        return sum;
    }

}
