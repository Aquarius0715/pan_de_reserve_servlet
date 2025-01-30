package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto;

import java.sql.Timestamp;

public class ReservationDetailDTO {
    private String reservationId;
    private int bakeryItemId;
    private int quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ReservationDetailDTO() {
    }

    public ReservationDetailDTO(String reservationId, int bakeryItemId, int quantity, Timestamp createdAt, Timestamp updatedAt) {
        this.reservationId = reservationId;
        this.bakeryItemId = bakeryItemId;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public int getBakeryItemId() {
        return bakeryItemId;
    }

    public void setBakeryItemId(int bakeryItemId) {
        this.bakeryItemId = bakeryItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
