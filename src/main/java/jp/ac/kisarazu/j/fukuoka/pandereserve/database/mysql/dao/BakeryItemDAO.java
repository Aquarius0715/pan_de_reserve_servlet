package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.BakeryItemDTO;

import java.util.ArrayList;

public interface BakeryItemDAO {
    boolean storeBakeryItem(BakeryItemDTO bakeryItemDTO);
    ArrayList<BakeryItemDTO> loadBakeryItems();
    ArrayList<BakeryItemDTO> loadAvailableBakeryItems();
    BakeryItemDTO loadBakeryItem(int id);
    boolean deleteBakeryItem(int id);
    boolean updateBakeryItem(BakeryItemDTO bakeryItemDTO);
    boolean updateSalesStatus(int id, SalesStatus salesStatus);
}
