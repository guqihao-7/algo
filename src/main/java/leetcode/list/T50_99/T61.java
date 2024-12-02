package leetcode.list.T50_99;


import codecrush.ListNode;

public class T61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode t = head;
        int size = 1;
        while (t.next != null) {
            size++;
            t = t.next;
        }
        k %= size;
        if (0 == k) return head;
        int move = size - k - 1;
        t = head;
        while (move-- > 0) t = t.next;

        ListNode n = t.next;
        t.next = null;

        ListNode p = n;
        while (p != null && p.next != null) p = p.next;
        if (p != null) p.next = head;
        return n;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        T61 solution = new T61();
        solution.rotateRight(head, 1);
    }
}
