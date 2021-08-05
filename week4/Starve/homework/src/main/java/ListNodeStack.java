public class ListNodeStack {

  ListNode head;

  public ListNodeStack() {
    this.head = new ListNode();
  }

  public void push(int data) {
    ListNode node = new ListNode(data);
    ListNode target = this.head;

    while (target.next != null) {
      target = target.next;
    }

    target.next = node;

  }

  public int pop() {
    if (this.head.next == null)
      throw new IndexOutOfBoundsException();

    ListNode target = this.head.next;
    ListNode before = head;

    while (target.next != null) {
      before = target;
      target = target.next;
    }

    before.next = null;
    return target.getData();
  }
}
