package leetcode.list.T3100_3149;

public class T3133 {
    public long minEnd(int n, int x) {
        int highestIdxOfOne = idxOfHighestOne(x);
        int numBits = numBits(x, n);

        if (numBits > highestIdxOfOne) {

        }
        else {

        }

        return 0;
    }

    double log(int basement, int n) {
        return Math.log(n) / Math.log(basement);
    }

    int numBits(int x, int n) {
        int cnt = cntOfOne(x);
        int idx = idxOfHighestOne(x);

        if (Math.pow(2, idx - cnt) > n) {
            return idx;
        }

        while (Math.pow(2, idx - cnt) <= n) {
            idx++;
        }
        return idx;
    }

    int idxOfHighestOne(int x) {
        int res = 0;
        while (x != 0) {
            res++;
            x >>= 1;
        }
        return res;
    }

    int cntOfOne(int x) {
        int res = 0;
        while (x != 0) {
            if ((x & 1) == 1) {
                res++;
            }
            x >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        T3133 solution = new T3133();
        System.out.println(solution.cntOfOne(4));
        System.out.println(solution.idxOfHighestOne(4));
        System.out.println(solution.numBits(4, 3));
        System.out.println(solution.log(2, 8));
        System.out.println(solution.log(2, 7));
    }
}
