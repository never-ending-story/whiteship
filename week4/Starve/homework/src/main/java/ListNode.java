import java.util.Objects;

public class ListNode {

  public ListNode next;
  private int data;

  public ListNode() {
  }

  public ListNode(int data) {
    this.data = data;
  }

  public static ListNode add(ListNode head, ListNode nodeToAdd, int position) {
    ListNode target = head;
    for (int i = 0; i < position - 1; i++) {
      target = target.next;
    }
    nodeToAdd.next = target.next;
    target.next = nodeToAdd;
    return nodeToAdd;
  }

  public static ListNode remove(ListNode head, int positionToRemove) {
    ListNode target = head.next;
    ListNode before = head;
    for(int i = 0; i < positionToRemove-1; i++) {
      before = target;
      target = target.next;
    }
    before.next = target.next;
    return before;
  }

  public static boolean contains(ListNode head, ListNode nodeTocheck) {
    while (head.next != null) {
      if(head.equals(nodeTocheck)) {
        return true;
      }

      head = head.next;
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListNode listNode = (ListNode) o;
    return data == listNode.data;
  }

  public int getData() {
    return data;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }
}

