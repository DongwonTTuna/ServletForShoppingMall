package model;
public class Cart {
    public long id;
    public long userID;
    public long productID;
    public String productName;
    public long productPrice;
    public long productStock;
    public long productStatus;
    public long quantity;

    public Cart(long id, long userID, long productID, long quantity) {
        this.id = id;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return userID;
    }

    public String getProductName() {
        return productName;
    }

    public long getProductID() {
        return productID;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public long getProductStock() {
        return productStock;
    }

    public long getProductStatus() {
        return productStatus;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductStock(long productStock) {
        this.productStock = productStock;
    }

    public void setProductStatus(long productStatus) {
        this.productStatus = productStatus;
    }

    public Order toOrder() {
        return new Order(0, userID, productID, quantity);
    }
}
