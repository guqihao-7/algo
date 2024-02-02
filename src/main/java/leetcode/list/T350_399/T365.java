package leetcode.list.T350_399;

import java.util.*;

/**
 * 1号桶和2号桶的最大容量分别是 a 和 b
 * 某一刻的容量分别是 x y
 * 从 x, y 凑到 t 的方法
 * 1. 倒空 1，此时 (x, y) -> (0, y)
 * 2. 倒空 2，此时 (x, y) -> (x, 0)
 * 3. 倒满 1，此时 (x, y) -> (a, y)
 * 4. 倒满 2，此时 (x, y) -> (x, b)
 * 5. 1 倒入 2，分两种情况，第一种 x+y<=b，则状态为 (0, x + y)；若 x+y>b，则状态为 (x+y-b, b)
 * 6. 2 倒入 1，分两种情况，第一种 x+y<=a，则状态为 (x + y, 0)；若 x+y>a，则状态为 (a, x+y-a)
 */
public class T365 {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity + jug2Capacity < targetCapacity) {
            return false;
        }
        int gcd = gcd(jug1Capacity, jug2Capacity);
        return targetCapacity % gcd == 0;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 有向有环无权图，从 0 0 走到 a b 的路径
    boolean bfs(int a, int b, int t) {
        Pair[][] pairs = new Pair[a + 1][b + 1];
        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j < pairs[i].length; j++) {
                pairs[i][j] = new Pair(i, j);
            }
        }
        boolean[][] visit = new boolean[a + 1][b + 1];
        Deque<Pair> q = new ArrayDeque<>();
        q.addLast(pairs[0][0]);
        while (!q.isEmpty()) {
            Pair head = q.removeFirst();
            int x = head.x, y = head.y;

            if (x == t || y == t || x + y == t) {
                return true;
            }

            if (!visit[0][y]) {
                visit[0][y] = true;
                q.addLast(pairs[0][y]);
            }
            if (!visit[x][0]) {
                visit[x][0] = true;
                q.addLast(pairs[x][0]);
            }
            if (!visit[a][y]) {
                visit[a][y] = true;
                q.addLast(pairs[a][y]);
            }
            if (!visit[x][b]) {
                visit[x][b] = true;
                q.addLast(pairs[x][b]);
            }

            if (x + y <= b) {
                if (!visit[0][x + y]) {
                    visit[0][x + y] = true;
                    q.addLast(pairs[0][x + y]);
                }
            }
            else {
                if (!visit[x + y - b][b]) {
                    visit[x + y - b][b] = true;
                    q.add(pairs[x + y - b][b]);
                }
            }

            if (x + y <= a) {
                if (!visit[x + y][0]) {
                    visit[x + y][0] = true;
                    q.addLast(pairs[x + y][0]);
                }
            }
            else {
                if (!visit[a][x + y - a]) {
                    visit[a][x + y - a] = true;
                    q.addLast(pairs[a][x + y - a]);
                }
            }
        }
        return false;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Pair pair = (Pair) object;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    boolean[][] visit;

    boolean dfs(int a, int b, int t, int x, int y) {
        if (x < 0 || y < 0 || x > a || y > b) return false;
        if (visit[x][y]) return false;
        visit[x][y] = true;
        if (x == t || y == t || x + y == t) {
            return true;
        }

        boolean f1, f2, f3, f4, f5, f6;

        f1 = dfs(a, b, t, 0, y);

        f2 = dfs(a, b, t, x, 0);

        f3 = dfs(a, b, t, a, y);

        f4 = dfs(a, b, t, x, b);

        if (x + y <= b) {
            f5 = dfs(a, b, t, 0, x + y);
        }
        else {
            f5 = dfs(a, b, t, 0, y);
        }

        if (x + y <= a) {
            f6 = dfs(a, b, t, x + y, 0);
        }
        else {
            f6 = dfs(a, b, t, a, x + y - a);
        }
        return f1 || f2 || f3 || f4 || f5 || f6;
    }

    public static void main(String[] args) {
        int j1 = 3, j2 = 5, t = 4;
        T365 solution = new T365();
        System.out.println(solution.canMeasureWater(j1, j2, t));

        System.out.println();
        /*
            3 5 -> 4
            5 满
            5倒入3，3满，5剩2
            倒空3
            5倒入3，3有2
            5倒满
            5倒入3，3满，5中剩余4
         */
        solution.bfs(j1, j2, t);
        solution.visit = new boolean[j1 + 1][j2 + 1];
        System.out.println(solution.dfs(j1, j2, t, 0, 0));
    }
}
