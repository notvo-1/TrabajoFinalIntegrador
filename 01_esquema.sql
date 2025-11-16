DROP DATABASE IF EXISTS gestion_comercio;
CREATE DATABASE gestion_comercio;
USE gestion_comercio;

-- TABLAS BASE

CREATE TABLE tipo_codigo_barras (
    tipo_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE marcas (
    marca_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_marca VARCHAR(50) NOT NULL UNIQUE,
    logo VARCHAR(200)
);

CREATE TABLE categorias (
    categoria_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE codigo_barras (
    codigo_id INT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    tipo_id INT NOT NULL CHECK (tipo_id >= 1 AND tipo_id <= 3),
    valor VARCHAR(20) NOT NULL UNIQUE,
    fecha_asignacion DATE,
    observaciones VARCHAR(255),
    CONSTRAINT FK_tipo_codigo FOREIGN KEY (tipo_id)
        REFERENCES tipo_codigo_barras(tipo_id)
);

CREATE TABLE productos (
    producto_id INT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    nombre_producto VARCHAR(50) NOT NULL CHECK (nombre_producto <> ''),
    marca_id INT NOT NULL CHECK (marca_id >= 1 AND marca_id <= 20),
    categoria_id INT NOT NULL CHECK (categoria_id >= 1 AND categoria_id <= 10),
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    peso DOUBLE CHECK (peso IS NULL OR peso > 0),
    codigo_id INT NOT NULL UNIQUE,
    CONSTRAINT FK_marca FOREIGN KEY (marca_id) REFERENCES marcas(marca_id),
    CONSTRAINT FK_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(categoria_id),
    CONSTRAINT FK_codigo_barras FOREIGN KEY (codigo_id) REFERENCES codigo_barras(codigo_id)
);

-- ÍNDICES

CREATE INDEX idx_productos_nombre ON productos(nombre_producto);
CREATE INDEX idx_productos_eliminado_nombre ON productos(eliminado, nombre_producto);
CREATE INDEX idx_codigo_barras_tipo_id ON codigo_barras(tipo_id);
CREATE INDEX idx_productos_precios ON productos(precio);
CREATE INDEX idx_productos_precio_eliminado_1 ON productos(precio, eliminado);
