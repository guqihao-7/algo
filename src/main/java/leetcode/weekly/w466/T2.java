package leetcode.weekly.w466;

public class T2 {
    public static void main(String[] args) {
        T2 solution = new T2();
        String s = "yz";
        System.out.println(solution.minOperations(s));
        s = "a";
        System.out.println(solution.minOperations(s));
    }

    public int minOperations(String s) {
        Character c = null;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            if (c1 == 'a') continue;
            if (c == null) {
                c = c1;
            }
            else {
                if (c > c1)
                    c = c1;
            }
        }
        if (c == null)
            return 0;
        return 123 - c;
    }
}
