import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListNodeQueueTest {

  ListNodeQueue listNodeQueue;

  @BeforeEach
  public void setUp() {
    this.listNodeQueue = new ListNodeQueue();
    listNodeQueue.push(1);
    listNodeQueue.push(2);
    listNodeQueue.push(3);
  }

  @Test
  public void pop() {
    assertThat(listNodeQueue.pop(),is(1));
  }

  @Test
  public void push() {
    listNodeQueue.push(4);

    assertThatQueue(1,2,3,4);

  }

  private void assertThatQueue(int... value) {
    ListNode node = listNodeQueue.head;

    for(int i : value) {
      node = node.next;
      assertThat(node.getData(), is(i));
    }

    assertNull(node.next);
  }

}
