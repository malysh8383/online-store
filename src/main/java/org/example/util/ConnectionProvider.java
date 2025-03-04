package org.example.util;

import org.example.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    final static String url = "jdbc:postgresql://localhost:5432/postgres";
    final static String user = "postgres";
    final static String password = "postgres";
    final static String driver = "org.postgresql.Driver";


    public ConnectionProvider() throws ConnectionException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Драйвер не найден");
        }
    }

    public Connection getConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new ConnectionException("Соединеие с бд не установлено");
        }
    }


}
