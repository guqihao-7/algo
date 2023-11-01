package codefun2000.mt230819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * AC
 */
public class P1470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = Integer.parseInt(line);
        line = br.readLine();
        String[] strings = line.split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(strings[i]);

        int sum = 0;
        for (int num : a)
            sum += num;
        memo = new long[n][sum + 1];
        for (long[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        System.out.println(dfs(n - 1, sum, a));

        br.close();
    }

    static long[][] memo;
    static int MOD = 1000000007;
    static long dfs(int i, int j, int[] nums) {
        if (i < 0) {
            return j == 0 ? 1 : 0;
        }
        if (memo[i][j] != -1) return memo[i][j];
        long sum = 0;
        for (int k = 1; k <= j; k++) {
            if (k != nums[i]) {
                sum += dfs(i - 1, j - k, nums) % MOD;
                sum %= MOD;
            }
        }
        memo[i][j] = sum;
        return sum;
    }
}
