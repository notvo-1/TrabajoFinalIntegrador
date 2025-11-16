CREATE DATABASE gestion_comercio2;

USE gestion_comercio2;

-- Tabla codigo_barras (sin tipo_codigo_barras)
CREATE TABLE codigo_barras (
    codigo_id INT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    tipo_id INT NOT NULL CHECK(tipo_id >= 1 AND tipo_id <= 3),
    valor VARCHAR(20) NOT NULL UNIQUE,
    fecha_asignacion DATE,
    observaciones VARCHAR(255)
);

-- Tabla productos (sin marcas ni categorias)
CREATE TABLE productos(
    producto_id INT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    nombre_producto VARCHAR(50) NOT NULL CHECK(nombre_producto <> ''),
    marca_id INT NOT NULL,  -- ❌ SIN foreign key, solo número
    categoria_id INT NOT NULL,  -- ❌ SIN foreign key, solo número  
    precio DECIMAL(10,2) NOT NULL CHECK(precio >= 0),
    peso DOUBLE CHECK(peso IS NULL OR peso > 0),
    codigo_id INT NOT NULL UNIQUE,
    CONSTRAINT FK_codigo_barras
    FOREIGN KEY (codigo_id)
    REFERENCES codigo_barras(codigo_id)

);

SELECT * FROM productos;