package codefun2000.bytedance230820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class T1 {
    public static int minSwapCount(int n, int k, int a1, int a2, int a3) {
        // 创建项链数组，初始状态下有3个红珠子
        char[] necklace = new char[n];
        Arrays.fill(necklace, 'W');
        necklace[a1] = 'R';
        necklace[a2] = 'R';
        necklace[a3] = 'R';


        int ans = Integer.MAX_VALUE;

        // 相邻, 到慢慢扩大距离
        for (int i = 0; i <= n - 1; i++) {
            for (int dis = 1; dis <= k; dis++) {
                char[] tmp = new char[n];
                Arrays.fill(tmp, 'W');
                int pos1 = i % n, pos2 = (i + k) % n, pos3 = (i + k * 2) % n;
                tmp[pos1] = 'R';
                tmp[pos2] = 'R';
                tmp[pos3] = 'R';

                int p1 = getMinDis(pos1, pos2, n);
                int p2 = getMinDis(pos1, pos3, n);
                int p3 = getMinDis(pos2, pos3, n);

                if (p1 <= k && p2 <= k && p3 <= k) {
                    // 比较移动次数

                }
            }
        }
        return 0;
    }

    static int check(char[] a, char[] orig) {
        return 0;
    }

    static int getMinDis(int i, int j, int n) {
        if (i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }

        int d1 = (n - 1 - j) + i;
        int d2 = j - i;
        return Math.min(d1, d2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();  // 询问次数
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();  // 项链上珠子的总数
            int k = scanner.nextInt();  // 任意两个红珠子的最小距离
            int a1 = scanner.nextInt(); // 第一个红珠子的位置
            int a2 = scanner.nextInt(); // 第二个红珠子的位置
            int a3 = scanner.nextInt(); // 第三个红珠子的位置

            int minSwaps = minSwapCount(n, k, a1, a2, a3);
            System.out.println(minSwaps);
        }
    }
}
