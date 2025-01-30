package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.UserDTO;

public interface UserDAO {
    UserDTO loadUserByUsername(String username);
    int countUsers();
    boolean storeUser(UserDTO user);
}
