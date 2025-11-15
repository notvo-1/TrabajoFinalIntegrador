/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.sql.*;
import java.util.List;
/**
 *
 * @author notvo
 */
public interface GenericService<T> {

    void insertar(T entidad) throws SQLException;
    
    void insertarTx(T entidad, Connection conn) throws SQLException;

    void actualizar(T entidad) throws SQLException;

    void eliminar(int id) throws SQLException;

    T getById(int id) throws SQLException;

    List<T> getAll() throws SQLException;
}
