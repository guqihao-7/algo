package leetcode.biweekly.b165;

import org.apache.commons.collections4.functors.IfClosure;

import java.util.Arrays;

public class T1 {
    public static void main(String[] args) {
        T1 solution = new T1();
        System.out.println(solution.smallestAbsent(new int[]{3, 5}));
        System.out.println(solution.smallestAbsent(new int[]{-1,1,2}));
        System.out.println(solution.smallestAbsent(new int[]{4,-1}));
    }

    public int smallestAbsent(int[] nums) {
        double average = Arrays.stream(nums)
                .average()
                .getAsDouble();
        int[] cnt = new int[205];
        int len = nums.length;
        for (int num : nums) {
            cnt[num + 100]++;
        }
        for (int i = 101; i < 205; i++) {
            if ((i - 100) > average && cnt[i] == 0) {
                return i - 100;
            }
        }
        return -1;
    }
}
