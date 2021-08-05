import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayQueueTest {

  ArrayQueue arrayQueue;

  @BeforeEach
  public void setUp() {
    this.arrayQueue = new ArrayQueue(new int[]{1,2,3});
  }

  @Test
  public void push() {
    arrayQueue.push(4);
    assertThatStack(1,2,3,4);
  }

  @Test
  public void pop() {
    assertThat(arrayQueue.pop(), is(1));
  }

  private void assertThatStack(int... value) {
    for(int i = 0; i < value.length; i++) {
      assertThat(arrayQueue.queue[i], is(value[i]));
    }
  }
}
