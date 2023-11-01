package codefun2000.xhs230819;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class P1465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        int n = Integer.parseInt(line);
        while (n-- > 0) {
            line = br.readLine();
            if (check(line)) {
                pw.println("Yes");
            } else {
                pw.println("No");
            }
        }

        pw.close();
        br.close();
    }

    static boolean check(String line) {
        int n = line.length();
        char[] chars = new char[n * 2];
        for (int i = 0; i < n; i++) chars[i] = line.charAt(i);
        int l = 0, r = n - 1;

        while (l <= r) {
            if (chars[l] == chars[r]) {
                l++;
                r--;
            } else {
                if (chars[r] == 'w' || chars[r] == 'm' || chars[r] == 'b' || chars[r] == 'd' || chars[r] == 'n' || chars[r] == 'q' || chars[r] == 'p' || chars[r] == 'u')
                    swap(chars, l, r);


                if (chars[l] == 'w') {
                    if (chars[r] != 'v') return false;
                    chars[l] = 'v';
                    //chars[l - 1] = 'v';
                    //l--;
                    for (int k = 2 * n - 1; k >= l + 1; k--)
                        chars[k] = chars[k - 1];
                    r++;
                } else if (chars[l] == 'm') {
                    if (chars[r] != 'n') return false;
                    chars[l] = 'n';
                    //chars[l - 1] = 'n';
                    //l--;
                    for (int k = 2 * n - 1; k >= l + 1; k--)
                        chars[k] = chars[k - 1];
                    r++;
                } else if (chars[l] == 'b') {
                    if (chars[r] != 'd' && chars[r] != 'q') return false;
                    chars[l] = chars[r] == 'd' ? 'd' : 'q';
                    //l++;
                    //r--;
                } else if (chars[l] == 'p') {
                    if (chars[r] != 'q' && chars[r] != 'd') return false;
                    chars[l] = chars[r] == 'q' ? 'q' : 'd';
                    //l++; r--;
                } else if (chars[l] == 'd') {
                    if (chars[r] != 'p') return false;
                    chars[l] = 'p';
                    //l++;r--;
                } else if (chars[l] == 'n') {
                    if (chars[r] != 'u') return false;
                    chars[l] = 'u';
                    //l++;r--;
                } else if (chars[l] == 'q') {
                    if (chars[r] != 'b') return false;
                    chars[l] = 'b';
                    //l++;r--;
                } else if (chars[l] == 'u') {
                    if (chars[r] != 'n') return false;
                    chars[l] = 'n';
                    //l++;r--;
                }
            }
        }
        return true;
    }

    static void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
