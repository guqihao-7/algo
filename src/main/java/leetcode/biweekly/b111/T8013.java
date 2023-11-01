package leetcode.biweekly.b111;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class T8013 {
    public static void main(String[] args) {
        T8013 solution = new T8013();
        System.out.println(solution.numberOfBeautifulIntegers(1, 10, 1));
    }

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        return 0;
    }



    int lower_bound(int[] a, int x) {
        int n = a.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) >> 1;
            if (a[m] < x) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    boolean check(int i) {
        if (i % 3 != 0) return false;
        int n1 = 0, n2 = 0;
        while (i != 0) {
            int c = i % 10;
            if (c % 2 == 0) n1++;
            else n2++;
            i /= 10;
        }
        return n1 == n2;
    }
}
