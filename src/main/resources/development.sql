INSERT INTO Users(username) VALUES('admin')

INSERT INTO Brands(name) VALUES ('Economax')
INSERT INTO Brands(name) VALUES ('Silken') 
INSERT INTO Brands(name) VALUES('Ades') 
INSERT INTO Brands(name) VALUES('Zwan') 
INSERT INTO Brands(name) VALUES('Philadelphia')
INSERT INTO Brands(name) VALUES('Jello') 
INSERT INTO Brands(name) VALUES('Alco') 
INSERT INTO Brands(name) VALUES('Barilla') 
INSERT INTO Brands(name) VALUES('ItalPasta') 
INSERT INTO Brands(name) VALUES('HEB')
INSERT INTO Brands(name) VALUES('Yakult')
INSERT INTO Brands(name) VALUES('Ritual')
INSERT INTO Brands(name) VALUES('KleanBebe')
INSERT INTO Brands(name) VALUES('Microdyn')
INSERT INTO Brands(name) VALUES('Tresemme')
INSERT INTO Brands(name) VALUES('Gloria')
INSERT INTO Brands(name) VALUES('Lala')
INSERT INTO Brands(name) VALUES('Sanissimo') 
INSERT INTO Brands(name) VALUES('Kellogs')
INSERT INTO Brands(name) VALUES('Generic')

INSERT INTO Categories(name) VALUES('Abarrotes')
INSERT INTO Categories(name) VALUES('Lacteos y Huevo')
INSERT INTO Categories(name) VALUES('Alimentos Congelados')
INSERT INTO Categories(name) VALUES('Frutas, Verduras y Especias')
INSERT INTO Categories(name) VALUES('Salchichoneria')
INSERT INTO Categories(name) VALUES('Panaderia y Tortilleria')
INSERT INTO Categories(name) VALUES('Dulces')
INSERT INTO Categories(name) VALUES('Carnes, Pollo y Pescado')
INSERT INTO Categories(name) VALUES('Cleaners')
INSERT INTO Categories(name) VALUES('Higiene y Belleza') 
INSERT INTO Categories(name) VALUES('Limpieza del Hogar y Desechables')
INSERT INTO Categories(name) VALUES('Farmacia')
INSERT INTO Categories(name) VALUES('Vinos y Licores')
INSERT INTO Categories(name) VALUES('Zapatos y Accesorios')
INSERT INTO Categories(name) VALUES('Bebes')

INSERT INTO items(name, category_id) VALUES('Tomate Huaje', 4)
INSERT INTO items(name, category_id) VALUES('Tomate Fresadilla', 4)
INSERT INTO items(name, category_id) VALUES('Naranja Valencia', 4)
INSERT INTO items(name, category_id) VALUES('Chile Jalapeño', 4)
INSERT INTO items(name, category_id) VALUES('Uva Blanca sin semilla', 4)
INSERT INTO items(name, category_id) VALUES('Cilantro', 4)
INSERT INTO items(name, category_id) VALUES('Camote amarillo', 4)
INSERT INTO items(name, category_id) VALUES('Platano', 4)
INSERT INTO items(name, category_id) VALUES('Chayote sin espinas', 4)
INSERT INTO items(name, category_id) VALUES('Manzana Golden', 4)
INSERT INTO items(name, brand_id, category_id) VALUES('Huevo Blanco 30 piezas', 1, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Corn Pops', 19, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Silken Coco 3 en 1', 2, 10)
INSERT INTO items(name, brand_id, category_id) VALUES('Leche UHT Soya Natural', 3, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Butter Krust pan para hot-dog', 10, 6)
INSERT INTO items(name, brand_id, category_id) VALUES('Twist 20oz', 10, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Dr B 20oz', 10, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Original Cola 20oz', 10, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Mantequilla Untable', 16, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Leche UHT Deslactosada', 17, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Gelatina Fresa', 6, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Gelatina Limon', 6, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Tostadas Horneadas de Maiz', 18, 6)
INSERT INTO items(name, brand_id, category_id) VALUES('Leche Vainilla 200ml', 3, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Salchicha de Pavo', 4, 5)
INSERT INTO items(name, brand_id, category_id) VALUES('Queso Crema', 5, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Leche Fresa 200ml', 3, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Gelatina Cereza', 6, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Milanesa Marinada 550g', 7, 8)
INSERT INTO items(name, brand_id, category_id) VALUES('Pasta codo 2 200g', 8, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Fideo 180g', 9, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Milanesa Pulpa Negra regular', 10, 8)
INSERT INTO items(name, brand_id, category_id) VALUES('Molida Pulpa fina 90/10', 10, 8)
INSERT INTO items(name, brand_id, category_id) VALUES('Leche Yakult 5 pack', 11, 2)
INSERT INTO items(name, brand_id, category_id) VALUES('Jabon Barra individual', 12, 10)
INSERT INTO items(name, brand_id, category_id) VALUES('Pasta Pepita 200g', 9, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Toallitas humedas con Aroma 100 unidades', 13, 14)
INSERT INTO items(name, brand_id, category_id) VALUES('Desinfectante 90ml', 14, 1)
INSERT INTO items(name, brand_id, category_id) VALUES('Shampoo Control Caida', 15, 10)

INSERT INTO Stores(name) VALUES('HEB')
INSERT INTO Stores(name) VALUES('Soriana')
INSERT INTO Stores(name) VALUES('Walmart')
INSERT INTO Stores(name) VALUES('Costco')

INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-01-06',1, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-03-31',2, 1)
INSERT INTO Shopping_Lists(shopping_date, store_id, user_id) VALUES('2020-05-20',3, 1)

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(2, 1.055, 3995, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(3, 2.0, 1295, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(1, 0.355, 2395, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(4, 0.290, 1895, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(5, 0.320, 8995, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(6, 1.0, 695, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(7, 0.200, 4695, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(8, 0.795, 1395, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(9, 0.695, 1695, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(10, 0.240, 6995, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(11, 1.0, 6300, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(12, 1.0, 7290, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(13, 1.0, 7890, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(14, 2.0, 2590, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(15, 1.0, 2550, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(16, 1.0, 1090, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(17, 1.0, 1090, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(18, 1.0, 1090, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(19, 1.0, 7000, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(20, 2.0, 2150, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(21, 1.0, 790, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(22, 1.0, 790, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(23, 1.0, 2690, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(24, 3.0, 900, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(25, 1.0, 4890, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(26, 1.0, 3290, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(27, 3.0, 900, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(28, 1.0, 790, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(29, 1.0, 7490, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(30, 1.0, 620, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(31, 2.0, 470, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(32, 0.425, 21200, 1,'KG', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(33, 1.0, 6929, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(34, 1.0, 2950, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(35, 1.0, 2790, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(36, 1.0, 3490, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(37, 1.0, 3890, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(38, 1.0, 3395, 1,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(39, 1.0, 5890, 1,'Unit', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(11, 1.0, 2400, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(14, 1.0, 2800, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(23, 1.0, 5890, 2,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(32, 0.500, 19000, 2,'KG', 'MXN')

INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(11, 1.0, 2300, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(30, 2.0, 500, 3,'Unit', 'MXN')
INSERT INTO shopping_items(item_id, quantity, unit_price, shopping_list_id, unit, currency) VALUES(32, 1.5, 19000, 3,'KG', 'MXN')
