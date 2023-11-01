package codefun2000.mt230819;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * AC
 */
public class P1468 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = scanner.nextInt();
        BigInteger sum = BigInteger.valueOf(0);
        for (int num : arr) sum = sum.add(BigInteger.valueOf(num));
        BigInteger maxProduct = BigInteger.valueOf(Integer.MIN_VALUE);
        int idx = -1;
        for (int i = 0; i < n - 1; i++) {
            BigInteger b1 = BigInteger.valueOf(arr[i]);
            BigInteger b2 = BigInteger.valueOf(arr[i + 1]);
            BigInteger p = b1.multiply(b2);
            BigInteger s = b1.add(b2);
            if (p.compareTo(s) > 0) {
                if (p.compareTo(maxProduct) > 0) {
                    maxProduct = p;
                    idx = i;
                }
            }
        }

        if (idx == -1)
            System.out.println(sum);
        else {
            BigInteger b1 = BigInteger.valueOf(arr[idx]);
            BigInteger b2 = BigInteger.valueOf(arr[idx + 1]);
            sum = sum.subtract(b1).subtract(b2);
            sum = sum.add(b1.multiply(b2));
            System.out.println(sum);
        }
    }
}
