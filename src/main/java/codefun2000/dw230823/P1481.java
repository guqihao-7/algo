package codefun2000.dw230823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class P1481 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        int n = Integer.parseInt(line);
        line = br.readLine();
        String[] strings = line.split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
        }
        int[] a = new int[n];
        int m, l, r;
        if (n % 2 == 1) {
            m = n / 2; l = m - 1; r = m + 1;
            a[m] = pq.remove();
        } else {
            m = n / 2; r = m; l = m - 1;
        }

        while (l >= 0 && r < n) {
            a[l--] = pq.remove();
            a[r++] = pq.remove();
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, Math.abs(a[i + 1] - a[i]));
        }

        max = Math.max(Math.abs(a[n - 1] - a[0]), max);
        pw.println(max);

        pw.close();
        br.close();
    }
}
