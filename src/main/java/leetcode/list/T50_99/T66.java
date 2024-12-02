package leetcode.list.T50_99;

import java.util.Arrays;

public class T66 {
    public int[] plusOne(int[] digits) {
        int len = digits.length;

        // 其实这个循环是在模拟进位
        // 如果当前位是 9 还需要加一，就往前进一位尝试加一
        // 一直进位到最头，跳出循环表示全是 9
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
        }

        int[] ans = new int[len + 1];
        ans[0] = 1;
        return ans;
    }

    public static void main(String[] args) {
        T66 solution = new T66();
        int[] digits = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(solution.plusOne(digits)));
    }
}
