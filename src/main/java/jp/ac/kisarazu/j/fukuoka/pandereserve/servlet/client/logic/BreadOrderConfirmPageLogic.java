package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.client.model.BreadOrderConfirmPageModel;

public interface BreadOrderConfirmPageLogic {
    boolean storeReservation(BreadOrderConfirmPageModel model) throws JsonProcessingException;
}
