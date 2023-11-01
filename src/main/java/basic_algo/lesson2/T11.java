package basic_algo.lesson2;

public class T11 {
    public int maxArea(int[] height) {
        int n = height.length, l = 0, r = n - 1;
        int ans = Integer.MIN_VALUE;
        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            // 这里是一个贪心策略，本身自己高度低，贪心往前走可能会遇到更高的
            if (height[l] < height[r]) l++;
            else r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        T11 solution = new T11();
        int[] a = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        a = new int[]{1, 1};
        System.out.println(solution.maxArea(a));
    }
}
