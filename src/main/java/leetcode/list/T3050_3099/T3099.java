package leetcode.list.T3050_3099;

public class T3099 {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int tmp = x, sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return tmp % sum == 0 ? sum : -1;
    }

    public static void main(String[] args) {
        T3099 solution = new T3099();
        System.out.println(solution.sumOfTheDigitsOfHarshadNumber(23));
    }
}
