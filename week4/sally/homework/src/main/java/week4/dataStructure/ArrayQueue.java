package week4.dataStructure;

public class ArrayQueue {
     int tail;
     int size;
     int[] queueArray;

    public ArrayQueue(int size) {
        this.size = size;
        queueArray = new int[size];
        tail = -1;
    }

    public void add(int data) {
        if (tail == -1) {
            tail += 1;
            queueArray[0] = data;
            return;
        }

        if (tail + 1 >= size) {
            System.out.println("Queue is full. Failed to add data.");
            return;
        }

        tail += 1;
        queueArray[tail] = data;
    }

    public int poll() {
        if (tail == -1) {
            System.out.println("Queue is empty");
            return 0;
        }

        if (tail == 0) {
            int dataToReturn = queueArray[0];
            tail = -1;
            return dataToReturn;
        }

        int dataToReturn = queueArray[0];

        for (int i = 0; i < tail; i++) {
            queueArray[i] = queueArray[i + 1];
        }
        tail -= 1;

        return dataToReturn;
    }
}
