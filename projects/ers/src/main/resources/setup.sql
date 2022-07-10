DROP TABLE IF EXISTS ers_reimbursement_status CASCADE;
CREATE TABLE ers_reimbursement_status
(
    reimb_status_id serial PRIMARY KEY,
    reimb_status    varchar(10) NOT NULL
);

DROP TABLE IF EXISTS ers_reimbursement_type CASCADE;
CREATE TABLE ers_reimbursement_type
(
    reimb_type_id serial PRIMARY KEY,
    reimb_type    varchar(10) NOT NULL
);

DROP TABLE IF EXISTS ers_user_roles CASCADE;
CREATE TABLE ers_user_roles
(
    ers_user_role_id serial PRIMARY KEY,
    user_role        varchar(10) NOT NULL
);

DROP TABLE IF EXISTS ers_users CASCADE;
CREATE TABLE ers_users
(
    ers_users_id    serial PRIMARY KEY,
    ers_username    varchar(50) UNIQUE  NOT NULL,
    ers_password    varchar(50)         NOT NULL,
    user_first_name varchar(100)        NOT NULL,
    user_last_name  varchar(100)        NOT NULL,
    user_email      varchar(150) UNIQUE NOT NULL,
    user_role_id    int REFERENCES ers_user_roles (ers_user_role_id)
);

DROP TABLE IF EXISTS ers_reimbursement CASCADE;
CREATE TABLE ers_reimbursement
(
    reimb_id          serial PRIMARY KEY,
    reimb_amount      money NOT NULL,
    reimb_submitted   date  NOT NULL,
    reimb_resolved    date,
    reimb_description varchar(250),
    reimb_receipt     text,
    reimb_author      int REFERENCES ers_users (ers_users_id),
    reimb_resolver    int REFERENCES ers_users (ers_users_id),
    reimb_status_id   int REFERENCES ers_reimbursement_status (reimb_status_id),
    reimb_type_id     int REFERENCES ers_reimbursement_type (reimb_type_id)
);

INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('APPROVED');
INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('DENIED');

INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('LODGING');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('TRAVEL');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('FOOD');
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('OTHER');

INSERT INTO ers_user_roles (user_role) VALUES ('EMPLOYEE');
INSERT INTO ers_user_roles (user_role) VALUES ('MANAGER');