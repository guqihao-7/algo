package codecrush;
import java.lang.String;
public class T1503 {
    public String constructPalindrome(String s, int k) {
        int len = s.length(), startIdx = len - k;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++)
            sb.append(s.charAt(i));
        sb.reverse();
        if (check(s + sb)) return sb.toString();
        sb.delete(0, sb.length());
        for (int i = 0; i < k; i++)
            sb.append(s.charAt(startIdx + i));
        sb.reverse();
        if (check(sb + s)) return sb.toString();
        return null;
    }

    private boolean check(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    public static void main(String[] args) {
        T1503 solution = new T1503();
        System.out.println(solution.constructPalindrome("abc", 2));
    }
}
