package leetcode.list.T700_749;

import java.util.*;

public class T743 {
    static class Node {
        public int id;
        public int dis; //  记录起点到 id 的距离

        public Node(int id, int dis) {
            this.id = id;
            this.dis = dis;
        }
    }

    static class Edge {
        // from 不用存储, edge 数组的下标即可表示
        public int to;
        public int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }


    @SuppressWarnings("unchecked")
    public int networkDelayTime(int[][] times, int n, int k) {
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : times)
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
        int[] dist = dijkstra(k, graph);
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            res = Math.max(res, dist[i]);
        }
        return res;
    }

    /**
     * dijkstra 求单源最短路径
     *
     * @param s 起点
     * @return 起点到其他所有点的最短路径
     */
    int[] dijkstra(int s, List<Edge>[] graph) {
        int V = graph.length;
        int[] dist;                // dist[i] 为从 s 到点 i 的最短路径长度
        dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;                             // 起点到自己的距离为 0
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dis));
        pq.add(new Node(s, dist[s]));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dis > dist[cur.id])
                continue;

            List<Edge> edges = graph[cur.id];
            for (Edge edge : edges) {
                int disToNeighbor = dist[cur.id] + edge.w;
                if (dist[edge.to] > disToNeighbor) {
                    dist[edge.to] = disToNeighbor;
                    pq.add(new Node(edge.to, disToNeighbor));
                }
            }
        }
        return dist;
    }
}
