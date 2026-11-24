import java.util.*;

class Team {
    String name;
    int points;
    int goalDifference;

    Team(String name, int points, int goalDifference) {
        this.name = name;
        this.points = points;
        this.goalDifference = goalDifference;
    }

    public String toString() {
        return name + "(" + points + "," + goalDifference + ")";
    }
}

class Leaderboard<T> {
    private final List<T> entries = new ArrayList<>();

    void add(T item) { entries.add(item); }

    List<T> ranked(Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>(entries);
        result.sort(comparator);
        return result;
    }
}

public class Task5 {
    public static void main(String[] args) {
        Leaderboard<Team> board = new Leaderboard<>();
        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        Comparator<Team> ranking = Comparator
            .comparingInt((Team t) -> t.points).reversed()
            .thenComparing(Comparator.comparingInt(
                (Team t) -> t.goalDifference).reversed())
            .thenComparing(t -> t.name);

        System.out.println("Leaderboard: " + board.ranked(ranking));
    }
}
