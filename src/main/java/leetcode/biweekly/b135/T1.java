package leetcode.biweekly.b135;

public class T1 {
    public String losingPlayer(int x, int y) {
        int round = 0;
        while (x - 1 >= 0 && y - 4 >= 0) {
            x -= 1;
            y -= 4;
            round++;
        }
        if (round % 2 == 1) {
            return "Alice";
        }
        else {
            return "Bob";
        }
    }

    public static void main(String[] args) {
        T1 solution = new T1();
        System.out.println(solution.losingPlayer(4, 11));
    }
}
