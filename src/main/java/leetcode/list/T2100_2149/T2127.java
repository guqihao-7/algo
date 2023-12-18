package leetcode.list.T2100_2149;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class T2127 {
    public static void main(String[] args) {
        T2127 solution = new T2127();
        /*
            2 2 1 2
            0 -> 2
            1 -> 2
            2 -> 1
            3 -> 2
         */
        int[] a = {2,2,1,2};
        System.out.println(solution.maximumInvitations(a));
    }

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        List<Integer>[] graph = new ArrayList[n];
        int[] in_degree = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            graph[i].add(favorite[i]);
            in_degree[favorite[i]]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < graph.length; i++) {
            if (in_degree[i] == 0) {
                q.add(i);
            }
        }

        int ans = 0;

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            ans++;
            List<Integer> neighbors = graph[cur];
            for (Integer neighborId : neighbors) {
                if (--in_degree[neighborId] == 0) {
                    q.add(neighborId);
                }
            }
        }

        return ans;
    }
}
