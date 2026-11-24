import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();

        String[][] stock = {
            {"M103", "25"}, {"M101", "40"}, {"M105", "12"}, {"M102", "30"}
        };

        for (String[] item : stock) {
            int quantity = Integer.parseInt(item[1]);
            hashMap.put(item[0], quantity);
            treeMap.put(item[0], quantity);
            linkedMap.put(item[0], quantity);
        }

        System.out.println("HashMap: " + hashMap);
        System.out.println("Code-sorted report: " + treeMap);
        System.out.println("Insertion-order report: " + linkedMap);
        System.out.println("Stock for M101: " + treeMap.get("M101"));
    }
}
