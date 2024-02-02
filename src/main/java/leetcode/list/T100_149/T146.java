package leetcode.list.T100_149;

import java.util.HashMap;
import java.util.Map;

public class T146 {
    public static class LRUCache {
        int cap;
        LinkedList list;
        HashMap<Integer, Node> map;

        public LRUCache(int capacity) {
            cap = capacity;
            list = new LinkedList();
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);
            list.moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            /*
                分几种情况：
                1. key 已经在 list 中
                    直接将 node 移到队头
                2. key 不在 list 中
                    2.1 如果 list 未满, 则直接放在队头
                    2.2 如果已满, 放到队头后移除队尾
             */

            boolean exist = map.containsKey(key);
            if (exist) {
                // 如果关键字 key 已经存在，则变更其数据值 value
                map.get(key).value = value;
                list.moveToHead(map.get(key));
            }
            else {
                // 如果不存在，则向缓存中插入该组 key-value 。
                // 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
                Node node = new Node(key, value);
                list.addFirst(node);
                map.put(key, node);

                if (list.size > cap) {
                    Node tail = list.removeLast();
                    map.remove(tail.key);
                }
            }
        }

        public static class LinkedList {
            public Node head;
            public Node tail;
            public int size;

            public LinkedList() {
                this.head = new Node();
                this.tail = new Node();
                this.tail.pre = head;
                this.head.next = tail;
                this.size = 0;
            }

            public void addFirst(Node node) {
                node.next = head.next;
                node.pre = head;
                head.next.pre = node;
                head.next = node;
                size++;
            }

            public void moveToHead(Node node) {
                node.next.pre = node.pre;
                node.pre.next = node.next;
                addFirst(node);
                size--;
            }

            public void addLast(Node node) {
                node.next = tail;
                node.pre = tail.pre;
                tail.pre.next = node;
                tail.pre = node;
                size++;
            }

            public Node removeFirst() {
                Node tmp = head.next;
                head.next = head.next.next;
                head.next.pre = head;
                size--;
                return tmp;
            }

            public Node removeLast() {
                if (tail.pre == head) {
                    return null;
                }
                Node tmp = tail.pre;
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
                size--;
                return tmp;
            }
        }

        public static class Node {
            public int key, value;
            public Node pre, next;

            public Node() {}

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    public static void main(String[] args) {
// ["LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
// [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
        String[] operations = {"LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
        int[][] values = {{10},{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};


        LRUCache lruCache = null;
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            int[] value = values[i];

            switch (operation) {
                case "LRUCache":
                    lruCache = new LRUCache(value[0]);
                    break;
                case "put":
                    lruCache.put(value[0], value[1]);
                    break;
                case "get":
                    int result = lruCache.get(value[0]);
                    // Assuming you want to print the result of 'get' operations.
                    System.out.println("Get operation result: " + result);
                    break;
            }
        }
    }
}
