package week4.dataStructure;

public class ListNode {
    int data;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int value) {
        this.data = value;
    }

    public ListNode(int value, ListNode next) {
        this.data = value;
        this.next = next;
    }

    public static ListNode add(ListNode head, ListNode nodeToAdd, int position) {
        if (position == 0) {
            nodeToAdd.next = head;
            return nodeToAdd;
        }
        ListNode node = head;
        while (position > 1) {
            node = node.next;
            position--;
        }

        nodeToAdd.next = node.next;
        node.next = nodeToAdd;

        return head;
    }

    public static  ListNode remove(ListNode head, int positionToRemove) {
        while (positionToRemove != 1) {
            head = head.next;
            positionToRemove--;
        }

        ListNode nodeToRemove = head.next;
        if (head.next.next == null) {
            head.next = null;
        } else {
            head.next = head.next.next;
        }

        return nodeToRemove;
    }

    public static boolean contains(ListNode head, ListNode nodeToCheck) {
        while (head.next != null) {
            if (head.next.equals(nodeToCheck)) {
                return true;
            }
            head = head.next;
        }

        return false;
    }
}
