package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDTO;

import java.util.ArrayList;

public interface ReservationDAO {
    ArrayList<ReservationDTO> loadAllReservations();
    ArrayList<ReservationDTO> loadAllReservationsByReceiveStatus(ReceiveStatus receiveStatus);
    ReservationDTO loadReservationByReservationId(String reservationId);
    boolean storeReservation(ReservationDTO reservationDTO);
    boolean updateReservationStatus(ReceiveStatus receiveStatus, String reservationId);
}
