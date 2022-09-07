CREATE TABLE PRODUCT (
    id VARCHAR(38) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    stock INTEGER NOT NULL,
    CONSTRAINT id UNIQUE  (id)
);

INSERT INTO product (id, name, price, stock)
VALUES (
        '6166dfbc-d319-45b6-a1f4-7f300856ee0f',
        'Mouse Wireless',
        89.90,
        15
 );

INSERT INTO product (id, name, price, stock)
VALUES (
           '8171ccb6-4165-41ee-9774-8b804be0e9f1',
           'Keyboard Wireless',
           100.00,
           20
);