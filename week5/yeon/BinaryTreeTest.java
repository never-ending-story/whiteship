public class BinaryTreeTest {


    /**             0
     *           /     \
     *          1       2
     *         /  \    /  \
     *        3    4  5    6
     *       / \   /
     *      7  8  9
     *
     *   BFS - 0 1 2 3 4 5 6 7 8 9
     *   DFS - 0 1 3 7 8 4 9 2 5 6
     */
    public static void main(String[] args) {

        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node3.setLeft(node7);
        node3.setRight(node8);
        node4.setLeft(node9);

        BinaryTree tree = new BinaryTree(root);
        tree.bfs(root);
        tree.dfs(root);
        tree.dfsRecursive(root);
    }
}
