package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.Order;

import java.util.ArrayList;

public interface BreadOrderPageLogic {
    ArrayList<BreadOrderPageModel> getAvailableBreadItems();
    ArrayList<Order> getOrdersFromJson(String json) throws JsonProcessingException;
}
