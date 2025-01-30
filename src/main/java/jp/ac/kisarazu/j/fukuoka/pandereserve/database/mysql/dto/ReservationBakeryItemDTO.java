package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto;

public class ReservationBakeryItemDTO {
    private String reservationItemName;
    private int reservationItemQuantity;

    public ReservationBakeryItemDTO() {
    }

    public ReservationBakeryItemDTO(String reservationItemName, int reservationItemQuantity) {
        this.reservationItemName = reservationItemName;
        this.reservationItemQuantity = reservationItemQuantity;
    }

    public String getReservationItemName() {
        return reservationItemName;
    }

    public void setReservationItemName(String reservationItemName) {
        this.reservationItemName = reservationItemName;
    }

    public int getReservationItemQuantity() {
        return reservationItemQuantity;
    }

    public void setReservationItemQuantity(int reservationItemQuantity) {
        this.reservationItemQuantity = reservationItemQuantity;
    }
}
