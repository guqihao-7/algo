package leetcode.list.T500_549;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class T503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] a = new int[n * 2];
        for (int i = 0, j = n; i < n; i++, j++) a[i] = a[j] = nums[i];
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
                int idx = stack.pop();
                if (idx < n) {
                    ans[idx] = a[i];
                }
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        T503 solution = new T503();
        int[] n = {1,2,3,4,3};
        System.out.println(Arrays.toString(solution.nextGreaterElements(n)));
    }
}
