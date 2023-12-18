package leetcode.list.T2600_2649;

import java.util.ArrayDeque;
import java.util.Deque;

public class T2609 {
    public int findTheLongestBalancedSubstring(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == '0') {
                stack.push('0');
                i++;
            } else {
                int len = 0;
                while (i < n && s.charAt(i) == '1') {
                    i++;
                    if (stack.isEmpty()) {
                        break;
                    }
                    stack.poll();
                    len++;
                }
                stack.clear();
                ans = Math.max(ans, len * 2);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        T2609 solution = new T2609();
        System.out.println(solution.findTheLongestBalancedSubstring("01000111"));
        System.out.println(solution.findTheLongestBalancedSubstring("111"));
        System.out.println(solution.findTheLongestBalancedSubstring("00111"));
        System.out.println(solution.findTheLongestBalancedSubstring("001011"));
    }
}
