package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager;

import java.sql.Connection;
import java.sql.SQLException;

public interface MySQLFunc {
    Connection open();
    boolean checkConnection();
    void close(Connection c) throws SQLException;
}
