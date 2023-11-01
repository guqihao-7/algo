package leetcode.list.T50_99;

public class T69 {
    public static void main(String[] args) {
        T69 solution = new T69();
        System.out.println(solution.mySqrt(8));
    }
    public int mySqrt(int x) {
        return lower_bound(x);
    }

    int lower_bound(int x) {
        int l = 1, r = x;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (m < x / m) l = m + 1;
            else if (m == x / m) return m;
            else r = m - 1;
        }
        return l - 1;
    }
}
