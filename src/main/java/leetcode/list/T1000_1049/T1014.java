package leetcode.list.T1000_1049;

public class T1014 {
    public int maxScoreSightseeingPair(int[] values) {
        int len = values.length, ans = Integer.MIN_VALUE;
        // values[i] + values[j] + i - j -> (values[i] + i) + (values[j] - j), i < j
        int[] a1 = new int[len], a2 = new int[len];
        for (var i = 0; i < len; i++) {
            a1[i] = values[i] + i;
            a2[i] = values[i] - i;
        }
        int lMax = a1[0];
        for (var j = 1; j < len; j++) {
            ans = Math.max(lMax + a2[j], ans);
            lMax = Math.max(lMax, a1[j]);
        }
        return ans;
    }

    public static void main(String[] args) {
        T1014 solution = new T1014();
        int[] values = new int[]{8, 1, 5, 2, 6};
        System.out.println(solution.maxScoreSightseeingPair(values));
    }
}
