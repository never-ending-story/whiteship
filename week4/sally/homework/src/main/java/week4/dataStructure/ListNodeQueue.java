package week4.dataStructure;

public class ListNodeQueue {
     ListNode head;
     ListNode tail;

    public void add(int data) {
        ListNode node = new ListNode(data);

        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = tail;
        }
    }

    public int poll() {
        if (head == null) {
            System.out.println("Queue is empty");
            return 0;
        }

        int dataToReturn = head.data;
        head = head.next;

        return dataToReturn;
    }
}
