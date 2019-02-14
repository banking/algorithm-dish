package work.interview;

/**
 * Add Two Numbers
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 *  * Definition for singly-linked list.
 *  * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode(int x) { val = x; }
 *  * }
 *
**/
public class LinkedList1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean leap = false;
        ListNode resultNode = new ListNode(0);
        resultNode.val = (l1.val + l2.val) % 10;
        leap = ((l1.val + l2.val) / 10) == 1;
        ListNode parentNode = resultNode;
        while (l1.next != null || l2.next != null || leap) {
            int valResult = 0;
            if (l1.next != null) {
                valResult += l1.next.val;
                l1 = l1.next;
            }
            if (l2.next != null) {
                valResult += l2.next.val;
                l2 = l2.next;
            }
            if (leap) {
                valResult ++;
            }
            ListNode childNode = new ListNode(0);
            childNode.val = valResult % 10;
            leap = (valResult / 10) == 1;
            parentNode.next = childNode;
            parentNode = childNode;
        }
        return resultNode;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
