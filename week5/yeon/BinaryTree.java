import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void bfs(Node root) {
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Node leftNode = node.getLeft();
            Node rightNode = node.getRight();
            if (leftNode != null) {
                queue.add(leftNode);
            }
            if (rightNode != null) {
                queue.add(rightNode);
            }

            System.out.print(node.getValue() + " ");
        }
        System.out.println();
    }

    public void dfs(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            Node rightNode = node.getRight();
            Node leftNode = node.getLeft();
            if (rightNode != null) {
                stack.push(rightNode);
            }
            if (leftNode != null) {
                stack.push(leftNode);
            }
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
    }

    public void dfsRecursive(Node node) {
        if (node == null) return;

        System.out.print(node.getValue() + " ");

        Node leftNode = node.getLeft();
        Node rightNode = node.getRight();

        dfsRecursive(leftNode);
        dfsRecursive(rightNode);
    }
}
