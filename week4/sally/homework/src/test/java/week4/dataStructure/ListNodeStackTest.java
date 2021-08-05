package week4.dataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeStackTest {
    ListNodeStack listNodeStack;

    @BeforeEach
    void setUp() {
        listNodeStack = new ListNodeStack();
    }

    @Test
    void pushTest(){
        listNodeStack.push(1);
        assertThat(listNodeStack.top.data).isEqualTo(1);

        listNodeStack.push(2);
        assertThat(listNodeStack.top.data).isEqualTo(2);
    }

    @Test
    void popTest() {
        listNodeStack.push(1);
        listNodeStack.push(2);

        assertThat(listNodeStack.pop()).isEqualTo(2);
        assertThat(listNodeStack.pop()).isEqualTo(1);
        assertThat(listNodeStack.pop()).isEqualTo(0);
    }
}
