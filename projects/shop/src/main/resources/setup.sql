-- create schema shop;
-- set schema 'shop';

drop table if exists "user" cascade;
drop type if exists role;
create type role as enum ('CUSTOMER', 'EMPLOYEE', 'MANAGER');
create table "user"
(
    user_id       serial primary key,
    username      varchar(20) not null,
    password      text        not null,
    assigned_role role default 'CUSTOMER'
);

drop table if exists item cascade;
drop type if exists stock;
create type stock as enum ('AVAILABLE', 'OWNED');
create table item
(
    item_id   serial primary key,
    item_name varchar(30) not null,
    stock     stock default 'AVAILABLE'
);

drop table if exists offer cascade;
create table offer
(
    user_id      integer references "user" (user_id),
    item_id      integer references item (item_id),
    offer_date   date default current_date,
    offer_amount money not null,
    primary key (user_id, item_id)
);

insert into "user" (username, password, assigned_role)
values ('eric', '1234', 'EMPLOYEE');
insert into "user" (username, password, assigned_role)
values ('manager', 'secret', 'MANAGER');

insert into item (item_name)
values ('Lemonade - Pineapple Passion');
insert into item (item_name)
values ('Pepper - Paprika, Hungarian');
insert into item (item_name)
values ('Carbonated Water - Blackberry');
insert into item (item_name)
values ('Wine - Red, Metus Rose');
insert into item (item_name)
values ('Split Peas - Yellow, Dry');
insert into item (item_name)
values ('Table Cloth 72x144 White');
insert into item (item_name)
values ('Lid Tray - 16in Dome');
insert into item (item_name)
values ('Pastry - French Mini Assorted');
insert into item (item_name)
values ('Chef Hat 20cm');
insert into item (item_name)
values ('Ecolab - Lime - A - Way 4/4 L');