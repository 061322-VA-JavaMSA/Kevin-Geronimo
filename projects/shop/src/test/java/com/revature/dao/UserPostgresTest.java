package com.revature.dao;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserPostgresTest {

    private final UserPostgres userPostgres = new UserPostgres();

    @BeforeAll
    static void createTestTable() throws SQLException, IOException {
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
                INSERT INTO "user" (username, password, assigned_role) VALUES ('kevin', '1234', 'MANAGER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('cartman', '12345', 'CUSTOMER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('patrick', '123456', 'CUSTOMER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('courage', '1234567', 'EMPLOYEE');
                """;

        // can't use try-with-resources here as that will close the connection and reset the schema back to public
        Connection c = ConnectionUtil.getConnectionFromFile();
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
    }

    @Test
    void testGetById() {
        User actualUser = userPostgres.get(1);
        User expectedUser = new User(1, "kevin", "sadfdfkj", User.Role.MANAGER);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetByUsername() {
        User actualUser = userPostgres.get("patrick");
        User expectedUser = new User(3, "patrick", "sadf234kj", User.Role.CUSTOMER);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetAllUsers() {
        List<User> actualUsers = userPostgres.getAll();
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, "kevin", "1234", User.Role.MANAGER));
        expectedUsers.add(new User(2, "cartman", "12345", User.Role.CUSTOMER));
        expectedUsers.add(new User(3, "patrick", "123456", User.Role.CUSTOMER));
        expectedUsers.add(new User(4, "courage", "1234567", User.Role.EMPLOYEE));

        assertArrayEquals(expectedUsers.toArray(), actualUsers.toArray());
    }

    @Test
    void testInsertUser() {
        User user = new User();
        user.setUsername("naruto");
        user.setPassword("12345rty");
        user.setRole(User.Role.EMPLOYEE);

        User actualUser = userPostgres.insert(user);

        assertEquals(5, actualUser.getId());
    }

    @Test
    void testUpdateUser() {
        User user = new User(4, "ichigo", "654321", User.Role.MANAGER);
        assertTrue(userPostgres.update(user));
    }

    @Test
    void testDeleteUser() {
        assertTrue(userPostgres.delete(2));
    }
}
