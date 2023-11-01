package codetop;

/**
 * 反转链表 II
 */
public class T92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;
        // 注意可以用一个 dummy, 否则边界条件会很恶心
        ListNode t1 = null, t2 = t1, t3 = t2, t4 = t3, d = new ListNode(), h = d;
        d.next = head;
        int cur = 1;
        while (t1 == null || t3 == null) {
            if (cur == left) t1 = h;
            if (cur == right + 1) t3 = h;
            h = h.next;
            cur++;
        }
        t2 = t1.next;
        t4 = t3.next;
        t1.next = null;
        t3.next = null;
        t1.next = reverse(t2, t3);
        h = t1;
        while (h.next != null) h = h.next;
        h.next = t4;
        return d.next;
    }

    ListNode reverse(ListNode begin, ListNode end) {
        ListNode d = new ListNode(), h = begin;
        while (h != null) {
            ListNode t = h.next;
            h.next = d.next;
            d.next = h;
            h = t;
        }
        return d.next;
    }

    public static void main(String[] args) {
        T92 solution = new T92();
        ListNode h = new ListNode(1);
        h.next = new ListNode(3);
        h.next.next = new ListNode(2);
        h.next.next.next = new ListNode(4);
        h.next.next.next.next = new ListNode(5);

        h = new ListNode(3);
        h.next = new ListNode(5);

        solution.reverseBetween(h, 1, 1);
    }
}
