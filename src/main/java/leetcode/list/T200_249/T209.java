package leetcode.list.T200_249;

public class T209 {
    public static void main(String[] args) {
        T209 solution = new T209();
        int[] a = {2, 3, 1, 2, 4, 3};
        System.out.println(solution.minSubArrayLen(7, a));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0, sum = 0, sz = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r++];
            while (sum >= target) {
                sz = Math.min(r - l, sz);
                sum -= nums[l++];
            }
        }
        return sz == Integer.MAX_VALUE ? 0 : sz;
    }
}
