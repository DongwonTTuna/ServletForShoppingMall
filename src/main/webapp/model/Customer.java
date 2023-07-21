package model;

public class Customer {
    public long id;
    public String userEmail;
    public String userPassword;

    public Customer(long id, String userEmail, String userPassword) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setId(long id) {
        this.id = id;
    }
}
