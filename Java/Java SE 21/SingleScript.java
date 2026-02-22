public class SingleScript {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(10);
        System.out.println(linkedList.head.next);
        Node node = linkedList.head.next;
        System.out.println(node);
    }

    
}
class LinkedList{
    Node head;
    Node tail; 
    int length;
    
    public LinkedList(int value){
        Node node = new Node(value);
        head = node;
        tail = node;
        length = 1;
    }
}

class Node{
    int value;
    Node next;

    public Node(int value){
        this.value = value;
    }
}