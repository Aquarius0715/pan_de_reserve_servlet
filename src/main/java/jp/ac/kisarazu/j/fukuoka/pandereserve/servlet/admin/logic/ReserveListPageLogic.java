package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.ReceiveStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveListPageModel;

import java.util.ArrayList;

public interface ReserveListPageLogic {
    ArrayList<ReserveListPageModel> getReserveList();
    boolean setReceiveStatus(ReceiveStatus receiveStatus, String reserveId);
}
