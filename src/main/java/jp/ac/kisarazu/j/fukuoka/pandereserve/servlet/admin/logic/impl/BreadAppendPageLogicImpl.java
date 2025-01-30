package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.BakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.BakeryItemDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.BakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.BreadAppendPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadAppendPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;

public class BreadAppendPageLogicImpl extends PageManagerImpl implements BreadAppendPageLogic {

    @Override
    public boolean appendBread(BreadAppendPageModel model) {
        BakeryItemDTO bakeryItemDTO = new BakeryItemDTO();
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        bakeryItemDTO.setBakeryItemName(model.getBreadName());
        bakeryItemDTO.setBakeryItemPrice(model.getBreadPrice());
        bakeryItemDTO.setBakeryItemDescription(model.getBreadDescription());
        bakeryItemDTO.setBakeryItemImage(model.getBreadImage());
        return bakeryItemDAO.storeBakeryItem(bakeryItemDTO);
    }
}
