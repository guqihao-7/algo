package codetop;

/**
 * 最长回文子串
 * 中心扩展法
 */
public class T5 {
    public String longestPalindrome(String s) {
        String res = "";
        int n = s.length();
        for (int i = 0; i < n; i++) {
            String palindrome = palindrome(s, i, i);
            res = res.length() < palindrome.length() ? palindrome : res;
        }

        for (int i = 0; i < n - 1; i++) {
            String palindrome = palindrome(s, i, i + 1);
            res = res.length() < palindrome.length() ? palindrome : res;
        }
        return res;
    }

    public String palindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--; j++;
        }
        return s.substring(i + 1, j);
    }
}
