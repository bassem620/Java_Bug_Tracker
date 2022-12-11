package bug_tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
    static Connection con;
    static String connectionString = "jdbc:sqlserver://BASSEM:1433;databaseName=bug_tracker;encrypt=true;trustServerCertificate=true";
    
    public static Connection getConnection() throws Exception{
        try{
            con = DriverManager.getConnection(connectionString,"sa","lenovoDatabase");
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        return con;
    }
    
}
