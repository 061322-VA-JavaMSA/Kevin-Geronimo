package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    /*
     * Singleton
     * 	- Design pattern that ensures that there is only one instance of a class
     * 		- useful for heavier resources
     * 	- enforced via multiple conditions
     * 		- private static instance of the singleton class
     * 		- public static getter method to retrieve the same instance of the singleton
     * 		- private constructor
     */
    // reuse c instead of creating new connections to the database
    private static Connection c;

    public static Connection getConnectionFromFile() throws SQLException, IOException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        prop.load(loader.getResourceAsStream("connection.properties"));

        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        if (c == null || c.isClosed()) {
            c = DriverManager.getConnection(url, username, password);
        }

        return c;
    }
}