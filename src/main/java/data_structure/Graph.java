package ds;

import java.util.*;

@SuppressWarnings("all")
public class Graph {
    // 顶点数量
    private int v;
    // 用于存图, 用链表保存后继(to), 可以换成红黑树、跳表等来保存后继
    private final List<Integer>[] graph;
    // 检测环用的, 如果确定图中不存在环, 此数组可以省
    boolean[] visited;
    // 判断是否有环, 默认无环
    private boolean hasCycle = false;
    // 是否为有向图, true 为有向图, false 为无向图
    private boolean direction;

    public Graph(int v, boolean direction, boolean needAllPaths) {
        this.v = v;
        this.direction = direction;
        this.needAllPaths = needAllPaths;
        // 在这里决定用 ArrayList 还是 LinkedList
        graph = new ArrayList[v];
        visited = new boolean[v];
        indegree = new int[v];
        outdegree = new int[v];
        for (int i = 0; i < v; i++)
            graph[i] = new ArrayList<>();
    }

    public void addEdge(int from, int to) {
        // 无向图需要存储两条边, 有向图只需要存储一条边
        graph[from].add(to);
        indegree[to]++;
        outdegree[from]++;
        if (!direction) {
            graph[to].add(from);
            indegree[from]++;
            outdegree[to]++;
        }
    }

    // DFS 保存从起点到终点的路径
    LinkedList<Integer> path = new LinkedList<>();
    // DFS 保存所有从起点到终点的路径
    List<List<Integer>> allPaths = new ArrayList<>();
    private boolean needAllPaths = false;
    // 保存在当前递归路径上的节点, 可以用于 DFS, 和 path 重复, 主要是用来快速判断是否存在的
    HashSet<Integer> onPath = new HashSet<>();

    public void dfs(int start, int to) {
        // 发现环
        if (onPath.contains(start))
            hasCycle = true;
        if (visited[start] || hasCycle)
            return;

        path.addLast(start);
        visited[start] = true;
        onPath.add(start);

        // 找到终点
        if (start == to) {
            // 当到达目标点, allPaths 保存所有从 start 到 to 的路径
            // path 是最后一条搜到的 start 到 to 的路径
            if (needAllPaths)
                allPaths.add(new LinkedList<>(path));
            Integer last = path.removeLast();
            onPath.remove(last);
            return;
        }

        // 前序位置
        // 获取所有后继
        List<Integer> succeed = graph[start];
        int size = succeed.size();
        // 递归, 如果 TLE, 可以考虑换成 ArrayList
        for (int i = 0; i < size; i++)
            dfs(succeed.get(i), to);

        // 后续位置
        if (needPostOrder)
            postOrder.add(start);
        path.removeLast();
        onPath.remove(start);
    }

    /**
     * 判断是否有环, DFS 版本
     *
     * @return true 为有环, false 为无环
     */
    public boolean hasCycleByDFS() {
        for (int i = 0; i < v; i++)
            dfs(i, -1);
        return hasCycle;
    }

    // 记录后序遍历结果, 主要是用于 DFS 版本的拓扑排序
    List<Integer> postOrder = new ArrayList<>();
    private boolean needPostOrder = false;

    /**
     * 拓扑排序, DFS 版本
     *
     * @return 一个合理的顺序
     */
    public int[] topologicalSortingByDFS() {
        needPostOrder = true;
        if (hasCycleByDFS()) return new int[]{};
        Collections.reverse(postOrder);
        int[] res = new int[v];
        for (int i = 0; i < v; i++)
            res[i] = postOrder.get(i);
        return res;
    }

    // 入度数组, 用于 BFS 环检测和拓扑排序
    int[] indegree;
    int[] outdegree;

    /**
     * 判断是否有环, BFS 版本
     * 1->2 表示 2 依赖 1, 也就是先进行 1 才能进行 2
     *
     * @return true 为有环, false 为无环
     */
    public boolean hasCycleByBFS() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < v; i++)
            if (indegree[i] == 0)
                // 入度为 0 表示没有依赖的节点
                // 可以作为拓扑排序的起点
                q.offer(i);
        // 记录遍历的节点个数
        int cnt = 0;
        // 开始 BFS
        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0)
                    // next 入度为 0 表示 next 以来的节点都已被遍历
                    q.offer(next);
            }
        }
        // 所有节点都被遍历过则说明不存在环
        return cnt == v;
    }

    /**
     * 拓扑排序 BFS
     *
     * @return 返回一个合适的顺序
     */
    public int[] topologicalSortingByBFS() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < v; i++)
            if (indegree[i] == 0)
                q.offer(i);
        int[] res = new int[v];
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            res[cnt] = cur;
            cnt++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0)
                    q.offer(next);
            }
        }
        // 存在环
        if (cnt != v) return new int[]{};
        return res;
    }

    /**
     * @return 返回成环的点
     */
    public int[] ringPoint() {
        int[] ints = topologicalSortingByBFS();
        if (ints.length != 0)
            return new int[]{};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < indegree.length; i++)
            // 入度没有被消完
            if (indegree[i] != 0)
                list.add(i);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }

    /**
     * BFS, 两点间最短路径, 如果需要多次 BFS, 记得要重新 new 一个 visit 数组
     *
     * @param start 开始顶点
     * @param to    终点, -1 表示从起点全部遍历一遍
     * @return 从 start 开始要走多少步才能到达 to
     */
    public int bfs(int start, int to) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        int step = 0;
        boolean find = false;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                int cur = q.poll();
                visited[cur] = true;
                List<Integer> succeed = graph[cur];
                for (Integer next : succeed) {
                    if (next == to) {
                        find = true;
                        break;
                    }
                    if (!visited[next])
                        q.offer(next);
                }

                size--;
                if (size == 0)
                    step++;
            }
            if (find) break;
        }
        return find ? step + 1 : step;
    }
}
