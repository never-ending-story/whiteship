public class ListNodeQueue {

  ListNode head;

  public ListNodeQueue() {
    this.head = new ListNode();
  }

  public void push(int data) {
    ListNode node = new ListNode(data);
    ListNode target = this.head;

    while (target.next != null) target = target.next;

    target.next = node;
  }

  public int pop() {
    if(this.head.next == null) throw new IndexOutOfBoundsException();
    ListNode target = this.head.next;
    this.head = head.next;
    return target.getData();
  }
}
