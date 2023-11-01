package luogu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2602 {
    static long[][] memo = new long[15][15];
    static long[] num = new long[15];
    static long now;

    static long dfs(int pos, int sum, boolean lead, boolean limit) {
        long ans = 0;
        if (pos == 0) return sum;
        if (!lead && !limit && memo[pos][sum] != -1)
            return memo[pos][sum];
        long up = limit ? num[pos] : 9;
        for (int i = 0; i <= up; i++) {
            if (i == 0 && lead) ans += dfs(pos - 1, sum, true, limit && (i == up));
            else if (i == now) ans += dfs(pos - 1, sum + 1, false, limit && (i == up));
            else if (i != now) ans += dfs(pos - 1, sum, false, limit && (i == up));
        }
        if (!lead && !limit) memo[pos][sum] = ans;
        return ans;
    }

    static long solve(long x) {
        int len = 0;
        while (x != 0) {
            num[++len] = x % 10;
            x /= 10;
        }
        for (long[] longs : memo)
            Arrays.fill(longs, -1);
        return dfs(len, 0, true, true);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] strings = line.split(" ");
        long a = Long.parseLong(strings[0]), b = Long.parseLong(strings[1]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            now = i;
            sb.append(solve(b) - solve(a - 1)).append(" ");
        }
        System.out.println(sb);

        br.close();
    }
}
