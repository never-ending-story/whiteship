package week4.dataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeQueueTest {
    ListNodeQueue listNodeQueue;

    @BeforeEach
    void setUp() {
        listNodeQueue = new ListNodeQueue();
    }

    @Test
    void addTest() {
        listNodeQueue.add(1);
        assertThat(listNodeQueue.head.data).isEqualTo(1);
    }

    @Test
    void pollTest() {
        listNodeQueue.add(1);
        listNodeQueue.add(2);

        assertThat(listNodeQueue.poll()).isEqualTo(1);
        assertThat(listNodeQueue.head.data).isEqualTo(2);
    }
}
