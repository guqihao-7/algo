package codefun2000.bytedance230820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class T3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = Integer.parseInt(line);

        line = br.readLine();
        String[] strings = line.split(" ");
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
            sum += arr[i];
        }

        int avg = sum / n;

        int min = Integer.MAX_VALUE;
        Arrays.sort(arr);
        int idx = n - 1;
        while (idx >= 0 && arr[idx] == arr[idx - 1]) idx--;
        int f = lower_bound(arr, avg);
        while (f < idx) {
            int tmp = 0;
            for (int j = 0; j < idx; j++) {
                if (arr[j] == arr[f]) continue;
                tmp += Math.abs(arr[f] - arr[j]);
            }
            if (tmp > min) break;
            else {
                min = tmp;
                f = lower_bound(arr, avg + 1);
            }
        }


        while (f >= 0) {
            int tmp = 0;
            for (int j = 0; j < idx; j++) {
                if (arr[j] == arr[f]) continue;
                tmp += Math.abs(arr[f] - arr[j]);
            }
            if (tmp > min) break;
            else {
                min = tmp;
                f = lower_bound(arr, avg - 1);
            }
        }

        System.out.println(min);
        br.close();
    }

    static int lower_bound(int[] nums, int target) {
        int n = nums.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }
}
