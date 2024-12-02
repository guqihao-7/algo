package leetcode.list.T700_749;

/**
 * 子数组默认连续
 * 子序列默认不连续
 */
public class T718 {
    public int findLength(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                max = Math.max(max, dfs(nums1, nums2, i, j));
            }
        }
        return max;
    }

    public int dfs(int[] nums1, int[] nums2, int i, int j) {
        if (i == -1 || j == -1) return 0;
        if (nums1[i] == nums2[j])
            return dfs(nums1, nums2, i - 1, j - 1) + 1;
        return 0;
    }

    private int max4(int a, int b, int c, int d) {
        return Math.max(Math.max(a, Math.max(b, c)), d);
    }

    public static void main(String[] args) {
        T718 solution = new T718();
        int[] nums1 = {0, 1, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 0, 1};
        int length = solution.findLength(nums1, nums2);
        System.out.println("length = " + length);

        nums1 = new int[]{0, 0, 0, 0, 0};
        nums2 = new int[]{0, 0, 0, 0, 0};
        length = solution.findLength(nums1, nums2);
        System.out.println("length = " + length);

        nums1 = new int[]{1, 2, 3, 2, 1};
        nums2 = new int[]{3, 2, 1, 4, 7};
        length = solution.findLength(nums1, nums2);
        System.out.println("length = " + length);

        nums1 = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        nums2 = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        length = solution.findLength(nums1, nums2);
        System.out.println("length = " + length);
    }
}
