import java.util.Arrays;

public class ArrayStack {

  int[] stack;

  public ArrayStack() {
  }

  public ArrayStack(int[] stack) {
    this.stack = stack;
  }

  public void push(int data) {
    if (stack == null) {
      stack = new int[1];
      stack[0] = data;
      return;
    }
    stack = Arrays.copyOf(stack, stack.length + 1);
    stack[stack.length-1] = data;
  }

  public int pop() {
    if (this.stack.length - 1 < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }

    int num = stack[stack.length - 1];
    Arrays.copyOf(stack, stack.length-1);
    return num;
  }

}
