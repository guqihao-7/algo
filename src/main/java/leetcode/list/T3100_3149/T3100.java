package leetcode.list.T3100_3149;


public class T3100 {
    public int maxBottlesDrunk(int fullBottles, int numExchange) {
        int emptyBottles = 0, bottlesDrunk = 0;
        while (true) {
            emptyBottles += fullBottles;
            bottlesDrunk += fullBottles;
            fullBottles = 0;

            if (emptyBottles < numExchange) {
                return bottlesDrunk;
            }

            while (emptyBottles >= numExchange) {
                emptyBottles -= numExchange;
                numExchange += 1;
                fullBottles += 1;
            }
        }
    }

    public static void main(String[] args) {
        T3100 solution = new T3100();
        int a = solution.maxBottlesDrunk(10, 3);
        System.out.println(a);
    }
}
