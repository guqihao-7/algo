package leetcode.list.T1100_1149;

import data_structure.DynamicSegmentTree;

import java.util.Arrays;

public class T1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        DynamicSegmentTree staticSegmentTree = new DynamicSegmentTree(Integer::sum);
        for (int[] booking : bookings) {
            int from = booking[0] - 1;
            int to = booking[1] - 1;
            staticSegmentTree.addInterval(from, to, booking[2]);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = staticSegmentTree.queryPoint(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        T1109 solution = new T1109();
        int[][] bookings = {
                {1, 2, 20},
                {1, 4, 15},
                {2, 4, 35},
                {2, 3, 50},
                {1, 1, 15}
        };
        int n = 5;
        // [50,120,100,50,0]
        System.out.println(Arrays.toString(solution.corpFlightBookings(bookings, n)));
    }
}
