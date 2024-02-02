package leetcode.list.T2800_2849;

import java.util.List;

public class T2828 {
    public boolean isAcronym(List<String> words, String s) {
        int len = s.length();
        if (len != words.size()) return false;
        for (int i = 0; i < len; i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}