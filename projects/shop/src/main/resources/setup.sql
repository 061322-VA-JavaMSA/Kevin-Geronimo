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
    assigned_role role
);

drop table if exists item cascade;
drop type if exists stock;
create type stock as enum ('AVAILABLE', 'OWNED');
create table item
(
    item_id   serial primary key,
    item_name varchar(20) not null,
    stock     stock       not null
);

drop table if exists offer cascade;
drop type if exists status;
create type status as enum ('PENDING', 'ACCEPTED');
create table offer
(
    user_id      integer references "user" (user_id),
    item_id      integer references item (item_id),
    offer_date   date default current_date,
    offer_amount numeric(2) not null,
    status       status     not null,
    primary key (user_id, item_id)
)