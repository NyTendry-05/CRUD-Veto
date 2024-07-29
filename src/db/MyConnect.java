package db;
import java.sql.Connection;
import java.sql.DriverManager;
public class MyConnect
{
    public static Connection getConnection ()
    {
        String url = "jdbc:postgresql://localhost:5432/animatronicv3";

        Connection connection=null;
        try {
        	connection = DriverManager.getConnection(url, "postgres", "1234");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return connection;
    }
}