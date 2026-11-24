import java.sql.*;

public class Task5 {
    static Connection connect() throws SQLException {
        return DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"),System.getenv("DB_PASSWORD"));
    }
    static void transfer(int from, int to, double amount) throws SQLException {
        try(Connection c=connect()) {
            c.setAutoCommit(false);
            try {
                try(PreparedStatement debit=c.prepareStatement(
                        "UPDATE wallet SET balance=balance-? WHERE wallet_id=? AND balance>=?");
                    PreparedStatement credit=c.prepareStatement(
                        "UPDATE wallet SET balance=balance+? WHERE wallet_id=?")) {
                    debit.setDouble(1,amount); debit.setInt(2,from); debit.setDouble(3,amount);
                    if(debit.executeUpdate()!=1) throw new SQLException("Insufficient funds or source wallet missing");
                    credit.setDouble(1,amount); credit.setInt(2,to);
                    if(credit.executeUpdate()!=1) throw new SQLException("Destination wallet missing");
                }
                c.commit();
            } catch(SQLException e) { c.rollback(); throw e; }
        }
    }
    static boolean login(String user,String passwordHash) throws SQLException {
        try(Connection c=connect(); PreparedStatement p=c.prepareStatement(
                "SELECT 1 FROM users WHERE username=? AND password_hash=?")) {
            p.setString(1,user); p.setString(2,passwordHash);
            try(ResultSet r=p.executeQuery()){return r.next();}
        }
    }
    public static void main(String[] args) {
        System.out.println("Use transfer(fromId,toId,amount) and login(username,passwordHash) after configuring the database.");
    }
}