package leetcode.list.T100_149;

import java.util.*;

public class T133 {
    public Node cloneGraph(Node node) {
        return bfs(node);
    }

    public Node bfs(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Deque<Node> q = new ArrayDeque<>();
        q.addLast(node);
        HashSet<Node> visit = new HashSet<>();

        while (!q.isEmpty()) {
            Node head = q.removeFirst();
            map.put(head, new Node(head.val));

            for (Node neighbor : head.neighbors) {
                if (visit.contains(neighbor)) continue;
                visit.add(neighbor);
                q.addLast(neighbor);
            }
        }

        Set<Node> nodes = map.keySet();
        for (Node nd : nodes) {
            Node nodeCloned = map.get(nd);
            List<Node> neighbors = nd.neighbors;
            for (Node neighbor : neighbors) {
                Node nei = map.get(neighbor);
                nodeCloned.neighbors.add(nei);
            }
        }

        return map.get(node);
    }

    public static void main(String[] args) {
        // [[2,4],[1,3],[2,4],[1,3]]
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        T133 solution = new T133();
        solution.cloneGraph(node1);
    }


    // Definition for a Node.
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
