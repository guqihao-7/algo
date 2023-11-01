package poj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class P1521 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (!"END".equals(line = br.readLine())) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < line.length(); i++) {
                map.put(line.charAt(i), map.getOrDefault(line.charAt(i), 0) + 1);
            }
            Set<Character> keys = map.keySet();
            for (Character key : keys) {
                pq.add(map.get(key));
            }

            int ans = 0;
            if (pq.size() == 1)
                ans = pq.size();
            while (pq.size() > 1) {
                int a = pq.poll();
                int b = pq.poll();
                pq.add(a + b);
                ans += a + b;
            }
            pq.poll();
            System.out.printf("%d %d %.1f\n", line.length() * 8, ans, (double) line.length() * 8 / (double) ans);
        }
    }
}
