package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Customer;

public class CartDAO {

    private static final String SELECT_ALL_CART = "SELECT * FROM cart WHERE userID = ?";
    private static final String SELECT_CART_BY_ID = "SELECT * FROM cart WHERE id=? AND userID = ?";
    private static final String SELECT_CART_WITH_PRODUCT_NAME = "SELECT cart.id, cart.userID, cart.productID, cart.quantity, product.productName, product.productPrice, product.productStock, product.productStatus FROM cart INNER JOIN product ON cart.productID = product.id WHERE userID = ?";
    private static final String SELECT_CART_WITH_PRODUCT_NAME_BY_CART_ID = "SELECT cart.id, cart.userID, cart.productID, cart.quantity, product.productName, product.productPrice FROM cart INNER JOIN product ON cart.productID = product.id WHERE cart.id = ? AND userID = ?";
    private static final String SELECT_CART_BY_PRODUCT_ID = "SELECT * FROM cart WHERE productID = ? AND userID = ?";
    private static final String UPDATE_CART = "UPDATE cart SET quantity=? WHERE id=?";
    private static final String INSERT_CART = "INSERT INTO cart (userID, productID, quantity) VALUES (?, ?, ?)";
    private static final String DELETE_CART = "DELETE FROM cart WHERE id=?";

    public static List<Cart> getCartsByUserID(Customer customer) {
        List<Cart> carts = new ArrayList<>();
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_CART);) {
            pstmt.setLong(1, customer.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");

                Cart cart = new Cart(id, userID, productID, quantity);
                carts.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (carts.size() == 0) {
            return null;
        }
        return carts;
    }

    public static List<Cart> getCartsWithProductInfoByUser(Customer customer) {
        List<Cart> carts = new ArrayList<>();
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SELECT_CART_WITH_PRODUCT_NAME);) {
            pstmt.setLong(1, customer.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");
                String productName = rs.getString("productName");
                long productPrice = rs.getLong("productPrice");
                long productStock = rs.getLong("productStock");
                long productStatus = rs.getLong("productStatus");

                Cart cart = new Cart(id, userID, productID, quantity);
                cart.setProductName(productName);
                cart.setProductPrice(productPrice);
                cart.setProductStock(productStock);
                cart.setProductStatus(productStatus);
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (carts.size() == 0) {
            return null;
        }
        return carts;
    }

    public static Cart getCartWithProductInfoByUserAndCartID(long cartId, Customer customer) {
        Cart cart = null;
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SELECT_CART_WITH_PRODUCT_NAME_BY_CART_ID);) {

            pstmt.setLong(1, cartId);
            pstmt.setLong(2, customer.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");
                String productName = rs.getString("productName");
                long productPrice = rs.getLong("productPrice");

                cart = new Cart(id, userID, productID, quantity);
                cart.setProductName(productName);
                cart.setProductPrice(productPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return cart;
    }

    public static Cart getCartByProductID(long id, Customer customer) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SELECT_CART_BY_PRODUCT_ID);) {

            pstmt.setLong(1, id);
            pstmt.setLong(2, customer.getId());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                long cartID = rs.getLong("id");
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");
                return new Cart(cartID, userID, productID, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cart getCartByID(long id, Customer customer) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SELECT_CART_BY_ID);) {

            pstmt.setLong(1, id);
            pstmt.setLong(2, customer.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                long userID = rs.getLong("userID");
                long productID = rs.getLong("productID");
                long quantity = rs.getLong("quantity");
                return new Cart(id, userID, productID, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insert(Cart cart) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT_CART)) {

            pstmt.setLong(1, cart.getUserID());
            pstmt.setLong(2, cart.getProductID());
            pstmt.setLong(3, cart.getQuantity());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Cart cart) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(UPDATE_CART);) {

            pstmt.setLong(1, cart.getQuantity());
            pstmt.setLong(2, cart.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Cart cart) {
        try (Connection conn = ConnnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(DELETE_CART);) {

            pstmt.setLong(1, cart.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
