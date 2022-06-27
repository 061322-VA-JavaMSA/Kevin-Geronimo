package com.revature.dao;

import com.revature.model.Item;
import com.revature.util.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemPostgresTest {

    private final ItemPostgres itemPostgres = new ItemPostgres("test");

    @BeforeAll
    static void createTestTable() throws SQLException, IOException {
        String sql = """
                DROP SCHEMA IF EXISTS test CASCADE;
                CREATE SCHEMA test;
                SET SCHEMA 'test';
                drop table if exists item cascade;
                drop type if exists stock;
                create type stock as enum ('AVAILABLE', 'OWNED');
                create table item (
                    item_id   serial primary key,
                    item_name varchar(20) not null,
                    stock     stock       not null
                );
                                
                INSERT INTO item (item_name, stock) VALUES ('laptop', 'OWNED');
                INSERT INTO item (item_name, stock) VALUES ('clipper', 'AVAILABLE');
                INSERT INTO item (item_name, stock) VALUES ('bottle', 'AVAILABLE');
                INSERT INTO item (item_name, stock) VALUES ('phone', 'AVAILABLE');
                """;

        Connection connection = ConnectionUtil.getConnectionFromFile();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }

    @AfterAll
    static void teardown() throws SQLException, IOException {
        String sql = "DROP SCHEMA IF EXISTS test CASCADE;";

        Connection connection = ConnectionUtil.getConnectionFromFile();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }

    @Test
    void testGetById() {
        Item actualItem = itemPostgres.get(1);
        Item expectedItem = new Item(1, "laptop", Item.Stock.OWNED);

        assertEquals(expectedItem, actualItem);
    }

    @Test
    void testGetAllItems() {
        List<Item> actualItems = itemPostgres.getAll();
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(new Item(1, "laptop", Item.Stock.OWNED));
        expectedItems.add(new Item(2, "clipper", Item.Stock.AVAILABLE));
        expectedItems.add(new Item(3, "bottle", Item.Stock.AVAILABLE));
        expectedItems.add(new Item(4, "phone", Item.Stock.AVAILABLE));

        assertArrayEquals(expectedItems.toArray(), actualItems.toArray());
    }

    @Test
    void testInsertItem() {
        Item user = new Item();
        user.setItemName("blender");
        user.setStock(Item.Stock.AVAILABLE);

        Item actualItem = itemPostgres.insert(user);

        assertEquals(5, actualItem.getId());
    }

    @Test
    void testUpdateItem() {
        Item user = new Item(4, "speaker", Item.Stock.OWNED);
        assertTrue(itemPostgres.update(user));
    }

    @Test
    void testDeleteItem() {
        assertTrue(itemPostgres.delete(2));
    }
}
