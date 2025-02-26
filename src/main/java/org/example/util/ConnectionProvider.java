package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    final String url = "jdbc:postgresql://localhost:5432/postgres";
    final String user = "postgres";
    final String password = "postgres";
    final String driver = "org.postgresql.Driver";

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
            throw new ConnectionException(" Соединеие с бд не установлено");

        }
    }


}
