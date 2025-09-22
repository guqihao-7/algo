package leetcode.weekly.w468;

import java.util.*;

public class T4 {
    public static void main(String[] args) {
        T4 solution = new T4();
        System.out.println(solution.maxTotalValue(new int[]{1, 3, 2}, 2));
        System.out.println(solution.maxTotalValue(new int[]{4, 2, 5, 1}, 3));
        System.out.println(solution.maxTotalValue(new int[]{11, 8}, 2));
        System.out.println(solution.maxTotalValue(new int[]{18, 36, 6}, 6));
        System.out.println();
        // System.out.println(solution.maxTotalValue2(new int[]{1, 3, 2}, 2));
        // System.out.println(solution.maxTotalValue2(new int[]{4, 2, 5, 1}, 3));
        // System.out.println(solution.maxTotalValue2(new int[]{11, 8}, 2));
        // System.out.println(solution.maxTotalValue2(new int[]{18, 36, 6}, 6));
    }

    public long maxTotalValue(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);

        for (int i = 0; i < len; i++) {
            int max = nums[i], min = nums[i];
            for (int j = i; j < len; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                pq.add(max - min);
                if (pq.size() > k) pq.poll();
            }
        }

        long res = 0;
        while (!pq.isEmpty())
            res += pq.poll();
        return res;
    }
}
