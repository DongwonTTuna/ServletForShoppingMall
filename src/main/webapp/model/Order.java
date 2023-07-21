package model;

public class Order {
    public long id;
    public long userID;
    public long productID;
    public long quantity;
    public String productName;

    public Order(long id, long userID, long productID, long quantity) {
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

    public long getProductID() {
        return productID;
    }
    
    public long getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
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
}
