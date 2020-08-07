package com.epam.winter.java.lab.dao.utills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDb {
    public static Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:8083/library";
        final String USER = "user";
        final String PASS = "password";
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("error load driver");
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
