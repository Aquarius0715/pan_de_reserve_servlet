package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationDetailDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDetailDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl.MySQLManagerImpl;

import java.util.ArrayList;

public class ReservationDetailDAOImpl implements ReservationDetailDAO {

    @Override
    public boolean storeReservationDetails(ArrayList<ReservationDetailDTO> reservationDetailDTOS) {
        MySQLManagerImpl manager = new MySQLManagerImpl("storeReservationDetail");
        StringBuilder sql = new StringBuilder("INSERT INTO reservation_details (reservation_id, bakery_item_id, quantity) VALUES ");
        for (ReservationDetailDTO reservationDetailDTO : reservationDetailDTOS) {
            sql.append("('").append(manager.escapeString(reservationDetailDTO.getReservationId())).append("', ");
            sql.append(reservationDetailDTO.getBakeryItemId()).append(", ");
            sql.append(reservationDetailDTO.getQuantity()).append("),");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(";");
        return manager.execute(sql.toString());
    }
}