package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model;

public class BreadOrderConfirmPageModel {
    private String customerName;
    private String customerPhoneNumber;
    private String receiveTime;
    private String orderJson;

    public BreadOrderConfirmPageModel() {
    }

    public BreadOrderConfirmPageModel(String customerName, String customerPhoneNumber, String receiveTime, String orderJson) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.receiveTime = receiveTime;
        this.orderJson = orderJson;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getOrderJson() {
        return orderJson;
    }

    public void setOrderJson(String orderJson) {
        this.orderJson = orderJson;
    }
}
