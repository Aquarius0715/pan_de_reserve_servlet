package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;

import java.sql.Timestamp;

public class ReservationDTO {
    private String reservationId;
    private String customerName;
    private String customerPhoneNumber;
    private Timestamp receiveDateTime;
    private ReceiveStatus receiveStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ReservationDTO(String reservationId, String customerName, String customerPhoneNumber, Timestamp receiveDateTime, ReceiveStatus receiveStatus, Timestamp createdAt, Timestamp updatedAt) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.receiveDateTime = receiveDateTime;
        this.receiveStatus = receiveStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ReservationDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Timestamp getReceiveDateTime() {
        return receiveDateTime;
    }

    public void setReceiveDateTime(Timestamp receiveDateTime) {
        this.receiveDateTime = receiveDateTime;
    }

    public ReceiveStatus getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(ReceiveStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
