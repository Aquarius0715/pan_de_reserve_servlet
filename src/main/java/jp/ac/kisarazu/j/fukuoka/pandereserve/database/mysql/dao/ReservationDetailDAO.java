package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDetailDTO;

import java.util.ArrayList;

public interface ReservationDetailDAO {
    boolean storeReservationDetails(ArrayList<ReservationDetailDTO> reservationDetailDTOS);
}
