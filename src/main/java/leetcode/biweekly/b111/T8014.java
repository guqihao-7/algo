package leetcode.biweekly.b111;

public class T8014 {
    public boolean canMakeSubsequence(String str1, String str2) {
        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (check(str1.charAt(i), str2.charAt(j)))
                j++;
            i++;
        }

        return j == str2.length();
    }

    boolean check(char a, char b) {
        if (a == b) return true;
        int t1 = a - 'a';
        int t2 = b - 'a';
        return (t1 + 1) % 26 == t2;
    }
}
