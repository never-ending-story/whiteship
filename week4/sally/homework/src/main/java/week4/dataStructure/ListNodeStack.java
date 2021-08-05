package week4.dataStructure;

public class ListNodeStack {
    ListNode top;

    public void push(int data) {
        ListNode node = new ListNode(data);
        node.next = top;
        top = node;
    }

    public int pop() {
        if (top == null) {
//            throw new EmptyStackException();
            System.out.println("Stack is empty");
            return 0;
        }

        int dataToReturn = top.data;
        top = top.next;

        return dataToReturn;
    }
}
