package linkedList;

public class ListNode {

    private int value;
    private ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode nodeToAdd) {
        next = nodeToAdd;
    }

    public int getValue() {
        return value;
    }
}
