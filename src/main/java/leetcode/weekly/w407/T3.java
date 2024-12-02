package leetcode.weekly.w407;

import java.util.ArrayDeque;
import java.util.Deque;

public class T3 {
    public int maxOperations(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '1') {
                stack.push(c);
            }
            else {
                while (!stack.isEmpty() && stack.peek() == '0') {
                    stack.pop();
                }
                stack.push('0');
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        // System.out.println(sb);

        int ans = 0;
        len = sb.length();
        int cntOfOne = 0;
        for (int i = 0; i < len; i++) {
            if (sb.charAt(i) == '1') {
                cntOfOne++;
            } else {
                ans += cntOfOne;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        T3 solution = new T3();
        System.out.println(solution.maxOperations("1001101"));
        System.out.println(solution.maxOperations("00111"));
    }
}
