import java.sql.*;
import java.util.*;

public class Task3 {
    record Student(int id, String registerNo, String name, String email) {}
    interface Dao<T,K> {
        void insert(T value) throws SQLException;
        Optional<T> findById(K id) throws SQLException;
        List<T> findAll() throws SQLException;
    }
    static Connection connect() throws SQLException {
        return DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }
    static class StudentDao implements Dao<Student,Integer> {
        public void insert(Student s) throws SQLException {
            try (Connection c=connect(); PreparedStatement p=c.prepareStatement(
                    "INSERT INTO student(student_id,register_no,student_name,email) VALUES(?,?,?,?)")) {
                p.setInt(1,s.id()); p.setString(2,s.registerNo()); p.setString(3,s.name()); p.setString(4,s.email()); p.executeUpdate();
            }
        }
        public Optional<Student> findById(Integer id) throws SQLException {
            try (Connection c=connect(); PreparedStatement p=c.prepareStatement("SELECT * FROM student WHERE student_id=?")) {
                p.setInt(1,id); try (ResultSet r=p.executeQuery()) {
                    if (!r.next()) return Optional.empty();
                    return Optional.of(new Student(r.getInt("student_id"),r.getString("register_no"),r.getString("student_name"),r.getString("email")));
                }
            }
        }
        public List<Student> findAll() throws SQLException {
            List<Student> out=new ArrayList<>();
            try (Connection c=connect(); Statement s=c.createStatement(); ResultSet r=s.executeQuery("SELECT * FROM student")) {
                while(r.next()) out.add(new Student(r.getInt(1),r.getString(2),r.getString(3),r.getString(4)));
            }
            return out;
        }
    }
    public static void main(String[] args) throws Exception {
        try (Connection c=connect(); Statement s=c.createStatement()) {
            s.executeUpdate("CREATE TABLE IF NOT EXISTS student(student_id INT PRIMARY KEY, register_no VARCHAR(30) UNIQUE, student_name VARCHAR(100), email VARCHAR(120))");
        }
        System.out.println("Student table is ready. Use StudentDao.insert/findById/findAll from your application.");
    }
}