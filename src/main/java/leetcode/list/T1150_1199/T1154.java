package leetcode.list.T1150_1199;

public class T1154 {
    public int dayOfYear(String date) {
        int[] map = new int[]{31,28,31,30,31,30,31,31,30,31,30,31,};
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 5 + 2));
        int day = Integer.parseInt(date.substring(8, 8 + 2));
        if (checkIsLeapYear(year)) {
            map[1] = 29;
        }
        int res = 0;
        for (int i = 0; i <= month - 2; i++) {
            res += map[i];
        }
        res += day;
        return res;
    }

    public boolean checkIsLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static void main(String[] args) {
        T1154 solution = new T1154();
        System.out.println(solution.dayOfYear("2004-03-01"));
    }
}
