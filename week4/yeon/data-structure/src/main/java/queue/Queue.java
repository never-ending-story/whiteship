package queue;

import java.util.Arrays;

public class Queue {

    private int[] queue;
    private int lastIdx;

    public Queue() {
        this.queue = new int[15];
        this.lastIdx = -1;
    }

    public void offer(int data) {
        if (lastIdx == queue.length - 1) {
            queue = Arrays.copyOf(queue, queue.length + 15);
        }
        lastIdx++;
        queue[lastIdx] = data;
    }

    public int poll() {
        int data = queue[0];
        queue = Arrays.stream(queue).skip(1L).toArray();
        lastIdx--;
        return data;
    }

    public void print() {
        System.out.println("================");
        Arrays.stream(queue).limit(lastIdx + 1).forEach(System.out::println);
        System.out.println();
    }
}
