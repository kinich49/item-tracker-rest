INSERT INTO Brands(name) VALUES ('Test Brand 1')
INSERT INTO Brands(name) VALUES ('Test Brand 2')
INSERT INTO Brands(name) VALUES ('Test Brand 3')
INSERT INTO Brands(name) VALUES ('Test Brand 4')
INSERT INTO Brands(name) VALUES ('Test Brand Empty')

INSERT INTO Categories(name) VALUES('Test Category 1')
INSERT INTO Categories(name) VALUES('Test Category 2')
INSERT INTO Categories(name) VALUES('Test Category 3')
INSERT INTO Categories(name) VALUES('Test Category Empty')

INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:1 b:1 c:1', 1, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:2 b:3 c:1', 3, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:3 b:2 c:1', 2, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:4 b:4 c:1', 4, 1)

INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:5 b:3 c:2', 3, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:6 b:3 c:3', 3, 3)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:7 b:4 c:2', 4, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Test Item i:8 b:4 c:3', 4, 3)

INSERT INTO Stores(name) VALUES('Test Store 1')
INSERT INTO Stores(name) VALUES('Test Store 2')
INSERT INTO Stores(name) VALUES('Test Store 3')

INSERT INTO Shopping_Lists(shopping_date, store_id) VALUES('2020-01-05',1)
INSERT INTO Shopping_Lists(shopping_date, store_id) VALUES('2020-01-19',1)
INSERT INTO Shopping_Lists(shopping_date, store_id) VALUES('2020-01-12',2)
INSERT INTO Shopping_Lists(shopping_date, store_id) VALUES('2020-01-26',2)
INSERT INTO Shopping_Lists(shopping_date, store_id) VALUES('2020-02-02',3)

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2200, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(2, 1.0, 5000, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 1000, 1,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2300, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 1300, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(7, 1.0, 1000, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(8, 1.0, 4500, 3,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2400, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(2, 1.0, 5000, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(3, 1.0, 1000, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 1200, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(8, 1.0, 5000, 2,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 1.0, 2100, 4,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(4, 1.0, 1000, 5,'Unit', 'MXN')