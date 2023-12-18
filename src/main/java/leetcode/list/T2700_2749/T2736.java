package leetcode.list.T2700_2749;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你两个长度为 n 、下标从 0 开始的整数数组 nums1 和 nums2 ，另给你一个下标从 1 开始的二维数组 queries ，其中 queries[i] = [xi, yi] 。
 * <p>
 * 对于第 i 个查询，在所有满足 nums1[j] >= xi 且 nums2[j] >= yi 的下标 j (0 <= j < n) 中，找出 nums1[j] + nums2[j] 的 最大值 ，如果不存在满足条件的 j 则返回 -1 。
 * <p>
 * 返回数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 */
public class T2736 {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums1[i];
            a[i][1] = nums2[i];
        }
        Arrays.sort(a, (x, y) -> y[0] - x[0]);

        Integer[] qid = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            qid[i] = i;
        }
        Arrays.sort(qid, (i, j) -> queries[j][0] - queries[i][0]);

        int[] ans = new int[queries.length];
        List<int[]> st = new ArrayList<>();
        int j = 0;
        for (int i : qid) {
            int x = queries[i][0], y = queries[i][1];
            for (; j < n && a[j][0] >= x; j++) { // 下面只需关心 a[j][1]
                while (!st.isEmpty() && st.get(st.size() - 1)[1] <= a[j][0] + a[j][1]) { // a[j][1] >= st.get(st.size()-1)[0]
                    st.remove(st.size() - 1);
                }
                if (st.isEmpty() || st.get(st.size() - 1)[0] < a[j][1]) {
                    st.add(new int[]{a[j][1], a[j][0] + a[j][1]});
                }
            }
            int p = lowerBound(st, y);
            ans[i] = p < st.size() ? st.get(p)[1] : -1;
        }
        return ans;
    }

    private int lowerBound(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            int mid = (left + right) >>> 1;
            if (st.get(mid)[0] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            }
            else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right;
    }

    public static void main(String[] args) {
        T2736 solution = new T2736();
        int[] nums1 = {4, 3, 1, 2};
        int[] nums2 = {2, 4, 9, 5};
        
        /*
            4 3 1 2
            2 4 9 5

            6 7 10 7
            6(0) 7(1) 7(3) 10(2)
         */
        int[][] queries = {{4, 1}, {1, 3}, {2, 5}};
        System.out.println(Arrays.toString(solution.maximumSumQueries(nums1, nums2, queries)));
    }
}
