package com.revature.dao;

import com.revature.model.Item;
import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OfferPostgresTest {

    private final OfferPostgres offerPostgres = new OfferPostgres("test");

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
                    user_id       serial primary key,
                    username      varchar(20) not null,
                    password      text        not null,
                    assigned_role role
                );
                                
                drop table if exists item cascade;
                drop type if exists stock;
                create type stock as enum ('AVAILABLE', 'OWNED');
                create table item (
                    item_id   serial primary key,
                    item_name varchar(20) not null,
                    stock     stock       not null
                );
                                
                drop table if exists offer cascade;
                create table offer (
                    user_id      integer references "user" (user_id),
                    item_id      integer references item (item_id),
                    offer_date   date default current_date,
                    offer_amount money not null,
                    primary key (user_id, item_id)
                );
                                
                INSERT INTO "user" (username, password, assigned_role) VALUES ('kevin', '1234', 'MANAGER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('cartman', '12345', 'CUSTOMER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('patrick', '123456', 'CUSTOMER');
                INSERT INTO "user" (username, password, assigned_role) VALUES ('courage', '1234567', 'EMPLOYEE');
                INSERT INTO item (item_name, stock) VALUES ('laptop', 'OWNED');
                INSERT INTO item (item_name, stock) VALUES ('clipper', 'AVAILABLE');
                INSERT INTO item (item_name, stock) VALUES ('bottle', 'AVAILABLE');
                INSERT INTO item (item_name, stock) VALUES ('phone', 'AVAILABLE');
                INSERT INTO offer (user_id, item_id, offer_date, offer_amount) VALUES (1, 1, current_date, money(50));
                INSERT INTO offer (user_id, item_id, offer_date, offer_amount) VALUES (2, 4, current_date, money(100));
                INSERT INTO offer (user_id, item_id, offer_date, offer_amount) VALUES (3, 2, current_date, money(300));
                INSERT INTO offer (user_id, item_id, offer_date, offer_amount) VALUES (2, 3, current_date, money(20));
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
    @Order(1)
    void testGetByUserId() {
        Offer actualOffer = offerPostgres.get(1);
        User user = new User(1, "kevin", "1234", User.Role.MANAGER);
        Item item = new Item(1, "laptop", Item.Stock.OWNED);
        Offer expectedOffer = new Offer(user, item, LocalDate.now(), 50.00f);

        assertEquals(expectedOffer, actualOffer);
    }

    @Test
    @Order(2)
    void testGetByCompositeKey() {
        Offer actualOffer = offerPostgres.get(2, 4);
        User user = new User(2, "cartman", "12345", User.Role.CUSTOMER);
        Item item = new Item(4, "phone", Item.Stock.AVAILABLE);
        Offer expectedOffer = new Offer(user, item, LocalDate.now(), 100);

        assertEquals(expectedOffer, actualOffer);
    }


    @Test
    @Order(3)
    void testGetAllOffers() {
        List<Offer> actualOffers = offerPostgres.getAll();
        List<Offer> expectedOffers = new ArrayList<>();
        User user1 = new User(1, "kevin", "1234", User.Role.MANAGER);
        Item item1 = new Item(1, "laptop", Item.Stock.OWNED);
        expectedOffers.add(new Offer(user1, item1, LocalDate.now(), 50));

        User user2 = new User(2, "cartman", "12345", User.Role.CUSTOMER);
        Item item2 = new Item(4, "phone", Item.Stock.AVAILABLE);
        expectedOffers.add(new Offer(user2, item2, LocalDate.now(), 100));

        User user3 = new User(3, "patrick", "123456", User.Role.CUSTOMER);
        Item item3 = new Item(2, "clipper", Item.Stock.AVAILABLE);
        expectedOffers.add(new Offer(user3, item3, LocalDate.now(), 300));

        User user = new User(2, "cartman", "12345", User.Role.CUSTOMER);
        Item item = new Item(3, "bottle", Item.Stock.AVAILABLE);
        expectedOffers.add(new Offer(user, item, LocalDate.now(), 20));

        assertArrayEquals(expectedOffers.toArray(), actualOffers.toArray());
    }

    @Test
    @Order(4)
    void testInsertOffer() {
        User user = new User();
        user.setId(1);

        Item item = new Item();
        item.setId(4);

        Offer offer = new Offer(user, item, LocalDate.now(), 250);

        //creates new relationship between existing user and item
        Offer actualOffer = offerPostgres.insert(offer);

        // testing for the amount makes more sense since this is the junction table
        assertEquals(250, actualOffer.getAmount());
    }

    @Test
    @Order(5)
    void testUpdateOffer() {
        User user = new User();
        user.setId(3);

        Item item = new Item();
        item.setId(2);

        Offer offer = new Offer(user, item, LocalDate.now(), 50.00f);
        assertTrue(offerPostgres.update(offer));
    }

    @Test
    @Order(6)
    void testDeleteOfferByUserId() {
        assertTrue(offerPostgres.delete(1));
    }

    @Test
    @Order(7)
    void testDeleteOfferByCompositeKey() {
        assertTrue(offerPostgres.delete(2, 4));
    }
}
