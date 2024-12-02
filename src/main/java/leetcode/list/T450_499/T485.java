package leetcode.list.T450_499;

import java.util.ArrayDeque;
import java.util.Deque;

public class T485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : nums) {
            if (num == 1) {
                stack.push(1);
                max = Math.max(max, stack.size());
            } else {
                stack.clear();
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,0,1,1,1};
        T485 solution = new T485();
        System.out.println(solution.findMaxConsecutiveOnes(nums));
    }
}
