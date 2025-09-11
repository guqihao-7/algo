package leetcode.list.T1300_1349;

import java.util.Arrays;

public class T1304 {
    public static void main(String[] args) {
        T1304 solution = new T1304();
    }

    public int[] sumZero(int n) {
        int[] res = new int[n];
        int l = -1001, r = 1001;
        int lIdx = 0, rIdx = n - 1;
        while (lIdx < rIdx) {
            res[lIdx++] = l++;
            res[rIdx--] = r--;
        }
        return res;
    }
}
