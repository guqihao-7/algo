package leetcode.list.T1900_1949;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class T1935 {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] strings = text.split(" ");
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < brokenLetters.length(); i++) {
            set.add(brokenLetters.charAt(i));
        }
        int res = 0;
        for (String string : strings) {
            boolean f = true;
            for (int i = 0; i < string.length(); i++) {
                if (set.contains(string.charAt(i))) {
                    f = false;
                    break;
                }
            }
            if (f) res++;
        }

        return res;
    }
}
