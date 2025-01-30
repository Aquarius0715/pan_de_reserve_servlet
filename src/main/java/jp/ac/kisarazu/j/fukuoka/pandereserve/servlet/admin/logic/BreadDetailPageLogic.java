package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic;

import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadDetailPageModel;

public interface BreadDetailPageLogic {
    BreadDetailPageModel getBreadDetail(int breadId);
    boolean updateBreadDetail(BreadDetailPageModel breadDetailPageModel);
}
