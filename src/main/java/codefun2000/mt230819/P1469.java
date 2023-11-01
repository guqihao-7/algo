package codefun2000.mt200819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 72 TLE
 */
public class P1469 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        char[] chars = s.toCharArray();
        int sum = 0;
        StringBuilder[] builders = getString(n);
        StringBuilder startWithZero = builders[0],
                startWithOne = builders[1];
        for (int len = 2; len <= n; len++) {    // 长度
            for (int i = 0; i <= n - len; i++) {    // 起点
                sum += minOperations(startWithZero, startWithOne, i, len, chars);
            }
        }
        System.out.println(sum);
    }

    static int minOperations(StringBuilder sb1, StringBuilder sb2, int i, int len, char[] chars) {
        int r1 = 0, r2 = 0;
        int j = i;
        while (len-- > 0) {
            if (chars[j] != sb1.charAt(j)) r1++;
            if (chars[j] != sb2.charAt(j)) r2++;
            j++;
        }
        return Math.min(r1, r2);
    }

    static StringBuilder[] getString(int len) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                sb1.append(0);
                sb2.append(1);
            } else {
                sb1.append(1);
                sb2.append(0);
            }
        }
        return new StringBuilder[]{sb1, sb2};
    }
}
