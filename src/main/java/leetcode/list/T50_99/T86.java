package leetcode.list.T50_99;

import leetcode.list.ListNode;

public class T86 {
    public ListNode partition(ListNode head, int x) {
        ListNode lt = new ListNode(), p1 = lt,
                ge = new ListNode(), p2 = ge, curr = head;

        while (curr != null) {
            if (curr.val < x) {
                p1.next = curr;
                p1 = p1.next;
            }
            else {
                p2.next = curr;
                p2 = p2.next;
            }

            ListNode t = curr.next;
            curr.next = null;
            curr = t;
        }

        p1.next = ge.next;

        return lt.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        T86 solution = new T86();
        solution.partition(listNode, 3);
    }
}
