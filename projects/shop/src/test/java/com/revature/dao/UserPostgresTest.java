package com.revature.dao;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPostgresTest {

    private final UserPostgres userPostgres = new UserPostgres();

    @BeforeAll
    static void setTestTable() throws SQLException, IOException {
        String sql = """
                DROP SCHEMA IF EXISTS test CASCADE;
                CREATE SCHEMA test;
                SET SCHEMA 'test';
                drop table if exists "user" cascade;
                drop type if exists role;
                create type role as enum ('CUSTOMER', 'EMPLOYEE', 'MANAGER');
                create table "user" (
                    user_id serial primary key,
                    username varchar(20) not null,
                    password text not null,
                    assigned_role role
                );
                INSERT INTO "user" (username, password, assigned_role) VALUES ('kevin', 'sadfdfkj', 'MANAGER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('cartman', 'adsffgg', 'CUSTOMER');
                """;

        // can't use try-with-resources here as that will close the connection and reset the schema back to public
        Connection c = ConnectionUtil.getConnectionFromFile();
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
    }

    @Test
    void testGet() {
        User actualUser = userPostgres.get(1);
        User expectedUser = new User(1, "kevin", "sadfdfkj", User.Role.MANAGER);

        assertEquals(expectedUser, actualUser);
    }
}
