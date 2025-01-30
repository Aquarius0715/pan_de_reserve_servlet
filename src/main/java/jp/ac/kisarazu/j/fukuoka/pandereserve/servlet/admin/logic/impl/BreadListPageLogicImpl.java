package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.BakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.BakeryItemDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.BakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.BreadListPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.BreadListPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;

import java.util.ArrayList;

public class BreadListPageLogicImpl extends PageManagerImpl implements BreadListPageLogic {

    @Override
    public ArrayList<BreadListPageModel> getBreadListPageModel() {
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        ArrayList<BakeryItemDTO> bakeryItemDTOS = bakeryItemDAO.loadBakeryItems();
        ArrayList<BreadListPageModel> breadListPageModels = new ArrayList<>();
        for (BakeryItemDTO bakeryItemDTO : bakeryItemDTOS) {
            BreadListPageModel breadListPageModel = getBreadListPageModel(bakeryItemDTO);
            breadListPageModels.add(breadListPageModel);
        }
        return breadListPageModels;
    }

    @Override
    public boolean toggleSalesStatus(int id, SalesStatus currentSalesStatus) {
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        if (currentSalesStatus == SalesStatus.AVAILABLE) {
            return bakeryItemDAO.updateSalesStatus(id, SalesStatus.UNAVAILABLE);
        } else {
            return bakeryItemDAO.updateSalesStatus(id, SalesStatus.AVAILABLE);
        }
    }

    @Override
    public boolean deleteBreadItem(int id) {
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        return bakeryItemDAO.deleteBakeryItem(id);
    }

    private BreadListPageModel getBreadListPageModel(BakeryItemDTO bakeryItemDTO) {
        BreadListPageModel breadListPageModel = new BreadListPageModel();
        breadListPageModel.setBreadName(bakeryItemDTO.getBakeryItemName());
        breadListPageModel.setBreadDescription(bakeryItemDTO.getBakeryItemDescription());
        breadListPageModel.setBreadPrice(bakeryItemDTO.getBakeryItemPrice());
        breadListPageModel.setId(bakeryItemDTO.getBakeryItemId());
        breadListPageModel.setBreadImage(bakeryItemDTO.getBakeryItemImage());
        breadListPageModel.setSalesStatus(bakeryItemDTO.getSalesStatus());
        return breadListPageModel;
    }
}
