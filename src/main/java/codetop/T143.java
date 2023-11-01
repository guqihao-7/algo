package codetop;

/**
 * 重排链表
 */
public class T143 {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head, l1 = head, l2, d = new ListNode(), t = d;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (slow == null) return;
        l2 = reverse(slow.next);
        slow.next = null;
        while (l1 != null && l2 != null) {
            ListNode tmp = l1.next;
            l1.next = null;
            d.next = l1;
            d = d.next;
            l1 = tmp;

            tmp = l2.next;
            l2.next = null;
            d.next = l2;
            d= d.next;
            l2 = tmp;
        }

        if (l1 != null) d.next = l1;
        if (l2 != null) d.next = l2;
    }

    ListNode reverse(ListNode head) {
        ListNode d = new ListNode();
        while (head != null) {
            ListNode t = head.next;
            head.next = d.next;
            d.next = head;
            head = t;
        }
        return d.next;
    }
}
