package leetcode.biweekly.b165;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T3 {
    public static void main(String[] args) {
        T3 solution = new T3();
        // System.out.println(Arrays.deepToString(solution.generateSchedule2(3)));
        System.out.println(Arrays.deepToString(solution.generateSchedule2(8)));
        // System.out.println(Arrays.deepToString(solution.generateSchedule2(50)));
    }

    public int[][] generateSchedule2(int n) {
        int days = n * (n - 1);
        int[][] res = new int[days][2];
        for (int today = 0, team = 0; today < days; today++, team++) {
            res[today][0] = team % n;
        }
        int forward = n % 2 == 0 ? n / 2 : n / 2 + 1;
        for (int today = 0, team = forward; today < days; today++, team++) {
            res[today][1] = team % n;
        }

        for (int i = 0; i < days - 1; i++) {
            int[] preDay = res[i];
            int[] postDay = res[i + 1];
            for (int team1 : preDay) {
                for (int team2 : postDay) {
                    if (team1 == team2)
                        return new int[][]{};
                }
            }
        }

        return res;
    }

    public int[][] generateSchedule(int n) {
        int[][] allMatches = generateAllMatches(n);
        backtrack(allMatches, new boolean[allMatches.length], new ArrayList<>());
        if (res.isEmpty())
            return new int[][]{};
        return convert(res);
    }

    int[][] generateAllMatches(int n) {
        List<int[]> matches = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                matches.add(new int[]{i, j});
            }
        }

        return convert(matches);
    }

    private int[][] convert(List<int[]> list) {
        int[][] a = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++)
            a[i] = list.get(i);
        return a;
    }

    List<int[]> res = new ArrayList<>();

    void backtrack(int[][] matches, boolean[] chosen, List<int[]> path) {
        int n = matches.length;

        if (!res.isEmpty()) {
            return;
        }

        if (path.size() == n) {
            res.addAll(path);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (chosen[i]) continue;

            // 剪枝
            boolean shouldContinue = false;
            if (!path.isEmpty()) {
                int[] last = path.get(path.size() - 1);
                for (int team1 : last) {
                    for (int team2 : matches[i]) {
                        if (team1 == team2) {
                            shouldContinue = true;
                            break;
                        }
                    }
                }
            }

            if (shouldContinue) continue;;

            chosen[i] = true;
            path.add(matches[i]);
            // print(path);
            backtrack(matches, chosen, path);
            // print(path);
            path.remove(path.size() - 1);
            chosen[i] = false;
        }
    }

    void print(List<int[]> path) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int[] ints : path) {
            sb.append(Arrays.toString(ints));
        }
        sb.append("]");
        System.out.println(sb);
    }


    void print(int[][] path) {
        List<int[]> list = new ArrayList<>(Arrays.asList(path));
        print(list);
    }
}
