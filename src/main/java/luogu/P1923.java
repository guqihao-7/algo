package luogu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1923 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] strings = line.split(" ");
        int n = Integer.parseInt(strings[0]),
                k = Integer.parseInt(strings[1]);
        line = br.readLine();
        strings = line.split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strings[i]);
        }

        System.out.println(qmin(a, 0, a.length - 1, k));
    }

    static int qmin(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];
        int i = l - 1, j = r + 1, x = nums[l + (r - l) / 2];
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(nums, i, j);
        }
        return k <= j ?
                qmin(nums, l, j, k) :
                qmin(nums, j + 1, r, k);
    }

    static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
