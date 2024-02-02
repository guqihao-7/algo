package leetcode.list.T150_199;

public class T162 {
    public int findPeakElement(int[] nums) {
        int n = nums.length, l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            /*
                前提：nums[m] != nums[m+1]

                则
                    若 nums[m] < nums[m+1]
                        说明峰顶一定在 m 左侧
                    否则
                        峰顶在 m 或 m 右侧(m位置可能是峰顶也可能不是)

                 最后循环结束的地方就是峰值
            */
            if (m >= 0 && (m + 1) >= 0 &&
                    m <= (n - 1) && (m + 1) <= n - 1 &&
                    nums[m] < nums[m + 1]) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        T162 solution = new T162();
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(solution.findPeakElement(nums));
    }
}
