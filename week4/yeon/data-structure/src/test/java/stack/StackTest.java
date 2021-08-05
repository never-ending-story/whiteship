package stack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {

    Stack stack;

    @BeforeEach
    void setup() {
        stack = new Stack(5);
    }

    @Test
    void push_pop_테스트() {
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.print();

        Assertions.assertThat(stack.pop()).isEqualTo(4);
        stack.print();
    }
}
