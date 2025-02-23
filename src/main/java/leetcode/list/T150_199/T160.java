package leetcode.list.T150_199;

import leetcode.list.ListNode;

public class T160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        int cnt1 = 0, cnt2 = 0;
        while (p1 != null) {
            cnt1++;
            p1 = p1.next;
        }

        while (p2 != null) {
            cnt2++;
            p2 = p2.next;
        }

        p1 = cnt1 > cnt2 ? headA : headB;
        p2 = p1 == headA ? headB : headA;
        int diff = Math.abs(cnt1 - cnt2);

        for (int i = 0; i < diff; i++)
            p1 = p1.next;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }
}
