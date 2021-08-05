package stack;

import linkedList.ListNode;

public class ListNodeStack {

    private ListNode head;

    public ListNodeStack(ListNode head) {
        this.head = head;
    }

    public void push(int data) {
        ListNode node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new ListNode(data));
    }

    public int pop() {
        ListNode node = head;
        ListNode previous = null;
        while (node.getNext() != null) {
            if (!node.equals(head)) {
                previous = node;
            }
            node = node.getNext();
        }
        previous.setNext(null);
        return node.getValue();
    }

    public void print(ListNode head) {
        System.out.println("==================");

        ListNode node = head;
        int idx = 0;
        while (node != null) {
            System.out.println(node.getValue());
            idx++;
            node = node.getNext();
        }

        System.out.println();
    }
}
