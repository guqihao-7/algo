package leetcode.weekly.w467;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T3 {
    public static void main(String[] args) {
        T3 solution = new T3();
        System.out.println(Arrays.toString(solution.subsequenceSumAfterCapping(new int[]{4, 3, 2, 4}, 5)));
        System.out.println(Arrays.toString(solution.subsequenceSumAfterCapping(new int[]{1, 2, 3, 4, 5}, 3)));
    }

    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;
        boolean[] answer = new boolean[n];

        for (int x = 1; x <= n; x++) {
            boolean[] dp = new boolean[k + 1];
            dp[0] = true;

            for (int value : nums) {
                int num = Math.min(value, x);
                if (num == 0) continue;
                for (int j = k; j >= num; j--) {
                    dp[j] |= dp[j - num];
                }
            }

            answer[x - 1] = dp[k];
        }

        return answer;
    }

    public boolean[] subsequenceSumAfterCapping2(int[] nums, int k) {
        int n = nums.length;
        boolean[] answer = new boolean[n];

        List<Integer> smallList = new ArrayList<>();
        int tmp = 0;
        for (int val : nums) {
            if (val > n) tmp++;
            else if (val > 0) smallList.add(val);
        }
        int m = smallList.size();
        int[] small = new int[m];
        for (int i = 0; i < m; i++) small[i] = smallList.get(i);
        Arrays.sort(small);

        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        int pos = 0;

        for (int x = 1; x <= n; x++) {
            while (pos < m && small[pos] <= x) {
                int num = small[pos];
                for (int j = k; j >= num; j--) {
                    dp[j] |= dp[j - num];
                }
                pos++;
            }

            int cnt1 = (m - pos) + tmp;
            int max = Math.min(cnt1, k / x);
            boolean can = false;
            for (int t = 0; t <= max; t++) {
                int s = k - t * x;
                if (s >= 0 && dp[s]) {
                    can = true;
                    break;
                }
            }
            answer[x - 1] = can;
        }

        return answer;
    }
}
