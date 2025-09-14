package leetcode.biweekly.b165;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class T2 {
    public static void main(String[] args) {
        T2 solution = new T2();
        // System.out.println(solution.minArrivalsToDiscard(new int[]{1, 2, 1, 3, 1}, 4, 2));
        System.out.println(solution.minArrivalsToDiscard(new int[]{1, 2, 3, 3, 3, 4}, 3, 2));
        // System.out.println(solution.minArrivalsToDiscard(new int[]{10, 4, 3, 6, 4, 5, 6, 1, 4}, 7, 1));
        System.out.println(solution.minArrivalsToDiscard(new int[]{7, 3, 9, 9, 7, 3, 5, 9, 7, 2, 6, 10, 9, 7, 9, 1, 3, 6, 2, 4, 6, 2, 6, 8, 4, 8, 2, 7, 5, 6}, 10, 1));
    }

    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int res = 0, len = arrivals.length, l = 0, r = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < w) {
            if (arrivals[r] > 0) {
                map.put(arrivals[r], map.getOrDefault(arrivals[r], 0) + 1);

                if (map.get(arrivals[r]) > m) {
                    map.put(arrivals[r], map.get(arrivals[r]) - 1);
                    arrivals[r] = 0;
                    res++;
                }

                r++;
            }
        }

        r--;

        while (r < len - 1) {
            r++;
            if (arrivals[r] > 0) {
                map.put(arrivals[r], map.getOrDefault(arrivals[r], 0) + 1);
            }

            if (arrivals[l] > 0) {
                map.put(arrivals[l], map.getOrDefault(arrivals[l], 0) - 1);
            }

            l++;

            if (arrivals[r] > 0 && map.get(arrivals[r]) > m) {
                res++;
                map.put(arrivals[r], map.get(arrivals[r]) - 1);
                arrivals[r] = 0;
            }
        }

        return res;
    }
}
