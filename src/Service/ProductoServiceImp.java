/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.ProductoDAO;
import Models.Producto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author notvo
 */
public class ProductoServiceImp implements GenericService<Producto> {

    private final ProductoDAO productoDAO; // mediante inyeccion 

    private final CodigoBarrasServiceImp codigoBarrasSeriviceImp;

    public ProductoServiceImp(ProductoDAO productoDAO, CodigoBarrasServiceImp codigoBarrasSeriviceImp) {
        if (productoDAO == null) {
            throw new IllegalArgumentException("ProductoDao no puede ser null");
        }
        if (codigoBarrasSeriviceImp == null) {
            throw new IllegalArgumentException("CodigoBarraServiceImp no puede ser null");
        }

        this.productoDAO = productoDAO;
        this.codigoBarrasSeriviceImp = codigoBarrasSeriviceImp;
    }

    public CodigoBarrasServiceImp getCodigoBarras(){
        return this.codigoBarrasSeriviceImp;
    }
    
    @Override
    public void insertar(Producto producto) throws SQLException, IllegalArgumentException {
        validateProducto(producto);
        
    }

    @Override
    public void actualizar(Producto producto) throws SQLException {
        if (producto.getId() <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor a 0 para actualizar");
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto getById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void validateProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Producto> buscarPorNombre(String nombre) throws SQLException{
         if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        return productoDAO.buscarPorNombre(nombre);
    }
}
