package com.revature.entity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection connection;

    private static String connectionString = "jdbc:postgresql://ella.db.elephantsql.com:5432/axccisbl";
    private static String username = "axccisbl";
    private static String password = "bqrjVb61xWZ-6EyawVUsH_uKNM8z7jPQ";

    public static void setConnection(String connectionString, String username, String password) {
        ConnectionManager.connectionString = connectionString;
        ConnectionManager.username = username;
        ConnectionManager.password = password;
    }

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(connectionString, username, password);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("ConnectionManager-error: " + e);
        } catch (SQLException e) {
            System.out.println("ConnectionManager-error: " + e);
        }
        return null;
    }

    // When the program is stopped, this will trigger and close the connection
    // You have to use the stop button in your IDE. Similar to finally clause in main()
    @Override
    public void finalize() {
        try {
            ConnectionManager.getConnection().close();
        } catch (SQLException e) {
            System.out.println("ConnectionManager-error: " + e);
        }
    }
}
