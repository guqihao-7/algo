package leetcode.list.T300_349;


import java.util.*;

public class T310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        return planB(n, graph);
    }

    // 拓扑排序的做法，因为是无环图，所以出度和入度相同，初始时最小是 1
    // 这个思路就是拓扑排序不断缩圈，最后留下的就是答案
    public List<Integer> planB(int n, List<Integer>[] graph) {
        int[] indegree = new int[n];
        for (int i = 0; i < graph.length; i++) {
            indegree[i] = graph[i].size();
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 1) {
                q.addLast(i);
            }
        }

        List<Integer> res = null;

        while (!q.isEmpty()) {
            res = new ArrayList<>();
            int size = q.size();
            while (size-- > 0) {
                Integer head = q.removeFirst();
                res.add(head);
                indegree[head]--;
                List<Integer> neighbors = graph[head];
                for (Integer neighbor : neighbors) {
                    if (--indegree[neighbor] == 1) {
                        q.addLast(neighbor);
                    }
                }
            }
        }

        return res;
    }

    // 最直观的做法会 time out
    public List<Integer> planA(int n, List<Integer>[] graph) {
        List<Integer> res = new ArrayList<>();
        int[] cnt = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            cnt[i] = bfs(i, graph);
            min = Math.min(cnt[i], min);
        }

        for (int i = 0; i < n; i++) {
            if (cnt[i] == min) {
                res.add(i);
            }
        }
        return res;
    }

    public int bfs(int start, List<Integer>[] graph) {
        Deque<Integer> q = new ArrayDeque<>();
        int level = 0;
        HashSet<Integer> visit = new HashSet<>();
        q.addLast(start);
        visit.add(start);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Integer head = q.removeFirst();
                List<Integer> neighbors = graph[head];
                for (Integer neighbor : neighbors) {
                    if (visit.contains(neighbor)) continue;
                    visit.add(neighbor);
                    q.addLast(neighbor);
                }
            }
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][]{
                {3, 0},
                {3, 1},
                {2, 3},
                {3, 4},
                {5, 4}
        };

        n = 1;
        edges = new int[0][0];

        T310 solution = new T310();
        List<Integer> minHeightTrees = solution.findMinHeightTrees(n, edges);
        System.out.println(minHeightTrees);
    }
}
