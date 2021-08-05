package queue;

import linkedList.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListNodeQueueTest {

    ListNode head;
    ListNodeQueue queue;

    @BeforeEach
    void setup() {
        head = new ListNode(0);
        queue = new ListNodeQueue(head);

        queue.print();
    }

    @Test
    void offer_poll_테스트() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.print();

        assertThat(queue.poll()).isEqualTo(0);
        queue.print();
    }
}
