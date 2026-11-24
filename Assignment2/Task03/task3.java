import java.util.*;

class Student {
    private final String regNo;
    private final String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    public String getRegNo() { return regNo; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() { return regNo.hashCode(); }

    @Override
    public String toString() { return regNo + " " + name; }
}

public class Task3 {
    public static void main(String[] args) {
        Set<Student> unique = new HashSet<>();
        unique.add(new Student("192311001", "Arun"));
        unique.add(new Student("192311002", "Divya"));
        unique.add(new Student("192311001", "Arun"));

        System.out.println("Unique students: " + unique.size());
        Set<Student> sorted = new TreeSet<>(
            Comparator.comparing(Student::getRegNo));
        sorted.addAll(unique);
        System.out.println("Students by registration number: " + sorted);
    }
}
