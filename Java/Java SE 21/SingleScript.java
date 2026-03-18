public class SingleScript {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.rInsert(2);
        bst.rInsert(1);
        bst.rInsert(3);
    }
}

class BST {
    private Node root;

    class Node {
        public int value;
        public Node left;
        public Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    public Node getRoot() {
        return root;
    }

    private Node rInsert(Node curNode, int value) {
        if (curNode == null)
            return new Node(value);
        if (value < curNode.value) {
            curNode.left = rInsert(curNode.left, value);
        } else if (value > curNode.value) {
            curNode.right = rInsert(curNode.right, value);
        }
        return curNode;
    }

    public void rInsert(int value) {
        if (root == null)
            root = new Node(value);
        rInsert(root,value);
    }
}