package DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Customer;

public class CustomerDAO {

    private static final String LOGIN_SQL = "SELECT * FROM customer WHERE userEmail = ? AND userPassword = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer (userEmail, userPassword) VALUES (?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET userEmail=?, userPassword=? WHERE id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";

    public static void checkTableIsExists() {
        try (Connection conn = ConnnectionManager.getConnection();
                Statement stmt = conn.createStatement();) {

            DatabaseMetaData metaData = conn.getMetaData();

            if (!doesTableExist(metaData, "customer")) {
                executeSql();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean doesTableExist(DatabaseMetaData metaData, String tableName) throws SQLException {
        try (ResultSet tables = metaData.getTables(null, null, tableName.toUpperCase(), null)) {
            return tables.next();
        }
    }

    private static void executeSql() {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(DBInfo.INITIAL_SQL);) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer login(String userEmail, String userPassword) {
        checkTableIsExists();
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(LOGIN_SQL);) {

            pstmt.setString(1, userEmail);
            pstmt.setString(2, userPassword);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                long id = rs.getLong("id");
                return new Customer(id, userEmail, userPassword);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean insert(Customer customer) {
        checkTableIsExists();
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT_CUSTOMER);) {
            pstmt.setString(1, customer.getUserEmail());
            pstmt.setString(2, customer.getUserPassword());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Customer customer) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(UPDATE_CUSTOMER);) {

            pstmt.setString(1, customer.getUserEmail());
            pstmt.setString(2, customer.getUserPassword());
            pstmt.setLong(3, customer.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Customer customer) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(DELETE_CUSTOMER);) {

            pstmt.setLong(1, customer.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
