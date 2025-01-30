package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationBakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationBakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl.MySQLManagerImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBakeryItemDAOImpl implements ReservationBakeryItemDAO {
    @Override
    public ArrayList<ReservationBakeryItemDTO> loadReservationBakeryItems(String reservationId) {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadReservationBakeryItems");
        ArrayList<ReservationBakeryItemDTO> reservationBakeryItems = new ArrayList<>();
        String sql = "SELECT b.bakery_item_name, r.quantity FROM reservation_details r " +
                "JOIN bakery_items b ON r.bakery_item_id = b.bakery_item_id " +
                "WHERE r.reservation_id = '" +
                manager.escapeString(reservationId) + "';";
        try {
            ResultSet resultSet = manager.query(sql);
            while (resultSet.next()) {
                ReservationBakeryItemDTO reservationBakeryItemDTO = new ReservationBakeryItemDTO();
                reservationBakeryItemDTO.setReservationItemName(resultSet.getString("bakery_item_name"));
                reservationBakeryItemDTO.setReservationItemQuantity(resultSet.getInt("quantity"));
                reservationBakeryItems.add(reservationBakeryItemDTO);
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return reservationBakeryItems;
    }
}
