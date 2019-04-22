package work.interview.LinkedList;

import work.interview.integer.Divide2Integers;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodes {
    public ListNode swapPairs(ListNode head) {
        ListNode newHeadNode = null;
        ListNode targetNode = head;
        ListNode preNode = null;
        while (targetNode != null && targetNode.next != null) {
            ListNode nowNode = targetNode;
            ListNode nextNode = nowNode.next;
            ListNode next2Node = nextNode.next;
            nowNode.next = next2Node;
            nextNode.next = nowNode;
            if (newHeadNode == null) {
                newHeadNode = nextNode;
            }
            if (preNode != null) {
                preNode.next = nextNode;
            }
            preNode = nowNode;
            targetNode = next2Node;
        }
        if (newHeadNode == null) {
            newHeadNode = head;
        }
        return newHeadNode;
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        System.out.println(new SwapNodes().swapPairs(node1));
    }
}
