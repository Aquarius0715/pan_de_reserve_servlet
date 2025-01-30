package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model;


import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;

public class ReserveListPageModel {
    private String reserveId;
    private String reserveTime;
    private String customerName;
    private ReceiveStatus receiveStatus;

    public ReserveListPageModel() {
    }

    public ReserveListPageModel(String reserveId, String reserveTime, String customerName, ReceiveStatus receiveStatus) {
        this.reserveId = reserveId;
        this.reserveTime = reserveTime;
        this.customerName = customerName;
        this.receiveStatus = receiveStatus;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ReceiveStatus getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(ReceiveStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
}
