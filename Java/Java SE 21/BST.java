
public class BST {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.rInsert(10);
        bst.rInsert(20);
        bst.rInsert(5);
        System.out.println("Root:" + bst.root.value);
        System.out.println("Right:" + bst.root.right.value);
        System.out.println("Left:" + bst.root.left.value);
        System.out.println("Check 20:" + bst.rContains(22));
    }
}

class BinarySearchTree {
    Node root;

    public void rInsert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            rInsert(root, value);
        }
    }

    private Node rInsert(Node currNode, int value) {
        if (currNode == null)
            return new Node(value);
        if (value > currNode.value) {
            currNode.right = rInsert(currNode.right, value);
        } else if (value < currNode.value) {
            currNode.left = rInsert(currNode.left, value);
        }
        return currNode;
    }

    public boolean rContains(int value){
        if(root == null ) return false;
        Node t = rContains(root,value);
        if(t != null){
            return true;
        }else{
            return false;
        }
    }

    private Node rContains(Node currNode, int value) {
        if(currNode == null ) return null;
        if(currNode.value == value) return currNode;
        if(currNode.value > value){
            currNode.right = rInsert(currNode.right, value);
        }else{
            currNode.left = rInsert(currNode.left, value);
        }
        return currNode;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}
