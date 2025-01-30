package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic;

import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.ReserveDetailPageModel;


public interface ReserveDetailPageLogic {
    ReserveDetailPageModel getReserveDetail(String reserveId);
}
