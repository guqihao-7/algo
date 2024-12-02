package codecrush;

public class T5031 {

    public ListNode insertAtHead(ListNode head, int value) {
        if (head == null) {
            head = new ListNode(value);
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode t = new ListNode(value);
        t.next = dummy.next;
        dummy.next = t;
        return t;
    }

    public ListNode insertAtTail(ListNode head, int value) {
        if (head == null) {
            head = new ListNode(value);
            return head;
        }

        ListNode dummy = new ListNode(0, head), p = dummy;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new ListNode(value);
        return p.next;
    }

    public int size(ListNode head) {
        ListNode dummy = new ListNode(0, head), p = dummy;
        int size = 0;
        while (p.next != null) {
            size++;
            p = p.next;
        }
        return size;
    }

    public ListNode prev(ListNode head, ListNode enode) {
        ListNode dummy = new ListNode(0, head), p = dummy;
        while (p != null && p.next != enode) {
            p = p.next;
        }

        return p;
    }

    public ListNode delete(ListNode head, ListNode enode) {
        ListNode prev = prev(head, enode);
        if (prev == null) return null;
        prev.next = prev.next.next;
        return head;
    }


}
