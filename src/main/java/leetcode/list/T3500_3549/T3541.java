package leetcode.list.T3500_3549;

public class T3541 {
    public static void main(String[] args) {
        T3541 solution = new T3541();
        System.out.println(solution.maxFreqSum("successes"));
    }

    public int maxFreqSum(String s) {
        int[] cnt = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++)
            cnt[s.charAt(i) - 'a']++;
        int max1 = 0, max2 = 0;

        for (int i = 0; i < cnt.length; i++) {
            char c = (char) ('a' + i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                 max1 = Math.max(max1, cnt[i]);
            else max2 = Math.max(max2, cnt[i]);
        }

        return max1 + max2;
    }
}
