package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Customer;
import model.Product;

public class ProductDAO {

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String SELECT_ONE_PRODUCT_BY_ID = "SELECT * FROM product WHERE id=?";
    private static final String INSERT_PRODUCT = "INSERT INTO product (productName, productPrice, productDescription, productStock, productStatus, ownerID) VALUES (?, ?, ?, ?, ?, ?)";
    // Check the userID because the product can be fetched without the userID.
    // --------------------
    private static final String UPDATE_PRODUCT_ONLY_QUANTITY = "UPDATE product SET productStock = productStock - ? WHERE id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE product SET productName=?, productPrice=?, productDescription=?, productStock=?, productStatus=?, ownerID=? WHERE id=? AND ownerID=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id=? AND ownerID=?";
    // --------------------------------------------------------------------------------------------
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PRODUCTS);){
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String productName = rs.getString("productName");
                long productPrice = rs.getLong("productPrice");
                String productDescription = rs.getString("productDescription");
                long productStock = rs.getLong("productStock");
                long productStatus = rs.getLong("productStatus");
                long ownerID = rs.getLong("ownerID");

                products.add(new Product(id, productName, productPrice, productDescription, productStock, productStatus,
                        ownerID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (products.size() == 0) {
            return null;
        }
        return products;
    }

    public static Product getProductByID(long id) {
        Product product = null;
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ONE_PRODUCT_BY_ID);){

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("productName");
                long productPrice = rs.getLong("productPrice");
                String productDescription = rs.getString("productDescription");
                long productStock = rs.getLong("productStock");
                long productStatus = rs.getLong("productStatus");
                long ownerID = rs.getLong("ownerID");

                product = new Product(id, productName, productPrice, productDescription, productStock, productStatus,
                        ownerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static boolean insert(Product product) {
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_PRODUCT);){

            pstmt.setString(1, product.getProductName());
            pstmt.setLong(2, product.getProductPrice());
            pstmt.setString(3, product.getProductDescription());
            pstmt.setLong(4, product.getProductStock());
            pstmt.setLong(5, product.getProductStatus());
            pstmt.setLong(6, product.getOwnerID());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Product product, Customer customer) {
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT);){

            pstmt.setString(1, product.getProductName());
            pstmt.setLong(2, product.getProductPrice());
            pstmt.setString(3, product.getProductDescription());
            pstmt.setLong(4, product.getProductStock());
            pstmt.setLong(5, product.getProductStatus());
            pstmt.setLong(6, product.getOwnerID());
            pstmt.setLong(7, product.getId());
            pstmt.setLong(8, customer.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateOnlyQuantity(long id, long quantity){
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT_ONLY_QUANTITY);){

            pstmt.setLong(1, quantity);
            pstmt.setLong(2, id);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(Product product, Customer customer) {
        try (Connection conn = ConnnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PRODUCT);){

            pstmt.setLong(1, product.getId());
            pstmt.setLong(2, customer.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
