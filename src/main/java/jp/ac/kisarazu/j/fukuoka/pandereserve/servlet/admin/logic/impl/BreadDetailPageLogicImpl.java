package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.BakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.BakeryItemDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.BakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.BreadDetailPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadDetailPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;

public class BreadDetailPageLogicImpl extends PageManagerImpl implements BreadDetailPageLogic {
    @Override
    public BreadDetailPageModel getBreadDetail(int breadId) {
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        BakeryItemDTO bakeryItemDTO = bakeryItemDAO.loadBakeryItem(breadId);
        BreadDetailPageModel breadDetailPageModel = new BreadDetailPageModel();
        breadDetailPageModel.setId(bakeryItemDTO.getBakeryItemId());
        breadDetailPageModel.setBreadName(bakeryItemDTO.getBakeryItemName());
        breadDetailPageModel.setBreadDescription(bakeryItemDTO.getBakeryItemDescription());
        breadDetailPageModel.setBreadPrice(bakeryItemDTO.getBakeryItemPrice());
        breadDetailPageModel.setBreadImage(bakeryItemDTO.getBakeryItemImage());
        return breadDetailPageModel;
    }

    @Override
    public boolean updateBreadDetail(BreadDetailPageModel breadDetailPageModel) {
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        BakeryItemDTO bakeryItemDTO = new BakeryItemDTO();
        bakeryItemDTO.setBakeryItemId(breadDetailPageModel.getId());
        bakeryItemDTO.setBakeryItemName(breadDetailPageModel.getBreadName());
        bakeryItemDTO.setBakeryItemDescription(breadDetailPageModel.getBreadDescription());
        bakeryItemDTO.setBakeryItemPrice(breadDetailPageModel.getBreadPrice());
        bakeryItemDTO.setBakeryItemImage(breadDetailPageModel.getBreadImage());
        return bakeryItemDAO.updateBakeryItem(bakeryItemDTO);
    }
}
