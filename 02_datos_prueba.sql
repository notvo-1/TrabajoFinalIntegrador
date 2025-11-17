USE gestion_comercio2;

-- DATOS DE PRUEBA PARA CODIGO_BARRAS
INSERT INTO codigo_barras (eliminado, tipo_id, valor, fecha_asignacion, observaciones) VALUES
(FALSE, 1, 'CB000001', CURDATE(), 'Asignado a producto Galaxy S24'),
(FALSE, 1, 'CB000002', CURDATE(), 'Asignado a producto iPhone 15'),
(FALSE, 2, 'CB000003', CURDATE(), 'Asignado a producto Smart TV 50"'),
(FALSE, 2, 'CB000004', CURDATE(), 'Asignado a producto Notebook HP'),
(FALSE, 3, 'CB000005', CURDATE(), 'Asignado a producto Cafetera Express'),
(FALSE, 3, 'CB000006', CURDATE(), 'Asignado a producto Lavarropas Eco'),
(FALSE, 1, 'CB000007', CURDATE(), 'Asignado a producto Aire Acondicionado'),
(FALSE, 2, 'CB000008', CURDATE(), 'Asignado a producto Auriculares Sony'),
(FALSE, 3, 'CB000009', CURDATE(), 'Asignado a producto Parlante Bluetooth'),
(FALSE, 1, 'CB000010', CURDATE(), 'Asignado a producto Tablet Samsung');

-- DATOS DE PRUEBA PARA PRODUCTOS
INSERT INTO productos (eliminado, nombre_producto, marca_id, categoria_id, precio, peso, codigo_id) VALUES
(FALSE, 'Galaxy S24', 1, 1, 999.99, 0.200, 1),
(FALSE, 'iPhone 15', 2, 1, 1299.00, 0.190, 2),
(FALSE, 'Smart TV 50"', 3, 2, 850.00, 12.500, 3),
(FALSE, 'Notebook HP', 4, 2, 1100.00, 2.300, 4),
(FALSE, 'Cafetera Express', 5, 3, 250.00, 3.500, 5),
(FALSE, 'Lavarropas Eco', 6, 4, 700.00, 50.000, 6),
(FALSE, 'Aire Acondicionado Split', 7, 5, 850.00, 35.000, 7),
(FALSE, 'Auriculares Sony WH1000XM5', 8, 6, 320.00, 0.250, 8),
(FALSE, 'Parlante Bluetooth JBL', 9, 6, 180.00, 1.000, 9),
(FALSE, 'Tablet Samsung A9', 10, 1, 450.00, 0.400, 10);
