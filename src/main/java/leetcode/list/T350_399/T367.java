package leetcode.list.T350_399;

public class T367 {
    public boolean isPerfectSquare(int num) {
        return lower_bound(num);
    }

    boolean lower_bound(int x) {
        int l = 1, r = x;
        while (l <= r) {
            int m = l + (r - l) / 2;
            long p = (long) m * m;
            if (p < x) l = m + 1;
            else if (p == x) return true;
            else r = m - 1;
        }
        return false;
    }
}
