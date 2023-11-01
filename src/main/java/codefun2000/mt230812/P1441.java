package codefun2000.mt230812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1441 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);

        line = br.readLine();
        int[] a = new int[n];
        String[] strings = line.split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strings[i]);
        }

        line = br.readLine();
        strings = line.split(" ");
        int n1 = Integer.parseInt(String.valueOf(strings[0])),
                n2 = Integer.parseInt(String.valueOf(strings[1]));

        boolean f = false;
        for (int i = 0; i < n; i++) {
            if (a[i] == n1) {
                if ((i - 1 >= 0 && a[i - 1] == n2) ||
                        (i + 1 < n && a[i + 1] == n2)) {
                    f = true;
                    break;
                }
            }
        }

        System.out.println(f ? "Yes" : "No");

        br.close();
    }
}
