package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model;

public class Order {
    private int id;
    private int quantity;
    private String breadName;
    private int breadPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public int getBreadPrice() {
        return breadPrice;
    }

    public void setBreadPrice(int breadPrice) {
        this.breadPrice = breadPrice;
    }
}
