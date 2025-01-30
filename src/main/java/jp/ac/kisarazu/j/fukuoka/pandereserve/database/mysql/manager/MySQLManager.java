package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.model.JDBCModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MySQLManager {
    void loadConfig();
    void commit();
    boolean connect(JDBCModel jdbcModel) throws SQLException;
    boolean connectCheck() throws SQLException;
    boolean execute(String query);
    ResultSet query(String query);
    void close();
    String escapeString(String input);
}
