package luogu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P5788 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int length = s.length;
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                Integer topIdx = stack.pop();
                ans[topIdx] = i + 1;
            }
            stack.push(i);
        }
        for (int an : ans) {
            System.out.print(an + " ");
        }
    }
}
