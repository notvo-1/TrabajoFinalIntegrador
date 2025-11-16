/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.DatabaseConnection;
import Models.Producto;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author notvo
 */
public class ProductoDAO implements GenericDAO<Producto> {

    //PreparedStatement
    private static final String INSERT_SQL = "INSERT INTO productos (nombre_producto, marca_id, categoria_id, precio, peso, codigo_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE productos SET nombre_producto = ?, marca_id = ?, categoria_id = ?, precio = ?, peso = ?, codigo_id = ? WHERE producto_id = ?";

    private static final String DELETE_SQL = "UPDATE productos SET eliminado = TRUE WHERE producto_id = ?";

private static final String SELECT_BY_ID_SQL =
    "SELECT producto_id, nombre_producto, marca_id, categoria_id, " +
    "precio, peso, codigo_id " +
    "FROM productos " +
    "WHERE producto_id = ? AND eliminado = FALSE";

private static final String SELECT_ALL_SQL = 
    "SELECT producto_id, nombre_producto, marca_id, categoria_id, " +
    "precio, peso, codigo_id " +
    "FROM productos " +
    "WHERE eliminado = FALSE";

private static final String SEARCH_BY_NAME_SQL = 
    "SELECT producto_id, nombre_producto, marca_id, categoria_id, " +
    "precio, peso, codigo_id " +
    "FROM productos " +
    "WHERE eliminado = FALSE AND nombre_producto LIKE ?";

// voy a usar DAOs tontos
//    //atributo y relacion con codigoBarraDao 
//    private final CodigoBarrasDao codigoBarrasDao;
//
//    // constructor
//    public ProductoDAO(CodigoBarrasDao codigoBarrasDao) {
//        if (codigoBarrasDao == null) {
//            throw new IllegalArgumentException("CodigoBarraDao no puede ser null");
//        }
//        this.codigoBarrasDao = codigoBarrasDao;
//    }

    public ProductoDAO() {
    }

    // metodos de genericDao para manejar los inserts, update, busquedas y deletes.
    // respecto a los errores se los maneja directamente en el service
    @Override
    public void crear(Producto producto) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setProductoParameters(stmt, producto);
            stmt.executeUpdate();
            setGeneratedId(stmt, producto);
        }
    }

    // crear con la conexion como parametro
    @Override
    public void crearTx(Producto producto, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setProductoParameters(stmt, producto);
            stmt.executeUpdate();
            setGeneratedId(stmt, producto);

        }
    }

    // este es getbyid en el ejemplo
    @Override
    public Producto leer(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProducto(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> leertodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                productos.add(mapResultSetToProducto(rs));
            }
        }
        return productos;
    }

    /**
     * Actualiza un producto existente en la base de datos Acutaliza nombre,
     * idmarca, idcategoria, precio y
     *
     * @param producto
     * @throws SQLException
     */
    @Override
    public void actualizar(Producto producto) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            setProductoParameters(stmt, producto);
            stmt.setInt(7, producto.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar el producto con ID: " + producto.getId());
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontro el producto con ID: " + id);
            }
        }
    }
    
    public List<Producto> buscarPorNombre(String nombreOMarca) throws SQLException{
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(SEARCH_BY_NAME_SQL)) {
              stmt.setString(1, nombreOMarca);
              
              try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapResultSetToProducto(rs));
                }
            }catch(SQLException e){
                throw e;
            }
        }catch(SQLException e){
            throw e;
        }
        return productos;
    }
    
    /**
     * Setea los parametros de un producto para un PreraedStatement.
     * Metodo aiziliar usado por Crear, crearTx y actualizar.
     * Parametros seteados: 
     * Nombre (string)
     * IdMarca (int)
     * idCategoria (int)
     * Precio (double)
     * Peso (double)
     * idCodigo (int)
     * @param stmt
     * @param producto
     * @throws SQLException 
     */
    private void setProductoParameters(PreparedStatement stmt, Producto producto) throws SQLException {
        stmt.setString(1, producto.getNombre());
        stmt.setInt(2, producto.getIdMarca());
        stmt.setInt(3, producto.getIdCategoria());
        stmt.setDouble(4, producto.getPrecio());
        stmt.setDouble(5, producto.getPeso());
        stmt.setInt(6, producto.getIdCodigo());
    }

    private void setGeneratedId(PreparedStatement stmt, Producto producto) throws SQLException {
        try(ResultSet generateKeys = stmt.getGeneratedKeys()){
            if (generateKeys.next()) {
                producto.setId(generateKeys.getInt(1));
            }else{
                throw new SQLException("La insercion de la persona fallo, no se obtudo el ID generado.");
            }
        }
    }

    private Producto mapResultSetToProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("producto_id"));
        producto.setNombre(rs.getString("nombre_producto"));
        producto.setIdMarca(rs.getInt("marca_id"));
        producto.setIdCategoria(rs.getInt("categoria_id"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setPeso(rs.getDouble("peso"));
        producto.setIdCodigo(rs.getInt("codigo_id")); 
        
        return producto;
    }

}
