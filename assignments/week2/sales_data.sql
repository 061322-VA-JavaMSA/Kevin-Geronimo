drop table if exists employee;
create table employee (
    employee_id serial primary key,
    sales_person varchar(20),
    sales_person_dob date
);

drop table if exists customer;
create table customer (
    customer_id serial primary key,
    customer varchar(20),
    customer_dob date
);

drop table if exists sales_office;
create table sales_office (
    id serial primary key,
    name varchar(20),
    street varchar(30),
    city varchar(20),
    state varchar(20),
    zip varchar(5)  
);

drop table if exists sales;
create table sales (
    employee_id integer references employee(employee_id),
    customer_id integer references customer(customer_id),
    sales_office_id integer references sales_office(id),
    primary key (employee_id, customer_id, sales_office_id)
)