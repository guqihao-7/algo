package leetcode.list.T200_249;

public class T204 {
    public static void main(String[] args) {
        T204 solution = new T204();
        System.out.println(solution.countPrimes(10));
    }

    public int countPrimes(int n) {
        byte[] a = new byte[n];
        for (int i = 2; i < n; i++) {
            if (a[i] != 0) continue;
            for (int j = 2; i * j < n; j++) {
                a[i * j] = 1;
            }
        }
        int ans = 0;
        for (int i = 2; i < n; i++)
            if (a[i] == 0)
                ans++;
        return ans;
    }
}
