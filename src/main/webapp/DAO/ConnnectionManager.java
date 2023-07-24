package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnnectionManager {

    private static final String DB_URL = "jdbc:mysql://db:3306/marketDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootpass";
    private static Connection connection = null;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: H2 JDBC driver not found.");
        } catch (SQLException e) {
            System.err.println("Error: Unable to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }
}
