package leetcode.weekly.w407;

public class T1 {
    public int minChanges(int n, int k) {
        String s1 = Integer.toBinaryString(n);
        String s2 = Integer.toBinaryString(k);
        int len1 = s1.length();
        int len2 = s2.length();

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append("0".repeat(32 - len1));
        sb2.append("0".repeat(32 - len2));
        sb1.append(s1);
        sb2.append(s2);

        System.out.println(sb1);
        System.out.println(sb2);

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (sb1.charAt(i) == '1' && sb2.charAt(i) == '0') {
                ans++;
                sb1.setCharAt(i, '0');
            }
        }
        return (sb1.compareTo(sb2) == 0) ? ans : -1;
    }

    public static void main(String[] args) {
        T1 solution = new T1();
        System.out.println(solution.minChanges(13, 4));
        System.out.println(solution.minChanges(21, 21));
        System.out.println(solution.minChanges(14, 13));
    }
}
