package model;

public class Product {
    public long id;
    public String productName;
    public String productDescription;
    public long productPrice;
    public long productStock;
    public long productStatus;
    public long ownerID;

    public Product(long id, String productName, long productPrice, String productDescription,
                   long productStock, long productStatus, long ownerID) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStock = productStock;
        this.productStatus = productStatus;
        this.ownerID = ownerID;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public long getProductStock() {
        return productStock;
    }

    public long getProductStatus() {
        return productStatus;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductStock(long productStock) {
        this.productStock = productStock;
    }

    public void setProductStatus(long productStatus) {
        this.productStatus = productStatus;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
