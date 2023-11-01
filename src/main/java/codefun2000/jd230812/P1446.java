package codefun2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class P1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String line = br.readLine();
        int n = Integer.parseInt(line.trim());
        line = br.readLine().trim();

        char[] chars = new char[n * 2];
        for (int i = 0, j = n; i < n; i++, j++) {
            chars[i] = line.charAt(i);
            chars[j] = chars[i];
        }
        StringBuilder sb = new StringBuilder();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < i + n; j++) {
                sb.append(chars[j]);
            }
            ans = Math.min(ans, magic(sb.toString()) + i);
            sb.delete(0, sb.length());
        }
        pw.write(ans + "");

        pw.close();
        br.close();
    }

    static int magic(String a) {
        int l = 0, n = a.length(), r = n - 1;
        int ans = 0;
        while (l < r) {
            if (a.charAt(l) != a.charAt(r))
                ans++;
            l++;
            r--;
        }
        return ans;
    }
}
