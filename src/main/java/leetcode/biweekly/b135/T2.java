package leetcode.biweekly.b135;

import java.util.*;

public class T2 {
    public int minimumLength(String s) {
        Deque<Integer>[] stacks = new ArrayDeque[26];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new ArrayDeque<>();
        }
        List<Integer> removedIdx = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int idx = c - 'a';
            Deque<Integer> stack = stacks[idx];
            if (stack.size() >= 2) {
                removedIdx.add(i);
                Integer top = stack.pop();
                Integer bottom = stack.pop();
                removedIdx.add(bottom);
                stack.push(top);
            }
            else {
                stack.push(i);
            }
        }
        return len - removedIdx.size();
    }

    public static void main(String[] args) {
        T2 solution = new T2();
        System.out.println(solution.minimumLength("abaacbcbb"));
    }
}
