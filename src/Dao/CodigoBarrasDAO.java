/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.DatabaseConnection;
import Models.CodigoBarras;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author notvo
 */

public class CodigoBarrasDAO implements GenericDAO<CodigoBarras> {

    //PreparedStatement
    private static final String INSERT_SQL = "INSERT INTO codigo_barras (tipo_id, valor, fecha_asignacion, observaciones) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE codigo_barras SET tipo_id = ?, valor = ?, fecha_asignacion = ?, observaciones = ? WHERE codigo_id = ?";

    private static final String DELETE_SQL = "UPDATE codigo_barras SET eliminado = TRUE WHERE codigo_id = ?";

    private static final String SELECT_BY_ID_SQL
            = "SELECT codigo_id, tipo_id, valor, fecha_asignacion, observaciones "
            + "FROM codigo_barras "
            + "WHERE codigo_id = ? AND eliminado = FALSE";

    private static final String SELECT_ALL_SQL
            = "SELECT codigo_id, tipo_id, valor, fecha_asignacion, observaciones "
            + "FROM codigo_barras "
            + "WHERE eliminado = FALSE";

    private static final String SEARCH_BY_NAME_SQL
            = "SELECT codigo_id, tipo_id, valor, fecha_asignacion, observaciones "
            + "FROM codigo_barras "
            + "WHERE eliminado = FALSE AND valor LIKE ?";

    public CodigoBarrasDAO() {
    }

    @Override
    public void crear(CodigoBarras codigobarras) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setCodigoBarrasParameters(stmt, codigobarras);
            stmt.executeUpdate();
            setGeneratedId(stmt, codigobarras);
        }
    }

    @Override
    public void crearTx(CodigoBarras codigobarras, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setCodigoBarrasParameters(stmt, codigobarras);
            stmt.executeUpdate();
            setGeneratedId(stmt, codigobarras);

        }
    }

    @Override
    public CodigoBarras leer(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCodigoBarras(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<CodigoBarras> leertodos() throws SQLException {
        List<CodigoBarras> productos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                productos.add(mapResultSetToCodigoBarras(rs));
            }
        }
        return productos;
    }

    @Override
    public void actualizar(CodigoBarras codigobarras) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            setCodigoBarrasParameters(stmt, codigobarras);
            stmt.setInt(5, codigobarras.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar el Codigo de Barras con ID: " + codigobarras.getId());
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontr� el Codigo de Barras con ID: " + id);
            }
        }
    }

    private void setCodigoBarrasParameters(PreparedStatement stmt, CodigoBarras codigobarras) throws SQLException {
        stmt.setInt(1,codigobarras.getTipoId()); // tipo codigo id
        stmt.setString(2, codigobarras.getValor()); // valor
        LocalDate fecha = codigobarras.getFechaAsignacion(); // fecha con conversion de localdate a date
        java.sql.Date sqlfecha = (fecha != null) ? java.sql.Date.valueOf(fecha): null;
        stmt.setDate(3, sqlfecha);
        stmt.setString(4, codigobarras.getObservaciones()); // obervaciones
        
    }
    

    private void setGeneratedId(PreparedStatement stmt, CodigoBarras codigobarras) throws SQLException {
        try(ResultSet generateKeys = stmt.getGeneratedKeys()){
            if (generateKeys.next()) {
                codigobarras.setId(generateKeys.getInt(1));
            }else{
                throw new SQLException("La insercion de la persona fallo, no se obtudo el ID generado.");
            }
        }
    }

    private CodigoBarras mapResultSetToCodigoBarras(ResultSet rs) throws SQLException {
    CodigoBarras codigo = new CodigoBarras();
    codigo.setId(rs.getInt("codigo_id"));
    codigo.setTipoId(rs.getInt("tipo_id"));
    codigo.setValor(rs.getString("valor"));
    
    java.sql.Date sqlDate = rs.getDate("fecha_asignacion");
    codigo.setFechaAsignacion(sqlDate != null ? sqlDate.toLocalDate() : null);
    
    codigo.setObservaciones(rs.getString("observaciones"));
    return codigo;
    }



}
