import java.sql.*;
import java.nio.file.*;
import java.util.*;

public class Task9 {
    static Connection connect() throws SQLException {
        return DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"),System.getenv("DB_PASSWORD"));
    }
    static void migrate(Path csv) throws Exception {
        try(Connection c=connect()) {
            c.setAutoCommit(false);
            try(PreparedStatement p=c.prepareStatement("INSERT INTO bus_pass(pass_id) VALUES(?)")) {
                int lineNo=0;
                for(String line:Files.readAllLines(csv)) {
                    lineNo++; String id=line.trim();
                    if(id.isEmpty()){System.out.println("Invalid line "+lineNo);continue;}
                    p.setString(1,id);p.addBatch();
                }
                p.executeBatch();c.commit();
            } catch(Exception e){c.rollback();throw e;}
        }
    }
    static void metadata() throws SQLException {
        try(Connection c=connect()) {
            DatabaseMetaData m=c.getMetaData();
            System.out.println("Database: "+m.getDatabaseProductName()+" "+m.getDatabaseProductVersion());
            System.out.println("Driver: "+m.getDriverName()+" "+m.getDriverVersion());
        }
    }
    public static void main(String[] args) throws Exception {
        Path file=Path.of(args.length==0?"passes.csv":args[0]);
        if(!Files.exists(file)) Files.writeString(file,"P2001\nP2002\n");
        migrate(file); metadata();
    }
}