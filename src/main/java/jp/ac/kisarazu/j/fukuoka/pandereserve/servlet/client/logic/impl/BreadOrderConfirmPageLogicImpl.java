package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationDetailDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.ReservationDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.ReservationDetailDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDetailDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.BreadOrderConfirmPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderConfirmPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.Order;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class BreadOrderConfirmPageLogicImpl implements BreadOrderConfirmPageLogic {
    @Override
    public boolean storeReservation(BreadOrderConfirmPageModel model) throws JsonProcessingException {
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        ReservationDTO reservationDTO = new ReservationDTO();

        String reservationId = UUID.randomUUID().toString();
        String customerName = model.getCustomerName();
        String customerPhoneNumber = model.getCustomerPhoneNumber();
        Timestamp receiveTime = getTimestamp(model.getReceiveTime());

        reservationDTO.setReservationId(reservationId);
        reservationDTO.setCustomerName(customerName);
        reservationDTO.setCustomerPhoneNumber(customerPhoneNumber);
        reservationDTO.setReceiveDateTime(receiveTime);

        boolean reservationDAOResult = reservationDAO.storeReservation(reservationDTO);

        ReservationDetailDAO reservationDetailDAO = new ReservationDetailDAOImpl();
        ArrayList<ReservationDetailDTO> reservationDetailDTOs = new ArrayList<>();

        ArrayList<Order> orders = getOrders(model.getOrderJson());
        for (Order order : orders) {
            ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO();
            reservationDetailDTO.setReservationId(reservationId);
            reservationDetailDTO.setBakeryItemId(order.getId());
            reservationDetailDTO.setQuantity(order.getQuantity());
            reservationDetailDTOs.add(reservationDetailDTO);
        }
        boolean reservationDetailDAOResult = reservationDetailDAO.storeReservationDetails(reservationDetailDTOs);

        return reservationDAOResult && reservationDetailDAOResult;
    }

    private ArrayList<Order> getOrders(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Order.class));
    }

    private Timestamp getTimestamp(String dateRaw) {
        Timestamp timestamp = null;
        try {
            // datetime-local の入力形式を指定
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            // LocalDateTime に変換
            LocalDateTime visitDateTime = LocalDateTime.parse(dateRaw, inputFormatter);
            // LocalDateTime を Timestamp に変換
            timestamp = Timestamp.valueOf(visitDateTime);
        } catch (Exception e) {
            e.fillInStackTrace(); // エラーが発生した場合のデバッグ用
        }
        return timestamp;
    }
}
