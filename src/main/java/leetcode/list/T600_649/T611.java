package leetcode.list.T600_649;

import java.util.Arrays;

public class T611 {
    public static void main(String[] args) {
        T611 solution = new T611();
        System.out.println(solution.triangleNumber(new int[]{2, 2, 3, 4}));
        solution.triangleNumber(new int[]{4, 2, 3, 4});
    }

    public int triangleNumber(int[] nums) {
        int res = 0, len = nums.length;
        if (len < 3) return 0;
        Arrays.sort(nums);
        int i = 0;
        while (i < len && nums[i] == 0) i++;
        if (i >= len) return 0;
        for (; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] > nums[k]) res++;
                    else break;
                }
            }
        }
        return res;
    }

}
