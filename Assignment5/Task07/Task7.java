import java.sql.*;

public class Task7 {
    static Connection connect() throws SQLException {
        return DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"),System.getenv("DB_PASSWORD"));
    }
    static boolean safeLogin(String username,String passwordHash) throws SQLException {
        String sql="SELECT 1 FROM users WHERE username=? AND password_hash=?";
        try(Connection c=connect(); PreparedStatement p=c.prepareStatement(sql)) {
            p.setString(1,username); p.setString(2,passwordHash);
            try(ResultSet r=p.executeQuery()){return r.next();}
        }
    }
    public static void main(String[] args) {
        System.out.println("Safe login method is ready. Test only with your own local database and test accounts.");
        System.out.println("PreparedStatement treats user input as data, not executable SQL.");
    }
}