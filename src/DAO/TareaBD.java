package DAO;

import Modelo.Tarea;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TareaBD {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/tu_bd";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    private Connection getConnection() throws SQLException {
        return java.sql.DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void addTarea(Tarea tarea) {
        String sql = "INSERT INTO tasks (nombre, descripcion, fecha_inicio, fecha_fin, estado, id_proyecto) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tarea.getNombre());
            pstmt.setString(2, tarea.getDescripcion());
            pstmt.setDate(3, new Date(tarea.getFechaInicio().getTime()));
            pstmt.setDate(4, new Date(tarea.getFechaFin().getTime()));
            pstmt.setString(5, tarea.getEstado());
            pstmt.setInt(6, tarea.getIdProyecto());
            pstmt.executeUpdate();
            System.out.println("Tarea guardada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(TareaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Tarea> getAllTareasByProjectId(int projectId) {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE id_proyecto = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    java.util.Date fechaInicio = rs.getDate("fecha_inicio");
                    java.util.Date fechaFin = rs.getDate("fecha_fin");
                    String estado = rs.getString("estado");
                    int idProyecto = rs.getInt("id_proyecto");
                    
                    Tarea tarea = new Tarea(id, nombre, descripcion, fechaInicio, fechaFin, estado, idProyecto);
                    tareas.add(tarea);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TareaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tareas;
    }

    public void updateTarea(Tarea tarea) {
        String sql = "UPDATE tasks SET nombre=?, descripcion=?, fecha_inicio=?, fecha_fin=?, estado=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tarea.getNombre());
            pstmt.setString(2, tarea.getDescripcion());
            pstmt.setDate(3, new java.sql.Date(tarea.getFechaInicio().getTime()));
            pstmt.setDate(4, new java.sql.Date(tarea.getFechaFin().getTime()));
            pstmt.setString(5, tarea.getEstado());
            pstmt.setInt(6, tarea.getId());
            pstmt.executeUpdate();
            System.out.println("Tarea actualizada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(TareaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTarea(int tareaId) {
        String sql = "DELETE FROM tasks WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tareaId);
            pstmt.executeUpdate();
            System.out.println("Tarea eliminada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(TareaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}