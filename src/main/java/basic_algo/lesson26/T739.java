package basic_algo.lesson26;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class T739 {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] ans = new int[n];
        // 下一个更大，单调递增栈
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                ans[idx] = i - idx;
            }

            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        T739 solution = new T739();
        int[] t = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(solution.dailyTemperatures(t)));
    }
}
