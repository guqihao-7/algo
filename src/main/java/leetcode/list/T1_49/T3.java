package leetcode.list.T1_49;

import java.util.*;

public class T3 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int l = 0, r = 0, len = s.length(), ans = 0;
        while (r < len) {
            char c = s.charAt(r++);
            while (window.contains(c))
                window.remove(s.charAt(l++));
            window.add(c);
            ans = Math.max(ans, window.size());
        }
        return ans;
    }

    public static void main(String[] args) {
        T3 solution = new T3();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
