package leetcode.weekly.w407;

public class T2 {
    public boolean doesAliceWin(String s) {
        int cnt = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                cnt++;
            }
        }

        return cnt != 0;
    }

    public static void main(String[] args) {
        T2 solution = new T2();
        System.out.println(solution.doesAliceWin("leetcoder"));
        System.out.println(solution.doesAliceWin("bbcd"));
    }
}
