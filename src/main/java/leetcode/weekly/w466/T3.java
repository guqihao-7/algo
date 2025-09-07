package leetcode.weekly.w466;

public class T3 {
    public static void main(String[] args) {
        T3 solution = new T3();
        System.out.println(solution.bowlSubarrays(new int[]{2, 5, 3, 1, 4}));
        System.out.println(solution.bowlSubarrays(new int[]{5,1,2,3,4}));
        System.out.println(solution.bowlSubarrays(new int[]{1000000000,999999999,999999998}));
    }

    public long bowlSubarrays(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int leftMax = nums[0];
        for (int i = 0; i < len; i++) {
            if (leftMax > nums[i]) {
                left[i] = 1;
            }
            else {
                leftMax = nums[i];
            }
        }

        int rightMax = nums[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            if (rightMax > nums[i]) {
                right[i] = 1;
            }
            else {
                rightMax = nums[i];
            }
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            res += left[i] * right[i];
        }
        return res;
    }
}
