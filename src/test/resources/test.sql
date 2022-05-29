INSERT INTO Users(username, password) VALUES ('User A', 'password')
INSERT INTO Users(username, password) VALUES ('User B', 'password')

INSERT INTO User_Security_Properties(user_id, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES (1,1,1,1,1)
INSERT INTO User_Security_Properties(user_id, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES (2,1,1,1,1)

INSERT INTO Brands(name) VALUES ('Test Brand 1')
INSERT INTO Brands(name) VALUES ('Test Brand 2')
INSERT INTO Brands(name) VALUES ('Test Brand 3')
INSERT INTO Brands(name) VALUES ('Test Brand 4')
INSERT INTO Brands(name) VALUES ('Test Brand Empty')

INSERT INTO Categories(name) VALUES('Test Category 1')
INSERT INTO Categories(name) VALUES('Test Category 2')
INSERT INTO Categories(name) VALUES('Test Category 3')
INSERT INTO Categories(name) VALUES('Test Category Empty')

INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:1 b_id:1 c_id:1', 1, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:2 b_id:3 c_id:1', 3, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:3 b_id:2 c_id:1', 2, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:4 b_id:4 c_id:1', 4, 1)

INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:5 b_id:3 c_id:2', 3, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:6 b_id:3 c_id:3', 3, 3)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:7 b_id:4 c_id:2', 4, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item id:8 b_id:4 c_id:3', 4, 3)

INSERT INTO Stores(name) VALUES('Test Store 1')
INSERT INTO Stores(name) VALUES('Test Store 2')
INSERT INTO Stores(name) VALUES('Test Store 3')

INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-01-05',1, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-01-12',2, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-01-19',1, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-01-26',2, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-02-02',3, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-02-16',1, 2)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-02-23',3, 2)

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2200, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(2, 1.0, 5000, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 1000, 1,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2400, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(2, 1.0, 5000, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(3, 1.0, 1000, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 1200, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(8, 1.0, 5000, 2,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2300, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 1300, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(7, 1.0, 1000, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(8, 1.0, 4500, 3,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2100, 4,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(4, 1.0, 1000, 5,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 1000, 6,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(3, 1.0, 2000, 6,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(5, 1.0, 5000, 6,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(8, 1.0, 4500, 6,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 3500, 7,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(3, 1.0, 2000, 7,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 4000, 7,'Unit', 'MXN')
