CREATE DATABASE gestion_comercio2;

USE gestion_comercio2;

-- Tabla codigo_barras
CREATE TABLE codigo_barras (
    codigo_id INT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,

    -- tipo_id con rango
    tipo_id INT NOT NULL CHECK(tipo_id BETWEEN 1 AND 3),

    -- valor: único y mínimo 8 caracteres
    valor VARCHAR(20) NOT NULL UNIQUE CHECK (CHAR_LENGTH(valor) >= 8),

    fecha_asignacion DATE,
    observaciones VARCHAR(255)
);

-- Tabla productos
CREATE TABLE productos(
    producto_id INT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,

    nombre_producto VARCHAR(50) NOT NULL CHECK(nombre_producto <> ''),

    -- marca entre 1 y 20
    marca_id INT NOT NULL CHECK (marca_id BETWEEN 1 AND 20),

    -- categoría entre 1 y 10
    categoria_id INT NOT NULL CHECK (categoria_id BETWEEN 1 AND 10),

    precio DECIMAL(10,2) NOT NULL CHECK(precio >= 0),

    peso DOUBLE CHECK(peso IS NULL OR peso > 0),

    codigo_id INT NOT NULL UNIQUE,

    CONSTRAINT FK_codigo_barras
        FOREIGN KEY (codigo_id) REFERENCES codigo_barras(codigo_id)
);

SELECT * FROM productos;
