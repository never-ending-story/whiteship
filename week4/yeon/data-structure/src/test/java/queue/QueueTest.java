package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueTest {

    Queue queue;

    @BeforeEach
    void setup() {
        queue = new Queue();
    }

    @Test
    void offer_poll_테스트() {
        for (int i = 0; i < 5; i++) {
            queue.offer(i);
        }
        queue.print();

        assertThat(queue.poll()).isEqualTo(0);
        queue.print();
    }
}
