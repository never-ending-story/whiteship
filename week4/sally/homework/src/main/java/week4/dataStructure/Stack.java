package week4.dataStructure;

public class Stack {
    public int top;
    private int size;
    public int[] stackArray;

    public Stack(int size) {
        this.size = size;
        top = -1;
        stackArray = new int[size];
    }

    public void push(int data) {
        if (top == -1) {
            top += 1;
            stackArray[top] = data;
            return;
        }

        if (top + 1 >= size) {
//            throw new RuntimeException("Stack is currently full");
            System.out.println("Stack is full");
            return;
        }

        top += 1;
        stackArray[top] = data;
    }

    public int pop() {
        if (top == -1) {
//            throw new RuntimeException("Stack is empty");
            System.out.println("Stack is empty");
            return 0;
        }

        int dataToReturn = stackArray[top];
        top -= 1;

        return dataToReturn;
    }
}
