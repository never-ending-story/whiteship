package linkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {

    LinkedList list;

    ListNode last;
    ListNode fourth;
    ListNode third;
    ListNode second;
    ListNode head;

    @BeforeEach
    void setup() {
        list = new LinkedList();

        // 해당 링크드리스트의 크기는 5
        last = new ListNode(4);
        fourth = new ListNode(3, last);
        third = new ListNode(2, fourth);
        second = new ListNode(1, third);
        head = new ListNode(0, second);

        list.print(head);
    }

    @Test
    void add_contain_테스트() {
        ListNode newNode = new ListNode(333);

        // 3번째 인덱스에 중간 삽입
        list.add(head, newNode, 3);
        list.print(head);

        assertThat(list.contains(head, newNode)).isTrue();

    }

    @Test
    void remove_contain_테스트() {
        // 3번째 인덱스 노드 삭제
        list.remove(head, 3);
        list.print(head);

        assertThat(list.contains(head, fourth)).isFalse();
    }

}
