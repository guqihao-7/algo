package codefun2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P1447 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = Integer.parseInt(line);

        line = br.readLine();
        Deque<Integer> stack = new ArrayDeque<>();
        String[] strings = line.trim().split(" ");
        for (int i = 0; i < n; i++) {
            stack.push(Integer.parseInt(strings[i]));
        }

        backtrack(stack);
        StringBuilder sb = new StringBuilder();
        for (int an : ans) {
            sb.append(an).append(" ");
        }
        System.out.println(sb.toString().trim());

        br.close();
    }

    static int[] ans = new int[10];

    static void backtrack(Deque<Integer> stack) {
        if (stack.size() == 1) {
            int tmp = stack.peek();
            ans[tmp] = (ans[tmp] % 1000000007 + 1) % 1000000007;
            return;
        }

        int a = stack.pop(), b = stack.pop();

        int sum = a + b;
        stack.push(sum % 10);
        backtrack(stack);
        stack.pop();

        int product = a * b;
        stack.push(product % 10);
        backtrack(stack);
        stack.pop();

        stack.push(b);
        stack.push(a);
    }
}
