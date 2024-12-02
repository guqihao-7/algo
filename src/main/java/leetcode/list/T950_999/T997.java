package leetcode.list.T950_999;

import java.util.Collection;

public class T997 {
    public int findJudge(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            ++inDegrees[y];
            ++outDegrees[x];
        }
        for (int i = 1; i <= n; ++i) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static void test(Object... args) {
        System.out.println("aaa");
    }

    private static void test(Collection<?> b) {
        System.out.println("bbb");
    }

    public static void main(String[] args) {
        // Object l = new ArrayList<>();
        // test(l);
        //
        // System.out.println(l instanceof Collection<?>);
        //
        // List t = new ArrayList();
        // test(t);
        int a = 'a';
        System.out.println(a);
    }
}
