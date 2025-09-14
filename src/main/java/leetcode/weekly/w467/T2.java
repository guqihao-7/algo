package leetcode.weekly.w467;

import java.util.*;

public class T2 {
    public static void main(String[] args) {
        T2 solution = new T2();
        System.out.println(Arrays.toString(solution.maxKDistinct(new int[]{84, 93, 100, 77, 90}, 3)));
        System.out.println(Arrays.toString(solution.maxKDistinct(new int[]{84, 93, 100, 77, 93}, 3)));
        System.out.println(Arrays.toString(solution.maxKDistinct(new int[]{1, 1, 1, 2, 2, 2,}, 6)));
    }

    public int[] maxKDistinct(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                res.add(num);
            }

            if (res.size() >= k)
                break;
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);
        return ans;
    }
}
