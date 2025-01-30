package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl.MySQLManagerImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public ArrayList<ReservationDTO> loadAllReservations() {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadAllReservations");
        ArrayList<ReservationDTO> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations ORDER BY receive_time ASC;";
        try {
            ResultSet resultSet = manager.query(sql);
            while (resultSet.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(resultSet.getString("reservation_id"));
                reservation.setCustomerName(resultSet.getString("customer_name"));
                reservation.setCustomerPhoneNumber(resultSet.getString("customer_phone_number"));
                reservation.setReceiveDateTime(resultSet.getTimestamp("receive_time"));
                reservation.setReceiveStatus(ReceiveStatus.valueOf(resultSet.getString("status").toUpperCase()));
                reservations.add(reservation);
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return reservations;
    }

    @Override
    public ArrayList<ReservationDTO> loadAllReservationsByReceiveStatus(ReceiveStatus receiveStatus) {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadAllReservationsByReceiveStatus");
        ArrayList<ReservationDTO> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE status = '";
        sql += manager.escapeString(receiveStatus.name()) + "';";
        try {
            ResultSet resultSet = manager.query(sql);
            while (resultSet.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(resultSet.getString("reservation_id"));
                reservation.setCustomerName(resultSet.getString("customer_name"));
                reservation.setCustomerPhoneNumber(resultSet.getString("customer_phone_number"));
                reservation.setReceiveDateTime(resultSet.getTimestamp("receive_time"));
                reservation.setReceiveStatus(ReceiveStatus.valueOf(resultSet.getString("status").toUpperCase()));
                reservations.add(reservation);
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return reservations;
    }

    @Override
    public ReservationDTO loadReservationByReservationId(String reservationId) {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadReservationByReservationId");
        ReservationDTO reservationDTO = new ReservationDTO();
        String sql = "SELECT * FROM reservations WHERE reservation_id = '" + reservationId + "';";
        try {
            ResultSet resultSet = manager.query(sql);
            if (resultSet.next()) {
                reservationDTO.setReservationId(resultSet.getString("reservation_id"));
                reservationDTO.setCustomerName(resultSet.getString("customer_name"));
                reservationDTO.setCustomerPhoneNumber(resultSet.getString("customer_phone_number"));
                reservationDTO.setReceiveDateTime(resultSet.getTimestamp("receive_time"));
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return reservationDTO;
    }

    @Override
    public boolean storeReservation(ReservationDTO reservationDTO) {
        MySQLManagerImpl manager = new MySQLManagerImpl("storeReservation");
        String sql = "INSERT INTO reservations(reservation_id, customer_name, customer_phone_number, receive_time) VALUES ('";
        sql += manager.escapeString(reservationDTO.getReservationId()) + "', '";
        sql += manager.escapeString(reservationDTO.getCustomerName()) + "', '";
        sql += manager.escapeString(reservationDTO.getCustomerPhoneNumber()) + "', '";
        sql += reservationDTO.getReceiveDateTime() + "');";
        return manager.execute(sql);
    }

    @Override
    public boolean updateReservationStatus(ReceiveStatus receiveStatus, String reservationId) {
        MySQLManagerImpl manager = new MySQLManagerImpl("updateReservationStatus");
        String sql = "UPDATE reservations SET status = '" + receiveStatus.name() + "' WHERE reservation_id = '" + reservationId + "';";
        return manager.execute(sql);
    }
}
