INSERT INTO brands(name) VALUES('Economax') -- 1
INSERT INTO brands(name) VALUES('Silken') -- 2
INSERT INTO brands(name) VALUES('Ades') -- 3
INSERT INTO brands(name) VALUES('Zqan') -- 4
INSERT INTO brands(name) VALUES('Philadelphia') -- 5
INSERT INTO brands(name) VALUES('Jello') -- 6
INSERT INTO brands(name) VALUES('Alco') -- 7
INSERT INTO brands(name) VALUES('Barilla') -- 8
INSERT INTO brands(name) VALUES('ItalPasta') -- 9
INSERT INTO brands(name) VALUES('HEB') -- 10
INSERT INTO brands(name) VALUES('Yakult') -- 11
INSERT INTO brands(name) VALUES('Ritual') -- 12
INSERT INTO brands(name) VALUES('KleanBebe') -- 13
INSERT INTO brands(name) VALUES('Microdyn') -- 14
INSERT INTO brands(name) VALUES('Tresemme') -- 15
INSERT INTO brands(name) VALUES('Gloria') -- 16
INSERT INTO brands(name) VALUES('Lala') -- 17
INSERT INTO brands(name) VALUES('Sanissimo') -- 18
INSERT INTO brands(name) VALUES('Kellogs') -- 19

INSERT INTO categories(name) VALUES('Abarrotes') -- 1
INSERT INTO categories(name) VALUES('Lácteos y Huevo') -- 2
INSERT INTO categories(name) VALUES('Alimentos Congelados') -- 3
INSERT INTO categories(name) VALUES('Frutas, Verduras y Especias') -- 4
INSERT INTO categories(name) VALUES('Salchichonería') -- 5
INSERT INTO categories(name) VALUES('Panadería y Tortillería') -- 6
INSERT INTO categories(name) VALUES('Dulces') -- 7
INSERT INTO categories(name) VALUES('Carnes, Pollo y Pescado') -- 8
INSERT INTO categories(name) VALUES('Cleaners') -- 9
INSERT INTO categories(name) VALUES('Higiene y Belleza') -- 10
INSERT INTO categories(name) VALUES('Limpieza del Hogar y Desechables') --11
INSERT INTO categories(name) VALUES('Farmacia') --11
INSERT INTO categories(name) VALUES('Vinos y Licores') --12
INSERT INTO categories(name) VALUES('Zapatos y Accesorios') --13
INSERT INTO categories(name) VALUES('Bebés') --14

