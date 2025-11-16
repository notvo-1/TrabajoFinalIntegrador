USE gestion_comercio;

-- CATALOGOS

INSERT INTO marcas (nombre_marca, logo) VALUES
('Samsung', ''),
('LG', ''),
('Sony', ''),
('Apple', ''),
('HP', ''),
('DELL', ''),
('Lenovo', ''),
('Philips', ''),
('Whirlpool', ''),
('Bosch', '');

INSERT INTO categorias (nombre_categoria, descripcion) VALUES
('Celulares', 'Smartphones y accesorios'),
('Computación', 'Notebook, PC y periféricos'),
('Televisores', 'Televisores y accesorios'),
('Audio', 'Parlantes y auriculares'),
('Linea Blanca', 'Electrodomésticos para el hogar'),
('Pequeños Electrodomésticos', 'Cafeteras, licuadoras, etc.'),
('Climatización', 'Aires y ventiladores'),
('Cuidado Personal', 'Secadores, planchitas, afeitadoras'),
('Consolas', 'Consolas de videojuegos'),
('Herramientas', 'Herramientas eléctricas y manuales');

INSERT INTO tipo_codigo_barras (nombre, descripcion) VALUES
('EAN13', 'European Article Number de 13 dígitos'),
('EAN8', 'European Article Number de 8 dígitos'),
('UPC', 'Universal Product Code');

-- CODIGOS DE BARRAS Y PRODUCTOS

INSERT INTO codigo_barras (eliminado, tipo_id, valor, fecha_asignacion) VALUES
(FALSE, 1, 'PROD00000001', CURDATE()),
(FALSE, 1, 'PROD00000002', CURDATE()),
(FALSE, 1, 'PROD00000003', CURDATE()),
(FALSE, 2, 'PROD00000004', CURDATE()),
(FALSE, 2, 'PROD00000005', CURDATE()),
(FALSE, 2, 'PROD00000006', CURDATE()),
(FALSE, 3, 'PROD00000007', CURDATE()),
(FALSE, 3, 'PROD00000008', CURDATE()),
(FALSE, 3, 'PROD00000009', CURDATE()),
(FALSE, 1, 'PROD00000010', CURDATE()),
(FALSE, 1, 'PROD00000011', CURDATE()),
(FALSE, 2, 'PROD00000012', CURDATE()),
(FALSE, 2, 'PROD00000013', CURDATE()),
(FALSE, 3, 'PROD00000014', CURDATE()),
(FALSE, 3, 'PROD00000015', CURDATE()),
(FALSE, 1, 'PROD00000016', CURDATE()),
(FALSE, 2, 'PROD00000017', CURDATE()),
(FALSE, 3, 'PROD00000018', CURDATE()),
(FALSE, 1, 'PROD00000019', CURDATE()),
(FALSE, 2, 'PROD00000020', CURDATE());

INSERT INTO productos (eliminado, nombre_producto, marca_id, categoria_id, precio, peso, codigo_id) VALUES
(FALSE, 'Galaxy S24', 1, 1, 999.99, 0.200, 1),
(FALSE, 'iPhone 15', 4, 1, 1299.00, 0.190, 2),
(FALSE, 'Smart TV 55"', 3, 3, 899.50, 12.500, 3),
(FALSE, 'Notebook Pavilion', 5, 2, 1200.00, 2.300, 4),
(FALSE, 'Auriculares WH1000XM5', 3, 4, 350.00, 0.250, 5),
(FALSE, 'Heladera Frost', 9, 5, 800.00, 50.000, 6),
(FALSE, 'Cafetera Express', 10, 6, 250.00, 3.500, 7),
(FALSE, 'Ventilador Turbo', 2, 7, 120.00, 4.000, 8),
(FALSE, 'Plancha ProStyle', 14, 8, 90.00, 1.200, 9),
(FALSE, 'PlayStation 5', 3, 9, 799.00, 4.500, 10),
(FALSE, 'Notebook Inspiron', 6, 2, 1100.00, 2.200, 11),
(FALSE, 'Afeitadora 5000', 14, 8, 130.00, 0.700, 12),
(FALSE, 'Licuadora PowerMix', 15, 6, 85.00, 1.800, 13),
(FALSE, 'Parlante Bluetooth', 19, 4, 180.00, 1.000, 14),
(FALSE, 'Notebook Legion', 7, 2, 1500.00, 2.600, 15),
(FALSE, 'TV OLED 65"', 3, 3, 2000.00, 18.000, 16),
(FALSE, 'SmartWatch Series 9', 4, 1, 499.99, 0.100, 17),
(FALSE, 'Aire Split 4500', 1, 7, 850.00, 35.000, 18),
(FALSE, 'Lavarropas EcoWash', 12, 5, 700.00, 45.000, 19),
(FALSE, 'Microondas Compact', 10, 6, 300.00, 15.000, 20);
