public class MergeLinkedList {

    ListNode targetNode = null;
    ListNode targetIndexNode = null;
    public ListNode merge(ListNode node1, ListNode node2) {
        while (node1 != null || node2 != null) {
            if (node1 != null && node2 != null) {
                if (node1.node > node2.node) {
                    setNext(node2);
                    node2 = node2.next;
                } else {
                    setNext(node1);
                    node1 = node1.next;
                }
                continue;
            }
            if (node1 != null) {
                targetIndexNode.next = node1;
                node1 = null;
                continue;
            }
            if (node2 != null) {
                targetIndexNode.next = node2;
                node2 = null;
                continue;
            }
        }
        return  targetNode;
    }

    public void setNext(ListNode node) {
        if (targetNode == null) {
            targetNode = node;
        } else {
            targetIndexNode.next = node;
        }
        targetIndexNode = node;
    }


    public class ListNode {
        int node;
        ListNode next;
    }
}
