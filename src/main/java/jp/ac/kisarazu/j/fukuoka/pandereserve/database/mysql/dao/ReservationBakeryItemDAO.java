package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationBakeryItemDTO;

import java.util.ArrayList;

public interface ReservationBakeryItemDAO {
    ArrayList<ReservationBakeryItemDTO> loadReservationBakeryItems(String reservationId);
}