INSERT INTO items(name, category_id) VALUES('Tomate Fresadilla', 4) -- 1
INSERT INTO items(name, category_id) VALUES('Tomate Huaje', 4) -- 2
INSERT INTO items(name, category_id) VALUES('Naranja Valencia', 4) -- 3
INSERT INTO items(name, category_id) VALUES('Chile Jalapeño', 4) -- 4
INSERT INTO items(name, category_id) VALUES('Uva Blanca sin semilla', 4) -- 5
INSERT INTO items(name, category_id) VALUES('Cilantro', 4) -- 6
INSERT INTO items(name, category_id) VALUES('Camote amarillo', 4) -- 7
INSERT INTO items(name, category_id) VALUES('Plátano', 4) -- 8
INSERT INTO items(name, category_id) VALUES('Chayote sin espinas', 4) -- 9
INSERT INTO items(name, category_id) VALUES('Manzana Golden', 4) -- 10
INSERT INTO items(name, brand_id, category_id) VALUES('Huevo Blanco 30 piezas', 1, 2) -- 11
INSERT INTO items(name, brand_id, category_id) VALUES('Corn Pops', 19, 1) -- 12
INSERT INTO items(name, brand_id, category_id) VALUES('Silken Coco 3 en 1', 2, 10) -- 13
INSERT INTO items(name, brand_id, category_id) VALUES('Leche UHT Soya Natural', 3, 2) -- 14
INSERT INTO items(name, brand_id, category_id) VALUES('Butter Krust pan para hot-dog', 10, 6) -- 15
INSERT INTO items(name, brand_id, category_id) VALUES('Twist 20oz', 10, 1) -- 16
INSERT INTO items(name, brand_id, category_id) VALUES('Dr B 20oz', 10, 1) -- 17
INSERT INTO items(name, brand_id, category_id) VALUES('Original Cola 20oz', 10, 1) -- 18
INSERT INTO items(name, brand_id, category_id) VALUES('Mantequilla Untable', 16, 2) -- 19
INSERT INTO items(name, brand_id, category_id) VALUES('Leche UHT Deslactosada', 17, 2) -- 20
INSERT INTO items(name, brand_id, category_id) VALUES('Gelatina Fresa', 6, 1) -- 21
INSERT INTO items(name, brand_id, category_id) VALUES('Gelatina Limón', 6, 1) -- 22
INSERT INTO items(name, brand_id, category_id) VALUES('Tostadas Horneadas de Maíz', 18, 6) -- 23
INSERT INTO items(name, brand_id, category_id) VALUES('Leche Vainilla 200ml', 3, 2) -- 24
INSERT INTO items(name, brand_id, category_id) VALUES('Salchicha de Pavo', 'Zwan', 5) -- 25
INSERT INTO items(name, brand_id, category_id) VALUES('Queso Crema', 'Philadelphia', 2) -- 26
INSERT INTO items(name, brand_id, category_id) VALUES('Leche Fresa 200ml', 3, 2) -- 27
INSERT INTO items(name, brand_id, category_id) VALUES('Gelatina Cereza', 6, 1) -- 28
INSERT INTO items(name, brand_id, category_id) VALUES('Milanesa Marinada 550g', 7, 8) -- 29
INSERT INTO items(name, brand_id, category_id) VALUES('Pasta codo 2 200g', 8, 1) -- 30
INSERT INTO items(name, brand_id, category_id) VALUES('Fideo 180g', 9, 1) -- 31
INSERT INTO items(name, brand_id, category_id) VALUES('Milanesa Pulpa Negra regular', 10, 8) -- 32
INSERT INTO items(name, brand_id, category_id) VALUES('Molida Pulpa fina 90/10', 10, 8) -- 33
INSERT INTO items(name, brand_id, category_id) VALUES('Leche Yakult 5 pack', 11, 2) -- 34
INSERT INTO items(name, brand_id, category_id) VALUES('Jabon Barra individual', 12, 10) -- 35
INSERT INTO items(name, brand_id, category_id) VALUES('Pasta Pepita 200g', 9, 1) -- 36
INSERT INTO items(name, brand_id, category_id) VALUES('Toallitas húmedas con Aroma 100 unidates', 13, 14) -- 37
INSERT INTO items(name, brand_id, category_id) VALUES('Desinfectante 90ml', 14, 1) -- 38
INSERT INTO items(name, brand_id, category_id) VALUES('Shampoo Control Caída', 15, 10) -- 39

INSERT INTO shopping(shopping_date) VALUES('2020-01-06')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(1, 0.355, 2395, 1) -- 1
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(2, 1.055, 3995, 1) -- 2
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(3, 2.0, 1295, 1) -- 3
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(4, 0.290, 1895, 1) -- 4
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(5, 0.320, 8995, 1) -- 5
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(6, 1.0, 695, 1) -- 6
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(7, 4695, 1) -- 7
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(8, 0.795, 1395, 1) -- 8
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(9, 0.695, 1695, 1) -- 9
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(10, 0.240, 6995, 1) -- 10
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(11, 1.0, 6300, 1) -- 11
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(12, 1.0, 7290, 1) -- 12
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(13, 1.0, 7890, 1) -- 13
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(14, 2, 2590, 1) -- 14
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(15, 1.0, 2550, 1) -- 15
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(16, 1.0, 1090, 1) -- 16
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(17, 1.0, 1090, 1) -- 17
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(18, 1.0, 1090, 1) -- 18
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(19, 1.0, 7000, 1) -- 19
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(20, 2.0, 2150, 1) -- 20
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(21, 1.0, 790, 1) -- 21
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(22, 1.0, 790, 1) -- 22
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(23, 1.0, 2690, 1) -- 23
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(24, 3.0, 900, 1) -- 24
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(25, 1.0, 4890, 1) -- 25
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(26, 1.0, 3290, 1) -- 26
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(27, 3.0, 900, 1) -- 27
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(28, 1.0, 790, 1) -- 28
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(29, 1.0, 7490, 1) -- 29
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(30, 1.0, 620, 1) -- 30
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(31, 2.0, 470, 1) -- 31
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(32, 0.425, 21200, 1) -- 32
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(33, 1.0, 6929, 1) -- 33
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(34, 1.0, 2950, 1) -- 34
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(35, 1.0, 2790, 1) -- 35
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(36, 1.0, 3490, 1) -- 36
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(37, 1.0, 3890, 1) -- 37
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(38, 1.0, 3395, 1) -- 38
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_id) VALUES(39, 1.0, 5890, 1) -- 39

