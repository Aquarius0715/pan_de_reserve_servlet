package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadListPageModel;

import java.util.ArrayList;

public interface BreadListPageLogic {
    ArrayList<BreadListPageModel> getBreadListPageModel();
    boolean toggleSalesStatus(int id, SalesStatus currentSalesStatus);
    boolean deleteBreadItem(int id);
}
