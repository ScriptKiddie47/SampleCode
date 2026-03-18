import java.util.PriorityQueue;

public class C_Heap {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap2 = new PriorityQueue<>();
        int ar2[] = new int[] { 3, 2, 1, 5, 6, 4 };
        for (int i : ar2) {
            minHeap2.add(i);
        }
        System.out.println(minHeap2);
        int k = 2;
        int ar[] = new int[] { 3, 2, 1, 5, 6, 4 };
        for (int i : ar) {
            minHeap.add(i);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        System.out.println(minHeap.peek());
    }
}
