package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.MySQLFunc;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.model.JDBCModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLFuncImpl implements MySQLFunc {
    private final JDBCModel jdbcModel;
    private Connection conn;
    public MySQLFuncImpl(JDBCModel jdbcModel) {
        this.jdbcModel = jdbcModel;
    }
    @Override
    public Connection open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" +
                    jdbcModel.getHost() +
                    ":" +
                    jdbcModel.getPort() +
                    "/" +
                    jdbcModel.getDb() +
                    "?allowPublicKeyRetrieval=true&useSSL=false";
            conn = DriverManager.getConnection(url, jdbcModel.getUser(), jdbcModel.getPass());
        } catch (SQLException e) {
            e.fillInStackTrace();
            System.err.println("Could not connect to MySQL server, error code: " + e.getErrorCode());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver was not found in this machine.");
        }
        return conn;
    }

    @Override
    public boolean checkConnection() {
        return conn != null;
    }

    @Override
    public void close(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }
}