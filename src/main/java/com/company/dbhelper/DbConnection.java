package com.company.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&serverTimezone=UTC", User.USERNAME.getValue(), User.PASSWORD.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
