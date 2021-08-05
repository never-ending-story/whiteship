import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListNodeStackTest {

  ListNodeStack listNodeStack;

  @BeforeEach
  public void setUp() {
    this.listNodeStack = new ListNodeStack();
    listNodeStack.push(1);
    listNodeStack.push(2);
    listNodeStack.push(3);
  }

  @Test
  public void pop() {
    assertThat(listNodeStack.pop(),is(3));
  }

  @Test
  public void push() {
    listNodeStack.push(4);

    assertThatStack(1,2,3,4);
  }


  private void assertThatStack(int... value) {
    ListNode node = listNodeStack.head;

    for(int i : value) {
      node = node.next;
      assertThat(node.getData(), is(i));
    }

    assertNull(node.next);
  }
}
