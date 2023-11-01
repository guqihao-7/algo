package leetcode.list.T2350_2399;

import java.util.Arrays;

public class T2376 {
    char s[];
    int memo[][];

    public int countSpecialNumbers(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        memo = new int[m][1 << 10];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], -1); // -1 表示没有计算过
        return f(0, 0, true, false);
    }

    int f(int i, int mask, boolean limit, boolean lead) {
        if (i == s.length)
            return lead ? 1 : 0; // lead 为 true 表示得到了一个合法数字
        if (!limit && lead && memo[i][mask] != -1)
            return memo[i][mask];
        int res = 0;
        if (!lead) // 可以跳过当前数位
            res = f(i + 1, mask, false, false);
        int up = limit ? s[i] - '0' : 9; // 如果前面填的数字都和 n 的一样，那么这一位至多填数字 s[i]（否则就超过 n 啦）
        for (int d = lead ? 0 : 1; d <= up; ++d) // 枚举要填入的数字 d
            if ((mask >> d & 1) == 0) // d 不在 mask 中
                res += f(i + 1, mask | (1 << d), limit && d == up, true);
        if (!limit && lead)
            memo[i][mask] = res;
        return res;
    }

    boolean check(int i) {
        boolean[] used = new boolean[10];
        while (i != 0) {
            int cur = i % 10;
            if (used[cur]) return false;
            used[cur] = true;
            i /= 10;
        }
        return true;
    }
}
