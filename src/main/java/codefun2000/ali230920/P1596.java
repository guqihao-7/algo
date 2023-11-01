package codefun2000.ali230920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1596 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = br.readLine();
        String[] strings = line.split(" ");
        int n = Integer.parseInt(strings[0]), k = Integer.parseInt(strings[1]);
        line = br.readLine();
        int[] a = new int[n];
        strings = line.split(" ");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(strings[i]);

        Map<Integer, Integer> map = new HashMap<>();
        // 0 全部是 k 的倍数
        for (int i : a) map.put(i % k, map.getOrDefault(i % k, 0) + 1);
        int res = -1;
        if (map.containsKey(0) && map.get(0) >= 2)
            res = map.get(0);
        if (k % 2 == 0 && map.get(k / 2) >= 2)
            res = Math.max(res, map.get(k / 2));
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            if (map.containsKey(k - key) && map.get(k - key) != 0)
                res = Math.max(res, 2);
        }
        System.out.println(res);
    }
}
