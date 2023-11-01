package codetop;

public class T21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode d = new ListNode(), cur = d;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                ListNode t = list1.next;
                cur.next = list1;
                list1.next = null;
                list1 = t;
            } else {
                ListNode t = list2.next;
                cur.next = list2;
                list2.next = null;
                list2 = t;
            }
            cur = cur.next;
        }
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;
        return d.next;
    }
}
