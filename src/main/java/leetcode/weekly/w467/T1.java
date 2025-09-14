package leetcode.weekly.w467;

public class T1 {
    public static void main(String[] args) {
        T1 solution = new T1();
    }

    public int earliestTime(int[][] tasks) {
        int min = Integer.MAX_VALUE;
        for (int[] task : tasks) {
            min = Math.min(min, task[0] + task[1]);
        }
        return min;
    }
}
