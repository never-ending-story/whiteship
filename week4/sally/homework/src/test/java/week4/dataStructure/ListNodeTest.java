package week4.dataStructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static week4.dataStructure.ListNode.*;

public class ListNodeTest {

    @DisplayName("제일 앞자리에 추가")
    @Test
    void addAtBeginning() {
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(2);
        add(head, node, 0);

        assertThat(node.next).isEqualTo(head);
    }

    @DisplayName("끝에 추가")
    @Test
    void addAtTheEnd() {
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(2);
        head.next = node;

        ListNode nodeToAdd = new ListNode(3);

        add(head, nodeToAdd, 2);
        assertThat(node.next).isEqualTo(nodeToAdd);
        assertThat(nodeToAdd.next).isNull();
    }

    @DisplayName("중간에 추가")
    @Test
    void addInTheMiddle() {
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(2);
        head.next = node;

        ListNode nodeToAdd = new ListNode(3);

        add(head, nodeToAdd, 1);
        assertThat(head.next).isEqualTo(nodeToAdd);
        assertThat(nodeToAdd.next).isEqualTo(node);
        assertThat(node.next).isNull();
    }

    @Test
    void removeTest() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        remove(head, 1);
        assertThat(head.next).isNull();
    }

    @Test
    void containsTest() {
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(2);
        ListNode anotherNode = new ListNode(3);
        ListNode random = new ListNode(1);

        head.next = node;
        head.next.next = anotherNode;

        assertThat(contains(head, node)).isTrue();
        assertThat(contains(head, anotherNode)).isTrue();
        assertThat(contains(head, random)).isFalse();
    }
}
