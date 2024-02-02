package leetcode.list.T750_799;

import leetcode.list.T200_249.T209;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class T797 {
    public static void main(String[] args) {
        T797 solution = new T797();
        int[][] graph = {
                {1,2},
                {3,},
                {3,},
                {}
        };
        System.out.println(solution.allPathsSourceTarget(graph));
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return bfs(graph);
    }

    List<List<Integer>> res = new ArrayList<>();

    List<List<Integer>> dfs(int[][] graph) {
        ArrayList<Integer> path = new ArrayList<>();
        path.add(0);    // 路径要加上起点
        traverse(graph, 0, path);
        return res;
    }

    void traverse(int[][] graph, int s, List<Integer> path) {
        if (s == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int neighbor : graph[s]) {
            path.add(neighbor);
            traverse(graph, neighbor, path);
            path.remove(path.size() - 1);
        }
    }

    List<List<Integer>> bfs(int[][] graph) {
        Queue<List<Integer>> paths = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        paths.add(path);
        while (!paths.isEmpty()) {
            int n = paths.size();
            for (int i = 0; i < n; i++) {
                List<Integer> cur = paths.poll();
                if (cur == null || cur.isEmpty()) continue;
                int last = cur.get(cur.size() - 1);
                if (last == graph.length - 1) {
                    res.add(cur);
                    continue;
                }

                int[] neighbors = graph[last];
                for (int neighbor : neighbors) {
                    ArrayList<Integer> list = new ArrayList<>(cur);
                    list.add(neighbor);
                    paths.add(list);
                }
            }
        }
        return res;
    }
}
