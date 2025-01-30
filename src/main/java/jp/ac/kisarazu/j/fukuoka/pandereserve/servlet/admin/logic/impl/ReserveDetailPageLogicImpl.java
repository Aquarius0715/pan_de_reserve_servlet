package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationBakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.ReservationBakeryItemDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.ReservationDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationBakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.ReserveDetailPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.Order;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveDetailPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReserveDetailPageLogicImpl extends PageManagerImpl implements ReserveDetailPageLogic {
    @Override
    public ReserveDetailPageModel getReserveDetail(String reserveId) {
        ReserveDetailPageModel model = new ReserveDetailPageModel();

        ReservationDAO reservationDAO = new ReservationDAOImpl();
        ReservationDTO reservationDTO = reservationDAO.loadReservationByReservationId(reserveId);
        model.setCustomerName(reservationDTO.getCustomerName());
        model.setCustomerPhoneNumber(reservationDTO.getCustomerPhoneNumber());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.setReceiveTime(sdf.format(reservationDTO.getReceiveDateTime()));

        ArrayList<Order> orderArrayList = getOrders(reserveId);
        model.setOrders(orderArrayList);
        return model;
    }

    private ArrayList<Order> getOrders(String reserveId) {
        ReservationBakeryItemDAO bakeryItemDAO = new ReservationBakeryItemDAOImpl();
        ArrayList<ReservationBakeryItemDTO> orders = bakeryItemDAO.loadReservationBakeryItems(reserveId);
        ArrayList<Order> orderArrayList = new ArrayList<>();
        for (ReservationBakeryItemDTO reservationBakeryItemDTO : orders) {
            Order order = new Order();
            order.setBreadName(reservationBakeryItemDTO.getReservationItemName());
            order.setQuantity(reservationBakeryItemDTO.getReservationItemQuantity());
            orderArrayList.add(order);
        }
        return orderArrayList;
    }
}
