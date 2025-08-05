package Modelo;
import java.util.Date;

public class Tarea { //Tarea dentro de un proyecto
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaVencimiento;
    private String estado;
    private String prioridad;
    private int projectId;

    // Constructor
    public Tarea(int id, String nombre, String descripcion, Date fechaVencimiento, String estado, String prioridad, int projectId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.prioridad = prioridad;
        this.projectId = projectId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public int getProjectId() {
        return projectId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}