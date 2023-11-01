package leetcode.list.T450_499;

import leetcode.list.T1_49.T46;

import java.util.*;

public class T496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums2.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] greater = new int[n];
        Arrays.fill(greater, -1);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int idx = stack.pop();
                greater[idx] = nums2[i];
            }

            stack.push(i);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums2[i], greater[i]);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        T496 solution = new T496();
        int[] a = {4,1,2,};
        int[] b = {1,3,4,2};
        System.out.println(Arrays.toString(solution.nextGreaterElement(a, b)));
    }
}
