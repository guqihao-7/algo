package leetcode.list.T2700_2749;

import java.util.Arrays;

public class T2706 {
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        int sum;
        return (sum = prices[0] + prices[1]) < money ? money - sum : money;
    }
}
