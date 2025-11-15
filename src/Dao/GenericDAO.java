/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author notvo
 */
public interface GenericDAO<T> {
    
    
    public void crear(T entidad)throws SQLException;
    public void crearTx (T endidad, Connection conn) throws SQLException;
    public T leer(int id)throws SQLException;
    public List<T> leertodos()throws SQLException;
    public void actualizar(T entidad)throws SQLException;
    public void eliminar(int id)throws SQLException;
    
}
