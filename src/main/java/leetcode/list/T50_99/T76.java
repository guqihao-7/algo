package leetcode.list.T50_99;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class T76 {
    public static void main(String[] args) {
        String s = "cwaefgcf", t = "cae";
        T76 solution = new T76();
        System.out.println(solution.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> cnt1 = new HashMap<>(),
                cnt2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++)
            cnt2.put(t.charAt(i), cnt2.getOrDefault(t.charAt(i), 0) + 1);
        int left = 0, right = 0;

        String res = null;

        while (right < s.length()) {
            char c = s.charAt(right);
            cnt1.put(c, cnt1.getOrDefault(c, 0) + 1);
            right++;

            while (left < right && needShrink(cnt1, cnt2, t)) {
                c = s.charAt(left);
                cnt1.put(c, cnt1.getOrDefault(c, 0) - 1);

                if (res == null) {
                    res = s.substring(left, right);
                }
                else {
                    if (right - left < res.length()) {
                        res = s.substring(left, right);
                    }
                }

                left++;
            }
        }

        return res == null ? "" : res;
    }

    private boolean needShrink(Map<Character, Integer> cnt1, Map<Character, Integer> cnt2, String t) {
        boolean res = true;
        Set<Character> keys = cnt2.keySet();
        for (Character key : keys)
            res &= cnt1.containsKey(key) && cnt1.get(key) >= cnt2.get(key);
        return res;
    }
}
