package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        try {
            // try to hack me :)
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456");
            System.out.println(c.getMetaData().getDriverName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
