package leetcode.list.T2100_2149;

import java.util.HashSet;
import java.util.Set;

public class T2103 {
    public static void main(String[] args) {
        T2103 t = new T2103();
        String a = "B0B6G0R6R0R6G9";
        System.out.println(t.countPoints(a));
    }

    public int countPoints(String rings) {
        int n = rings.length() >> 1;
        Set<Character>[] sets = new HashSet[10];
        for (int i = 0; i < 10; i++) {
            sets[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            char color = rings.charAt(i * 2);
            char pos = rings.charAt(i * 2 + 1);
            int idx = Integer.parseInt(pos + "");
            sets[idx].add(color);
        }
        int ans = 0;
        for (Set<Character> set : sets) {
            if (set.size() == 3) {
                ans++;
            }
        }
        return ans;
    }
}
