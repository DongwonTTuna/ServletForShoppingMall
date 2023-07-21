package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;

public class OrderDAO {

    private static final String SELECT_ALL_ORDER = "SELECT * FROM orders WHERE userID = ? ORDER BY id DESC";
    private static final String SELECT_ORDER = "SELECT * FROM orders WHERE id=? AND userID = ?";
    private static final String SELECT_ORDER_WITH_PRODUCT_NAME = "SELECT orders.id, orders.userID, orders.productID, orders.quantity, product.productName FROM orders INNER JOIN product ON orders.productID = product.id WHERE orders.userID = ?";
    private static final String INSERT_ORDER = "INSERT INTO orders (userID, productID, quantity) VALUES (?, ?, ?)";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";
    private static final String UPDATE_ORDER = "UPDATE orders SET userID=?, productID=?, quantity=? WHERE id=?";

    public static List<Order> getAllOrderLists(Customer customer) {
        List<Order> orderLists = new ArrayList<>();
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ORDER);){

            pstmt.setLong(1, customer.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");

                Order orderList = new Order(id, userID, productID, quantity);
                orderLists.add(orderList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (orderLists.size() == 0) {
            return null;
        }
        return orderLists;

    }

    public static List<Order> getAllOrderListsWithProductName(Customer customer) {
        List<Order> orderLists = new ArrayList<>();
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ORDER_WITH_PRODUCT_NAME);){

            pstmt.setLong(1, customer.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");
                String productName = rs.getString("productName");

                Order order = new Order(id, userID, productID, quantity);
                order.setProductName(productName);
                orderLists.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (orderLists.size() == 0) {
            return null;
        }
        return orderLists;

    }

    public static Order getOrderByID(long id, Customer customer) {
        Order order = null;
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ORDER);){

            pstmt.setLong(1, id);
            pstmt.setLong(2, customer.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");
                order = new Order(id, userID, productID, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public static boolean insert(Order order) {
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_ORDER);){

            pstmt.setLong(1, order.getUserID());
            pstmt.setLong(2, order.getProductID());
            pstmt.setLong(3, order.getQuantity());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Order order) {
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_ORDER);){

            pstmt.setLong(1, order.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Order order) {
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_ORDER);){
            pstmt.setLong(1, order.getUserID());
            pstmt.setLong(2, order.getProductID());
            pstmt.setLong(3, order.getQuantity());
            pstmt.setLong(3, order.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
