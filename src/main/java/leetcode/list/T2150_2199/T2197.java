package leetcode.list.T2150_2199;

import java.util.*;

public class T2197 {
    public static void main(String[] args) {
        T2197 solution = new T2197();
        // System.out.println(solution.replaceNonCoprimes(new int[]{6, 4, 3, 2, 7, 6, 2}));
        // System.out.println(solution.replaceNonCoprimes(new int[]{2, 2, 1, 1, 3, 3, 3}));
        System.out.println(solution.replaceNonCoprimes(new int[]{31, 97561, 97561, 97561, 97561, 97561, 97561, 97561, 97561}));
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            stack.push(num);
            while (stack.size() >= 2) {
                Integer top1 = stack.pop();
                Integer top2 = stack.pop();
                int gcd = gcd(top1, top2);
                if (gcd > 1) {
                    int lcm = lcm(top1, top2, gcd);
                    stack.push(lcm);
                }
                else {
                    stack.push(top2);
                    stack.push(top1);
                    break;
                }
            }
        }

        List<Integer> res = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) res.add(stack.pop());
        Collections.reverse(res);
        return res;
    }

    public int lcm(int a, int b, int gcd) {
        return (int) ((long) a * (long) b / (long) gcd);
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }
}
