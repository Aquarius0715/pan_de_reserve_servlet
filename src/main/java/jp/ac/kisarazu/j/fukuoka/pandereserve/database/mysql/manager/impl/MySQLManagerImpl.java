package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.MySQLFunc;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.MySQLManager;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.model.JDBCModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLManagerImpl implements MySQLManager {
    private JDBCModel jdbcModel;
    private boolean isDebugMode;
    private boolean isConnected = false;
    private Statement statement;
    private Connection connection;
    private MySQLFunc mySQLFunc;
    private final String connectionName;

    public MySQLManagerImpl(String connectionName) {
        jdbcModel = new JDBCModel();
        loadConfig();
        isDebugMode = true;
        isConnected = false;
        this.connectionName = connectionName;
    }

    @Override
    public void loadConfig() {


        /*
        try {
            String yaml = Files.readString(Paths.get("src/main/resources/mysqlConfig.yml"));
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            jdbcModel = mapper.readValue(yaml, JDBCModel.class);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
         */


        jdbcModel.setDb("pan_de_reserve");
        jdbcModel.setHost("localhost");
        jdbcModel.setPass("mk871396");
        jdbcModel.setPort("3306");
        jdbcModel.setUser("root");
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean connect(JDBCModel jdbcModel) throws SQLException {
        this.jdbcModel = jdbcModel;
        mySQLFunc = new MySQLFuncImpl(jdbcModel);
        connection = mySQLFunc.open();
        if (connection == null) {
            System.err.println("Failed to Open MySQL");
            return false;
        }
        StringBuilder msg;
        try {
            statement = connection.createStatement();
            isConnected = true;
            msg = new StringBuilder();
            msg.append("[");
            msg.append(connectionName);
            msg.append("] Connected to the database");
            System.out.println(msg);
        } catch (SQLException e) {
            isConnected = false;
            msg = new StringBuilder();
            msg.append("[");
            msg.append(connectionName);
            msg.append("] Could not connect to the database");
            e.fillInStackTrace();
        }
        mySQLFunc.close(connection);
        return isConnected;
    }

    @Override
    public boolean connectCheck() throws SQLException {
        return connect(this.jdbcModel);
    }
    @Override
    public boolean execute(String query) {
        StringBuilder msg;
        mySQLFunc = new MySQLFuncImpl(jdbcModel);
        connection = mySQLFunc.open();
        if (connection == null) {
            System.err.println("Failed to open MySQL");
            return false;
        }
        boolean ret = true;
        if (isDebugMode) {
            System.out.println(query);
        }
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            msg = new StringBuilder();
            msg.append("[");
            msg.append(connectionName);
            msg.append("] Error executing statement: ");
            msg.append(e.getErrorCode());
            msg.append(":");
            msg.append(e.getMessage());
            System.err.println(msg);
            ret = false;
            e.fillInStackTrace();
        }
        close();
        return ret;
    }

    @Override
    public ResultSet query(String query) {
        StringBuilder msg;
        mySQLFunc = new MySQLFuncImpl(jdbcModel);
        connection = mySQLFunc.open();
        ResultSet resultSet = null;
        if (connection == null) {
            System.err.println("failed to open MySQL");
            return null;
        }
        if (isDebugMode) {
            System.out.println(query);
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.fillInStackTrace();
            msg = new StringBuilder();
            msg.append("[");
            msg.append(connectionName);
            msg.append("] Error executing query: ");
            msg.append(e.getErrorCode());
            msg.append(":");
            msg.append(e.getMessage());
            System.err.println(msg);
        }
        return resultSet;
    }

    @Override
    public void close() {
        try {
            statement.close();
            connection.close();
            mySQLFunc.close(connection);
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    public void setIsDebugMode(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }
    public boolean getIsDebugMode() {
        return isDebugMode;
    }

    @Override
    public String escapeString(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder escapedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '\\': // バックスラッシュ
                    escapedString.append("\\\\");
                    break;
                case '\'': // シングルクォート
                    escapedString.append("\\'");
                    break;
                case '"': // ダブルクォート
                    escapedString.append("\\\"");
                    break;
                case '\b': // バックスペース
                    escapedString.append("\\b");
                    break;
                case '\n': // 改行
                    escapedString.append("\\n");
                    break;
                case '\r': // 復帰
                    escapedString.append("\\r");
                    break;
                case '\t': // タブ
                    escapedString.append("\\t");
                    break;
                case '\u0000': // NULL文字
                    escapedString.append("\\0");
                    break;
                case '%': // ワイルドカード
                    escapedString.append("\\%");
                    break;
                case '_': // ワイルドカード
                    escapedString.append("\\_");
                    break;
                default:
                    escapedString.append(c);
                    break;
            }
        }
        return escapedString.toString();
    }
}
