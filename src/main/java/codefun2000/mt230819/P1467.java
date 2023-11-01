package codefun2000.mt230819;

import java.util.Scanner;

/**
 * AC
 */
public class P1467 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt(); // 询问次数

        for (int i = 0; i < q; i++) {
            int m = scanner.nextInt(); // 订单编号上限
            long x = scanner.nextLong(); // 第 x 个订单编号
            long ans = x % m;
            ans = ans == 0 ? m : ans;
            System.out.println(ans);
        }

        scanner.close();
    }
}
