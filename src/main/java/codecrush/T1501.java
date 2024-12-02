package codecrush;

public class T1501 {
    public static void main(String[] args) {
        T1501 solution = new T1501();
        int difference = solution.getDifference(124634);
        System.out.println("difference = " + difference);
    }

    public int getDifference(int n) {
        String s = Integer.valueOf(n).toString();
        char[] chars = s.toCharArray();

        int a = 0, b = 0;
        for (char c : chars) {
            int i = c - '0';
            if (i % 2 != 0)
                a = a * 10 + i;
            else
                b = b * 10 + i;
        }

        return Math.abs(a - b);
    }
}
