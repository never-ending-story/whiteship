package stack;

import java.util.Arrays;
import java.util.Collections;

/**
 * stack 생성 시 크기를 정해준다.
 * push 수행 시 stack이 꽉 차면 예외를 발생시킨다.
 */
public class Stack {

    private int[] stack;
    private int topIdx;

    public Stack(int size) {
        this.stack = new int[size];
        this.topIdx = -1;
    }

    public void push(int data) {
        if (topIdx == stack.length) {
            throw new StackFullException();
        }
        topIdx++;
        stack[topIdx] = data;
    }

    public int pop() {
        /**
         *  마지막 요소를 제거한 스택으로 교체하고 배열의 마지막 데이터 반환
         */
        int lastData = stack[topIdx];
        topIdx--;
        return lastData;
    }

    public void print() {
        System.out.println("================");
        Arrays.stream(stack).limit(topIdx + 1).boxed().sorted(Collections.reverseOrder()).forEach(System.out::println);
        System.out.println();
    }

    private class StackFullException extends RuntimeException {

        public StackFullException() {
            super("Stack is full!!!");
        }
    }
}
