package leetcode.list.T100_149;

import java.util.ArrayDeque;
import java.util.Deque;

public class T117 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

        public Node connect(Node root) {
            Deque<Node> q = new ArrayDeque<>();
            if (root != null) {
                q.add(root);
            }
            while (!q.isEmpty()) {
                int sz = q.size();
                while (sz > 0) {
                    Node node = q.poll();
                    sz--;
                    if (node == null) break;
                    if (sz != 0) {
                        node.next = q.peek();
                    }
                    if (node.left != null) {
                        q.add(node.left);
                    }

                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
            }
            return root;
        }
}
