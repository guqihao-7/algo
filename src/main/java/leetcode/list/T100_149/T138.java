package leetcode.list.T100_149;

import java.util.*;

public class T138 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // k 是原链表节点, v 是新节点
        Map<Node, Node> map = new HashMap<>();
        Node tmp = head;
        while (tmp != null) {
            map.put(tmp, new Node(tmp.val));
            tmp = tmp.next;
        }

        Set<Node> nodes = map.keySet();
        for (Node node : nodes) {
            Node clonedNode = map.get(node);
            Node next = map.get(node.next);
            Node random = map.get(node.random);

            clonedNode.next = next;
            clonedNode.random = random;
        }

        return map.get(head);
    }


    // Definition for a Node.
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
