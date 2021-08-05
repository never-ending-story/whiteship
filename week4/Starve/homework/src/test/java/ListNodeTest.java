import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListNodeTest {
  ListNode head;

  @BeforeEach
  void config() {
    head = new ListNode();
    ListNode first = new ListNode(1);
    ListNode second = new ListNode(2);
    ListNode third = new ListNode(3);

    this.head.next = first;
    first.next = second;
    second.next = third;
  }

  @Test
  public void add() {
    ListNode newNode = new ListNode(4);

    ListNode.add(head,newNode,2);

    this.assertThatList(1,4,2,3);
  }

  @Test
  public void delete() {
    ListNode.remove(head, 2);

    this.assertThatList(1,3);
  }

  @Test
  public void contains() {
    assertTrue(ListNode.contains(head,head.next.next));
    assertFalse(ListNode.contains(head,new ListNode(5)));
  }


  private void assertThatList(int... value) {
    ListNode node = this.head;

    for(int i : value) {
      node = node.next;
      assertThat(node.getData(), is(i));
    }

    assertNull(node.next);
  }

}
