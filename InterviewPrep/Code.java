import java.util.LinkedHashMap;
import java.util.Map;

public class Code extends LinkedHashMap<String, Integer>{

    private int capacity;

    Code(int capacity){
        super(capacity, 0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<String, Integer> eldest) {
        return size() > capacity;
    }



    public static void main(String[] args) {
        Code code = new Code(3);
        code.put("A", 1);
        code.put("B", 2);
        code.put("C", 3);
        code.put("D", 4);
        
        Integer integer = code.get("A");
        System.out.println(integer);

        System.out.println(code);
    }
}
