package codetop;

import java.util.HashSet;
import java.util.Set;

public class T3 {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = 0;
        int n = chars.length;
        Set<Character> set = new HashSet<>();
        int ans = 0;
        while (r < n) {
            char cur = chars[r++];
            while (set.contains(cur))
                set.remove(chars[l++]);
            set.add(cur);
            ans = Math.max(ans, set.size());
        }
        return ans;
    }
}
