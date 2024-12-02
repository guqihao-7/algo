package leetcode.list.T150_199;

import java.util.Arrays;

public class T151 {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        chars = trim(chars);
        reverse(chars, 0, chars.length - 1);
        int l = 0, r = l, len = chars.length;
        StringBuilder sb = new StringBuilder();
        while (r < len) {
            while (r < len && chars[r] != ' ') r++;
            reverse(chars, l, r - 1);
            for (int k = l; k < r; k++) sb.append(chars[k]);
            sb.append(" ");
            while (r < len && chars[r] == ' ') r++;
            l = r;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public char[] trim(char[] chars) {
        int len = chars.length, l = 0, r = len - 1;
        if (chars[l] != ' ' && chars[r] != ' ') return chars;
        while (chars[l] == ' ') l++;
        while (chars[r] == ' ') r--;
        char[] ans = new char[r - l + 1];
        for (int k = 0; l <= r; k++, l++) ans[k] = chars[l];
        return ans;
    }

    public void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i, j);
            i++;
            j--;
        }
    }

    public void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static void main(String[] args) {
        T151 solution = new T151();
        System.out.println(solution.reverseWords("  hello world  "));

        String a = "a  b c";
        String[] s = a.split(" ");
        System.out.println(Arrays.toString(s));
    }
}
