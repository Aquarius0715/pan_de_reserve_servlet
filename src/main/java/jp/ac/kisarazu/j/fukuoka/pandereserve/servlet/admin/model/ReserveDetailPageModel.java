package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;

import java.util.ArrayList;

public class ReserveDetailPageModel {
    private String customerName;
    private String customerPhoneNumber;
    private String receiveTime;
    private ArrayList<Order> orders;

    public ReserveDetailPageModel() {
    }

    public ReserveDetailPageModel(String customerName, String customerPhoneNumber, String receiveTime, ArrayList<Order> orders) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.receiveTime = receiveTime;
        this.orders = orders;
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

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}

