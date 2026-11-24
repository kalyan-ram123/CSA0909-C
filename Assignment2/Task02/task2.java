import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        String[] songs = {"Song A", "Song B", "Song C"};
        for (String song : songs) {
            arrayList.add(song);
            linkedList.add(song);
        }

        arrayList.add(1, "New Song");
        linkedList.add(1, "New Song");
        System.out.println("ArrayList playlist: " + arrayList);
        System.out.println("LinkedList playlist: " + linkedList);
        System.out.println("ArrayList item at index 2: " + arrayList.get(2));

        Deque<String> upNext = new ArrayDeque<>();
        upNext.addLast("Song D");
        upNext.addLast("Song E");
        upNext.addFirst("Priority Song");
        System.out.println("Up Next queue: " + upNext);
        System.out.println("Playing: " + upNext.removeFirst());
        System.out.println("Remaining queue: " + upNext);
    }
}
