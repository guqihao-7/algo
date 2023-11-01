package leetcode.everyday;

import leetcode.biweekly.b111.T8013;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 23-08-22
 */
public class T849 {
    public int maxDistToClosest(int[] seats) {
        int i = 0, j = 0, n = seats.length;
        while (seats[j++] != 1);
        j--;
        int max = j - i;
        while (j + 1 < n) { // j 快指针到最后一位就不需要比了
            i = j;
            while(j + 1 < n && seats[++j] != 1);
            int diff = j - i;
            if (seats[j] == 1) {
                diff = j - i >> 1;
            }
            max = Math.max(max, diff);
        }
        return max;
    }

    public static void main(String[] args) {
        T849 solution = new T849();
        int[] a = {0,1,0,0,0,0};
        System.out.println(solution.maxDistToClosest(a));
    }
}
