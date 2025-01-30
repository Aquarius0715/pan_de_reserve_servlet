package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.BakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.BakeryItemDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.BakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic.BreadOrderPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.Order;

import java.util.ArrayList;

public class BreadOrderPageLogicImpl implements BreadOrderPageLogic {

    @Override
    public ArrayList<BreadOrderPageModel> getAvailableBreadItems() {
        BakeryItemDAO bakeryItemDAO = new BakeryItemDAOImpl();
        ArrayList<BakeryItemDTO> bakeryItemDTOS = bakeryItemDAO.loadAvailableBakeryItems();
        ArrayList<BreadOrderPageModel> breadOrderPageModels = new ArrayList<>();
        for (BakeryItemDTO bakeryItemDTO : bakeryItemDTOS) {
            BreadOrderPageModel breadOrderPageModel = new BreadOrderPageModel();
            breadOrderPageModel.setId(bakeryItemDTO.getBakeryItemId());
            breadOrderPageModel.setBreadName(bakeryItemDTO.getBakeryItemName());
            breadOrderPageModel.setBreadPrice(bakeryItemDTO.getBakeryItemPrice());
            breadOrderPageModel.setBreadDescription(bakeryItemDTO.getBakeryItemDescription());
            breadOrderPageModel.setBreadImage(bakeryItemDTO.getBakeryItemImage());
            breadOrderPageModels.add(breadOrderPageModel);
        }
        return breadOrderPageModels;
    }

    @Override
    public ArrayList<Order> getOrdersFromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Order.class));
    }
}
