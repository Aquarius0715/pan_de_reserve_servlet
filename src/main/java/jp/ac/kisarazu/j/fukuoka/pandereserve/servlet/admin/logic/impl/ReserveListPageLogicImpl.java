package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.ReservationDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.ReservationDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.ReservationDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.ReserveListPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveListPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReserveListPageLogicImpl extends PageManagerImpl implements ReserveListPageLogic {
    @Override
    public ArrayList<ReserveListPageModel> getReserveList() {
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        ArrayList<ReservationDTO> reservationDTOS = reservationDAO.loadAllReservations();
        ArrayList<ReserveListPageModel> reserveListPageModels = new ArrayList<>();
        for (ReservationDTO reservationDTO : reservationDTOS) {
            ReserveListPageModel reserveListPageModel = new ReserveListPageModel();
            reserveListPageModel.setReserveId(reservationDTO.getReservationId());
            reserveListPageModel.setCustomerName(reservationDTO.getCustomerName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reserveListPageModel.setReserveTime(sdf.format(reservationDTO.getReceiveDateTime()));
            reserveListPageModel.setReceiveStatus(reservationDTO.getReceiveStatus());
            reserveListPageModels.add(reserveListPageModel);
        }
        return reserveListPageModels;      }

    @Override
    public boolean setReceiveStatus(ReceiveStatus receiveStatus, String reserveId) {
        ReservationDAO reservationDAO = new ReservationDAOImpl();
        return reservationDAO.updateReservationStatus(receiveStatus, reserveId);
    }
}
