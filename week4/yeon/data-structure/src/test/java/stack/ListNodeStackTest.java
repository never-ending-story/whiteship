package stack;

import linkedList.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeStackTest {

    ListNode head;
    ListNodeStack stack;

    @BeforeEach
    void setup() {
        head = new ListNode(0);
        stack = new ListNodeStack(head);
    }

    @Test
    void push_pop_테스트() {

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print(head);

        assertThat(stack.pop()).isEqualTo(3);
        stack.print(head);
    }
}
