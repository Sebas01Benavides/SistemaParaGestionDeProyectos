package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Proyecto;

public class ProyectoBD {
    // Información de conexión a la base de datos
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/nombre_de_tu_bd";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    // Método para obtener la conexión
    private Connection getConnection() throws SQLException {
        return java.sql.DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    // Método para guardar un nuevo proyecto
    public void addProyecto(Proyecto proyecto) {
        String sql = "INSERT INTO projects (nombre, descripcion, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, proyecto.getNombre());
            pstmt.setString(2, proyecto.getDescripcion());
            pstmt.setDate(3, new Date(proyecto.getFechaInicio().getTime()));
            pstmt.setDate(4, new Date(proyecto.getFechaFin().getTime()));

            pstmt.executeUpdate();
            System.out.println("Proyecto guardado con éxito.");

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Método para obtener todos los proyectos
    public List<Proyecto> getAllProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");
                Proyecto proyecto = new Proyecto(id, nombre, descripcion, fechaInicio, fechaFin);
                proyectos.add(proyecto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proyectos;
    }
}
