package leetcode.list.T2200_2249;

import leetcode.list.T800_849.T823;

public class T2240 {
    public static void main(String[] args) {
        T2240 solution = new T2240();
        int tot = 20, c1 = 10, c2 = 5;
        System.out.println(solution.waysToBuyPensPencils(tot, c1, c2));
    }

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (cost1 < cost2) return waysToBuyPensPencils(total, cost2, cost1);
        long res = 0, cnt = 0;
        while (cnt * cost1 <= total) {
            res += (total - cnt * cost1) / cost2 + 1;
            cnt++;
        }
        return res;
    }
}
