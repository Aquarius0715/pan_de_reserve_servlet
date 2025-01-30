package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;

public class Order {
    private String breadName;
    private int quantity;

    public Order() {
    }

    public Order(String breadName, int quantity) {
        this.breadName = breadName;
        this.quantity = quantity;
    }

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
