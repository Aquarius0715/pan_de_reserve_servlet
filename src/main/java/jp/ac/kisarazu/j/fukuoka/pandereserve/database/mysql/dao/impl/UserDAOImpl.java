package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.UserDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.UserDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.MySQLManager;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl.MySQLManagerImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public UserDTO loadUserByUsername(String username) {
        MySQLManager manager = new MySQLManagerImpl("loadUserByUsername");
        UserDTO userDTO = new UserDTO();
        String sql = "SELECT * FROM users WHERE username = '" + manager.escapeString(username) + "';";
        try {
            ResultSet resultSet = manager.query(sql);
            if (resultSet.next()) {
                userDTO.setId(resultSet.getInt("id"));
                userDTO.setUsername(resultSet.getString("username"));
                userDTO.setPassword(resultSet.getString("password"));
                userDTO.setSalt(resultSet.getString("salt"));
                userDTO.setCreatedAt(resultSet.getTimestamp("created_at"));
                userDTO.setUpdatedAt(resultSet.getTimestamp("updated_at"));
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return userDTO;
    }

    @Override
    public int countUsers() {
        int count = 0;
        MySQLManager manager = new MySQLManagerImpl("countUsers");
        String sql = "SELECT COUNT(*) FROM users;";
        try {
            ResultSet resultSet = manager.query(sql);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            manager.close();
            throw new RuntimeException(e);
        }
        manager.close();
        return count;
    }

    @Override
    public boolean storeUser(UserDTO user) {
        MySQLManager manager = new MySQLManagerImpl("storeUser");
        String sql = "INSERT INTO users(username, password, salt) VALUES ('";
        sql += manager.escapeString(user.getUsername()) + "', '";
        sql += manager.escapeString(user.getPassword()) + "', '";
        sql += manager.escapeString(user.getSalt()) + "');";
        return manager.execute(sql);
    }
}
