package nowcoder.acm116337;

import java.math.BigInteger;
import java.util.Scanner;

public class T1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        int n = Integer.parseInt(s);

        BigInteger res = BigInteger.ZERO;

        for (int i = 1; i <= n; i++) {
            BigInteger v = v(i);
            BigInteger w = w(i, v);
            BigInteger x = x(i, w);
            BigInteger y = y(i, x);
            BigInteger z = z(i, y);
            res = res.add(z);
        }
        
        res = res.mod(mod3);

        System.out.println(res);

        scanner.close();
    }

    static BigInteger mod1 = BigInteger.valueOf(998_244_353);
    static BigInteger mod2 = BigInteger.valueOf(1_000_000_007);
    static BigInteger mod3 = BigInteger.valueOf(4_294_967_296L);
    static BigInteger xor1 = BigInteger.valueOf(1_777_777_777);


    static BigInteger v(int i) {
        return (((BigInteger.valueOf(114514).pow(i).mod(mod1)).multiply((BigInteger.valueOf(1919810).pow(i).mod(mod2)))).mod(mod3)).xor(xor1);
    }

    static BigInteger w(int i, BigInteger v) {
        return (BigInteger.valueOf(2_333_333_333L).add(v)).mod(mod3);
    }

    static BigInteger x(int i, BigInteger w) {
        return (w.xor(w.shiftRight(15))).multiply(BigInteger.valueOf(1_145_141_145L)).mod(mod3);
    }

    static BigInteger y(int i, BigInteger x) {
        return ((x.xor(x.shiftRight(14))).multiply(BigInteger.valueOf(1_919_810_191))).mod(mod3);
    }

    static BigInteger z(int i, BigInteger y) {
        return y.xor(y.shiftRight(16));
    }
}
