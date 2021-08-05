package queue;

import linkedList.ListNode;

public class ListNodeQueue {

    private ListNode head;

    public ListNodeQueue(ListNode head) {
        this.head = head;
    }

    public void offer(int data) {
        ListNode node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new ListNode(data));
    }

    public int poll() {
        int data = head.getValue();
        head = head.getNext();
        return data;
    }

    public void print() {
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
