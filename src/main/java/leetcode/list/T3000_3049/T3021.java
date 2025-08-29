package leetcode.list.T3000_3049;

public class T3021 {
    public static void main(String[] args) {
        T3021 solution = new T3021();
        System.out.println(solution.flowerGame(58280, 69389));
        System.out.println(solution.flowerGame2(58280, 69389));
        System.out.println(solution.flowerGame(3, 2));
        System.out.println(solution.flowerGame2(3, 2));
    }

    public long flowerGame(int n, int m) {
        long res = 0;
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= m; y++) {
                if ((x + y) % 2 != 0) {
                    // System.out.println("(" + x + ", " + y + ")");
                    res++;
                }
            }
        }
        return res;
    }

    public long flowerGame2(int n, int m) {
        // 计算 1 到 n 之间奇数和偶数的数量
        long odd_n = (n + 1) / 2;
        long even_n = n / 2;

        // 计算 1 到 m 之间奇数和偶数的数量
        long odd_m = (m + 1) / 2;
        long even_m = m / 2;

        // 满足 (x + y) 为奇数的两种情况：
        // 1. x 为奇数，y 为偶数
        // 2. x 为偶数，y 为奇数
        return (odd_n * even_m) + (even_n * odd_m);
    }
}
