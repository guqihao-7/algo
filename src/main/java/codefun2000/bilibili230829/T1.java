package codefun2000.bilibili230829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class T1 {   // [1,12,-5,-6,50,3],4
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int lastCommaIdx = line.lastIndexOf(",");
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (int i = 1; i <= lastCommaIdx - 2; i++) {
            sb1.append(line.charAt(i));
        }

        for (int i = lastCommaIdx + 1; i < line.length(); i++) {
            sb2.append(line.charAt(i));
        }

        int k = Integer.parseInt(sb2.toString());
        String[] strings = sb1.toString().split(",");
        int n = strings.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strings[i]);
        }

        // 开始滑窗
        int i = 0, j = k - 1, sum = 0;
        for (int m = 0; m < k; m++)
            sum += a[m];
        int max = sum;
        while (j + 1 < n) {
            sum += a[++j];
            sum -= a[i++];
            max = Math.max(max, sum);
        }
        System.out.println((double) max / k);
    }
}
