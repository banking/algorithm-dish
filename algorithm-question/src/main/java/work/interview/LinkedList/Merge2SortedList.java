package work.interview.LinkedList;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class Merge2SortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode target = null;
        ListNode targetCursor = null;
        if (l1 == null) {
            target = l2;
            return target;
        }
        if (l2 == null) {
            target = l1;
            return target;
        }
        if (l1.val < l2.val) {
            target = l1;
            l1 = l1.next;
        } else {
            target = l2;
            l2 = l2.next;
        }
        targetCursor = target;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                targetCursor.next = l2;
                l2 = null;
                continue;
            }
            if (l2 == null) {
                targetCursor.next = l1;
                l1 = null;
                continue;
            }
            if (l1.val < l2.val) {
                targetCursor.next = l1;
                targetCursor = targetCursor.next;
                l1 = l1.next;
            } else {
                targetCursor.next = l2;
                targetCursor = targetCursor.next;
                l2 = l2.next;
            }
        }
        return target;
    }

}
