package leetcode.list.T200_249;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class T207 {
    public static void main(String[] args) {
        T207 solution = new T207();
        System.out.println(solution.canFinish(2, new int[][]{
                {1, 0}
        }));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++)
            if (indegree[i] == 0)
                q.addLast(i);

        int count = 0;
        while (!q.isEmpty()) {
            Integer head = q.removeFirst();
            count++;
            for (Integer neighbor : graph[head])
                if (--indegree[neighbor] == 0)
                    q.addLast(neighbor);
        }

        return count == numCourses;
    }

    private List<Integer>[] buildGraph(int num, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[num];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1], to = prerequisite[0];
            graph[from].add(to);
        }
        return graph;
    }
}
