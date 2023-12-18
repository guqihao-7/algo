package leetcode.list.T500_549;

import leetcode.list.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class T543 {
    public int diameterOfBinaryTree(TreeNode root) {
        return 0;
    }

    void bfs(TreeNode u) {
        int cnt = cnt(u);
        e = new ArrayList[cnt];
        dist = new int[cnt];

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addFirst(u);
        int dis = 0;
        TreeNode last;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                last = q.getLast();
                if (last.left != null) {
                    e[last.val].add(new Edge(last.left.val, 1));
                    e[last.left.val].add(new Edge(last.val, 1));
                    q.addFirst(last.left);
                }
                if (last.right != null) {
                    e[last.val].add(new Edge(last.left.val, 1));
                    e[last.right.val].add(new Edge(last.val, 1));
                    q.addFirst(last.right);
                }
            }
        }


    }

    int[] dist;
    List<Edge>[] e;

    void dfs(int u, int father, int d) {
        dist[u] = d;
        for (int i = 0; i < e[u].size(); i++)
            if (e[u].get(i).to != father)
                dfs(e[u].get(i).to, u, d + e[u].get(i).w);
    }

    int cnt(TreeNode root) {
        if (root == null) return 0;
        return 1 + cnt(root.left) + cnt(root.right);
    }

    static class Edge {
        public int to;
        public int w;   // 边长

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
