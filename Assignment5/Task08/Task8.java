import java.sql.*;
import java.util.*;

public class Task8 {
    static Connection connect() throws SQLException {
        return DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"),System.getenv("DB_PASSWORD"));
    }
    static void batchInsert(List<String> ids) throws SQLException {
        try(Connection c=connect()) {
            c.setAutoCommit(false);
            try(PreparedStatement p=c.prepareStatement("INSERT INTO bus_pass(pass_id) VALUES(?)")) {
                for(String id:ids){p.setString(1,id);p.addBatch();}
                p.executeBatch(); c.commit();
            } catch(SQLException e){c.rollback();throw e;}
        }
    }
    public static void main(String[] args) {
        System.out.println("Batch insert method ready. Create bus_pass(pass_id VARCHAR(30) PRIMARY KEY) first.");
        System.out.println("Compare repeated single inserts with addBatch using the same sample dataset.");
    }
}