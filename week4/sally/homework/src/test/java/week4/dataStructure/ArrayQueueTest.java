package week4.dataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayQueueTest {

    ArrayQueue q;

    @BeforeEach
    void setUp() {
        q = new ArrayQueue(4);
    }

    @Test
    public void addTest() {
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);

        assertThat(q.queueArray[0]).isEqualTo(1);
        assertThat(q.queueArray[1]).isEqualTo(2);
        assertThat(q.queueArray[2]).isEqualTo(3);
        assertThat(q.queueArray[3]).isEqualTo(4);
    }

    @Test
    public void pollTest() {
        q.add(1);
        assertThat(q.poll()).isEqualTo(1);
        assertThat(q.poll()).isEqualTo(0);
    }
}
