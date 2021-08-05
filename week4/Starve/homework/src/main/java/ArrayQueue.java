import java.util.Arrays;

public class ArrayQueue {

  int[] queue;

  public ArrayQueue() {
  }

  public ArrayQueue(int[] queue) {
    this.queue = queue;
  }

  public void push(int data) {
    if (queue == null) {
      queue = new int[1];
      queue[0] = data;
      return;
    }
    queue = Arrays.copyOf(queue, queue.length + 1);
    queue[queue.length-1] = data;
  }

  public int pop() {
    if (this.queue.length - 1 < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }

    int[] temp = new int[queue.length-1];
    int answer = queue[0];
    for(int i = 0; i < temp.length; i++) {
      temp[i] = queue[i+1];
    }

    queue = temp;

    return answer;
  }

}
