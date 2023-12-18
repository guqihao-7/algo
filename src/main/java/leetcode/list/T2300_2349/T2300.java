package leetcode.list.T2300_2349;

import codetop.T23;

import java.util.Arrays;

public class T2300 {

    public static void main(String[] args) {
        T2300 solution = new T2300();
        int[] spells = {5,1,3};
        int[] potions = {1,2,3,4,5};
        int success = 7;
        int[] pairs = solution.successfulPairs(spells, potions, success);
        System.out.println(Arrays.toString(pairs));
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] pairs = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            int need = (int) Math.ceil((double) success / spells[i]);
            int idx = lower_bound(potions, 0, m - 1, need);
            pairs[i] = Math.max(m - idx, 0);
        }

        return pairs;
    }

    int lower_bound(int[] nums, int l, int r, int target) {
        int i = l, j = r;
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                i = m + 1;
            }
            else {
                j = m - 1;
            }
        }
        return i;
    }
}
