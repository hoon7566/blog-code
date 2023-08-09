create database hoon7566;

CREATE TABLE hoon7566.items(
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    category_code VARCHAR(255) NOT NULL,
    item_price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE hoon7566.orders(
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_date_time DATE NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE hoon7566.users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL
);

INSERT INTO hoon7566.users (user_name) VALUES ('John Doe');
INSERT INTO hoon7566.users (user_name) VALUES ('Jane Smith');


INSERT INTO hoon7566.items (item_name, category_code, item_price, quantity) VALUES ('Laptop', '555555', 1200.50, 10);
INSERT INTO hoon7566.items (item_name, category_code, item_price, quantity) VALUES ('Mouse', '555555', 25.00, 50);

-- John Doe의 주문을 추가
INSERT INTO hoon7566.orders (order_date_time, user_id) VALUES (now(), 1);

-- Jane Smith의 주문을 추가
INSERT INTO hoon7566.orders (order_date_time, user_id) VALUES (now(), 2);
