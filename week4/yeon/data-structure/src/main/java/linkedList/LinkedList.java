package linkedList;

public class LinkedList {

    public ListNode add(ListNode head, ListNode nodeToAdd, int position) {
        /**
         *  추가될 노드를 참조할 노드 - position을 이용해서 찾는다.
         *  position: 삽일할 인덱스
         */
        ListNode node = null;
        for (int i = 0; i < position; i++) {
            if (i == 0) {
                node = head;
            } else {
                node = node.getNext();
            }
        }
        ListNode nextNode = node.getNext();
        if (nextNode == null) {       // 마지막 노드인 경우
            node.setNext(nodeToAdd);
        } else {                             // 마지막 노드가 아닌 경우
            node.setNext(nodeToAdd);
            nodeToAdd.setNext(nextNode);
        }

        return node;
    }

    public ListNode remove(ListNode head, int positionToRemove) {
        /**
         *  삭제될 노드를 참조하는 노드 - position을 이용해서 찾는다.
         *  삭제될 노드의 이전 노드(previousNode)에 다음 노드(nextNode)를 연결한 뒤, 해당 노드를 삭제한다.
         */
        ListNode node = null;
        ListNode previousNode = null;
        for (int i = 0; i <= positionToRemove; i++) {
            if (i == 0) {
                node = head;
            } else {
                if (i == positionToRemove) {
                    previousNode = node;
                }
                node = node.getNext();
            }
        }
        ListNode nextNode = node.getNext();
        if (nextNode != null) {     // 삭제될 노드가 마지막 노드가 아닌 경우
            previousNode.setNext(nextNode);
        } else {                    // 삭제될 노드가 마지막 노드인 경우
            previousNode.setNext(null);
        }
        // 삭제인데 삭제되는 노드를 return 하는 건가, 뭐를 return 해야하지..?
        return node;
    }

    public boolean contains(ListNode head, ListNode nodeToCheck) {
        ListNode node = head;
        while (node != null) {
            if (node.equals(nodeToCheck)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void print(ListNode head) {
        System.out.println("==================");

        ListNode node = head;
        int idx = 0;
        while (node != null) {
            System.out.println("node index: " + idx + " value: " + node.getValue());
            idx++;
            node = node.getNext();
        }

        System.out.println();
    }
}
