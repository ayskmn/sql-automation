package com.omerinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class handles connections to the MySQL database.
 * by OmerAKBEN
 */
public class MySqlConnector {

    // The URL of the database, including the database name (employees).
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employees";

    // The username for the database connection.
    private static final String USER = "root";

    // The password for the database connection.
    private static final String PASSWORD = "root";

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return A Connection object representing the database connection.
     * @throws SQLException If there is an issue establishing the connection.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
