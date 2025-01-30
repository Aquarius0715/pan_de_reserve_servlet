package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.BakeryItemDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.struct.SalesStatus;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.BakeryItemDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl.MySQLManagerImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BakeryItemDAOImpl implements BakeryItemDAO {

    @Override
    public boolean storeBakeryItem(BakeryItemDTO bakeryItemDTO) {
        MySQLManagerImpl manager = new MySQLManagerImpl("storeBakeryItem");
        String sql = "INSERT INTO bakery_items(bakery_item_name, bakery_item_price, bakery_item_description, status, bakery_item_image) VALUES ('";
        sql += manager.escapeString(bakeryItemDTO.getBakeryItemName()) + "', ";
        sql += bakeryItemDTO.getBakeryItemPrice() + ", '";
        sql += manager.escapeString(bakeryItemDTO.getBakeryItemDescription()) + "', '";
        sql += "AVAILABLE', '";
        sql += manager.escapeString(bakeryItemDTO.getBakeryItemImage()) + "');";
        return manager.execute(sql);
    }

    @Override
    public ArrayList<BakeryItemDTO> loadBakeryItems() {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadBakeryItems");
        ArrayList<BakeryItemDTO> bakeryItems = new ArrayList<>();
        String sql = "SELECT * FROM bakery_items;";
        try {
            ResultSet resultSet = manager.query(sql);
            while (resultSet.next()) {
                BakeryItemDTO bakeryItemDTO = new BakeryItemDTO();
                bakeryItemDTO.setBakeryItemId(resultSet.getInt("bakery_item_id"));
                bakeryItemDTO.setBakeryItemName(resultSet.getString("bakery_item_name"));
                bakeryItemDTO.setBakeryItemPrice(resultSet.getInt("bakery_item_price"));
                bakeryItemDTO.setBakeryItemDescription(resultSet.getString("bakery_item_description"));
                bakeryItemDTO.setSalesStatus(SalesStatus.valueOf(resultSet.getString("status")));
                bakeryItemDTO.setBakeryItemImage(resultSet.getString("bakery_item_image"));
                bakeryItems.add(bakeryItemDTO);
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return bakeryItems;
    }

    @Override
    public ArrayList<BakeryItemDTO> loadAvailableBakeryItems() {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadAvailableBakeryItems");
        ArrayList<BakeryItemDTO> bakeryItems = new ArrayList<>();
        String sql = "SELECT * FROM bakery_items WHERE status = 'AVAILABLE';";
        try {
            ResultSet resultSet = manager.query(sql);
            while (resultSet.next()) {
                BakeryItemDTO bakeryItemDTO = new BakeryItemDTO();
                bakeryItemDTO.setBakeryItemId(resultSet.getInt("bakery_item_id"));
                bakeryItemDTO.setBakeryItemName(resultSet.getString("bakery_item_name"));
                bakeryItemDTO.setBakeryItemPrice(resultSet.getInt("bakery_item_price"));
                bakeryItemDTO.setBakeryItemDescription(resultSet.getString("bakery_item_description"));
                bakeryItemDTO.setBakeryItemImage(resultSet.getString("bakery_item_image"));
                bakeryItems.add(bakeryItemDTO);
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return bakeryItems;
    }

    @Override
    public BakeryItemDTO loadBakeryItem(int id) {
        MySQLManagerImpl manager = new MySQLManagerImpl("loadBakeryItem");
        BakeryItemDTO bakeryItemDTO = new BakeryItemDTO();
        String sql = "SELECT * FROM bakery_items WHERE bakery_item_id = '" + id + "';";
        try {
            ResultSet resultSet = manager.query(sql);
            if (resultSet.next()) {
                bakeryItemDTO.setBakeryItemId(resultSet.getInt("bakery_item_id"));
                bakeryItemDTO.setBakeryItemName(resultSet.getString("bakery_item_name"));
                bakeryItemDTO.setBakeryItemPrice(resultSet.getInt("bakery_item_price"));
                bakeryItemDTO.setBakeryItemDescription(resultSet.getString("bakery_item_description"));
                bakeryItemDTO.setSalesStatus(SalesStatus.valueOf(resultSet.getString("status")));
                bakeryItemDTO.setBakeryItemImage(resultSet.getString("bakery_item_image"));
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return bakeryItemDTO;
    }

    @Override
    public boolean deleteBakeryItem(int id) {
        MySQLManagerImpl manager = new MySQLManagerImpl("deleteBakeryItem");
        String sql = "DELETE FROM bakery_items WHERE bakery_item_id = " + id + ";";
        return manager.execute(sql);
    }

    @Override
    public boolean updateBakeryItem(BakeryItemDTO bakeryItemDTO) {
        MySQLManagerImpl manager = new MySQLManagerImpl("updateBakeryItem");
        String sql = "UPDATE bakery_items SET bakery_item_name = '";
        sql += manager.escapeString(bakeryItemDTO.getBakeryItemName()) + "', bakery_item_price = ";
        sql += bakeryItemDTO.getBakeryItemPrice();
        sql += ", bakery_item_description = '";
        sql += manager.escapeString(bakeryItemDTO.getBakeryItemDescription()) + "', bakery_item_image = '";
        sql += manager.escapeString(bakeryItemDTO.getBakeryItemImage()) + "' WHERE bakery_item_id = " + bakeryItemDTO.getBakeryItemId() + ";";
        return manager.execute(sql);
    }

    @Override
    public boolean updateSalesStatus(int id, SalesStatus salesStatus) {
        MySQLManagerImpl manager = new MySQLManagerImpl("updateSalesStatus");
        String sql = "UPDATE bakery_items SET status = '" + salesStatus.name() + "' WHERE bakery_item_id = " + id + ";";
        return manager.execute(sql);
    }
}
