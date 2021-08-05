import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayStackTest {

  ArrayStack arrayStack;

  @BeforeEach
  public void setUp() {
    this.arrayStack = new ArrayStack(new int[]{1,2,3});
  }

  @Test
  public void push() {
    arrayStack.push(4);
    assertThatStack(1,2,3,4);
  }

  @Test
  public void pop() {
    assertThat(arrayStack.pop(), is(3));
  }

  private void assertThatStack(int... value) {
    for(int i = 0; i < value.length; i++) {
      assertThat(arrayStack.stack[i], is(value[i]));
    }
  }

}
