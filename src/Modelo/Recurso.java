package Modelo;
public class Recurso {
    private int id;
    private String nombreArchivo;
    private String tipoArchivo;
    private String rutaArchivo;
    private int taskId;

    // Constructor
    public Recurso(int id, String nombreArchivo, String tipoArchivo, String rutaArchivo, int taskId) {
        this.id = id;
        this.nombreArchivo = nombreArchivo;
        this.tipoArchivo = tipoArchivo;
        this.rutaArchivo = rutaArchivo;
        this.taskId = taskId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public int getTaskId() {
        return taskId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}