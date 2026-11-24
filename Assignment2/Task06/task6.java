import java.util.*;

class Patient {
    String name;
    int severity;
    int arrival;

    Patient(String name, int severity, int arrival) {
        this.name = name;
        this.severity = severity;
        this.arrival = arrival;
    }

    public String toString() { return name + "(S" + severity + ")"; }
}

public class Task6 {
    public static void main(String[] args) {
        Comparator<Patient> order = Comparator
            .comparingInt((Patient p) -> p.severity)
            .thenComparingInt(p -> p.arrival);

        Queue<Patient> triage = new PriorityQueue<>(order);
        triage.add(new Patient("Ravi", 3, 1));
        triage.add(new Patient("Meena", 1, 2));
        triage.add(new Patient("Kumar", 1, 3));
        triage.add(new Patient("Lata", 2, 4));
        triage.add(new Patient("Asha", 5, 5));

        System.out.println("Treatment order:");
        while (!triage.isEmpty()) {
            System.out.print(triage.poll() + " ");
        }
        System.out.println();
    }
}
