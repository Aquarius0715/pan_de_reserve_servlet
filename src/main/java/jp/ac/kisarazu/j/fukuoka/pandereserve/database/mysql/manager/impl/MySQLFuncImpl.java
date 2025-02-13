package jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.manager.MySQLFunc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLFuncImpl implements MySQLFunc {
    private Connection conn;
    public MySQLFuncImpl() {
    }
    @Override
    public Connection open() {

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/PanDeReserveDB");
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Could not connect to MySQL server, error code: " + e.getErrorCode());
        } catch (NamingException e) {
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