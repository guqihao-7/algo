package data_structure;

public class SkipList {
    private static final double P = 0.25;
    private static final int MAX_LEVEL = 32;

    private final Node head = new Node(-1, MAX_LEVEL);

    public void add(int num) {
        int level = this.randomLevel();
        Node node = new Node(num, level);
        Node cur = head;
        // 从最高等级开始 向下寻找 找到属于自己的等级时 添加
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (cur.next[i] != null && num > cur.next[i].val)
                cur = cur.next[i];
            if (i >= level) continue;
            node.next[i] = cur.next[i];
            cur.next[i] = node;
        }
    }

    /**
     * 返回target是否存在于跳表中
     */
    public boolean search(int target) {
        Node search = head;
        // 从最高等级找 最接近自己的节点 判断
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (search.next[i] != null && target >= search.next[i].val) search = search.next[i];
            if (search.val == target) return true;
        }
        return false;
    }

    /**
     * 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可
     */
    public boolean erase(int num) {
        boolean flag = false;
        Node erase = head;
        // 从最高等级找 小于自己的节点(找上一个节点 才能删除)
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            while (erase.next[i] != null && num > erase.next[i].val) erase = erase.next[i];
            if (erase.next[i] == null || erase.next[i].val != num) continue;
            erase.next[i] = erase.next[i].next[i];
            flag = true;
        }
        return flag;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL)
            level++;
        return level;
    }

    private static class Node {
        // 存储的值
        private final int val;
        // 每个等级的下一个节点
        private final Node[] next;

        public Node(int val, int level) {
            this.val = val;
            this.next = new Node[level];
        }
    }
}
