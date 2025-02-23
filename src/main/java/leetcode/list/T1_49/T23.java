package leetcode.list.T1_49;

import leetcode.list.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class T23 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(), p = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode head : lists)
            if (head != null) pq.add(head);

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            p.next = min;
            p = p.next;
            ListNode t = min.next;
            min.next = null;
            min = t;
            if (min != null) pq.add(min);
        }

        return dummy.next;
    }
}
