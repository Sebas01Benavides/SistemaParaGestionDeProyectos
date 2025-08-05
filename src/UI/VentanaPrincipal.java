package UI;

import DAO.ProyectoBD;
import DAO.TareaBD;
import Modelo.Proyecto;
import Modelo.Tarea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebas
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    private ProyectoBD proyectoBD;
    private Proyecto proyectoSeleccionado;
    private TareaBD tareaBD;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        proyectoBD = new ProyectoBD();
        tareaBD = new TareaBD();
        loadProjectsInTable();
        updateProjectCounters();
        jTableProyectos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTableProyectosMouseClicked(evt);
            }
        });
    }
    // Método para cargar y mostrar los proyectos en la JTable
    public void loadProjectsInTable() {
        try {
        List<Proyecto> proyectos = proyectoBD.getAllProyectos();
        DefaultTableModel model = (DefaultTableModel) jTableProyectos.getModel();
        model.setRowCount(0);
        for (Proyecto proyecto : proyectos) {
            Object[] row = new Object[]{
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin()
            };
            model.addRow(row);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los proyectos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }     
    }
    
    private void jTableProyectosMouseClicked(MouseEvent evt) {
    int filaSeleccionada = jTableProyectos.getSelectedRow();
    if (filaSeleccionada >= 0) {
        try {
            String nombreProyecto = (String) jTableProyectos.getValueAt(filaSeleccionada, 0); 
            
            // Suponiendo que tienes un método para obtener todos los proyectos
            List<Proyecto> proyectos = proyectoBD.getAllProyectos();
            for (Proyecto p : proyectos) {
                if (p.getNombre().equals(nombreProyecto)) {
                    proyectoSeleccionado = p;
                    System.out.println("Proyecto seleccionado: " + proyectoSeleccionado.getNombre());
                    break;
                }
            }
            
            // Llama al nuevo método para cargar las tareas del proyecto seleccionado
            if (proyectoSeleccionado != null) {
                loadTasksInTable(proyectoSeleccionado.getId());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar el proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
    
    public void loadTasksInTable(int projectId) {
    // Definir las columnas para la tabla de tareas
    String[] columnas = {"ID", "Nombre", "Descripción", "Fecha Inicio", "Fecha Fin", "Estado"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);
    jTableTareas.setModel(model); // Asigna el modelo a tu JTable

    try {
        // Obtener la lista de tareas del proyecto
        List<Tarea> tareas = tareaBD.getAllTareasByProjectId(projectId);
        // Llenar la tabla con los datos de las tareas
        for (Tarea tarea : tareas) {
            Object[] row = new Object[]{
                tarea.getId(),
                tarea.getNombre(),
                tarea.getDescripcion(),
                tarea.getFechaInicio(),
                tarea.getFechaFin(),
                tarea.getEstado()
            };
            model.addRow(row);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar las tareas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    public void updateProjectCounters() {
    try {
        List<Proyecto> proyectos = proyectoBD.getAllProyectos();
        int pendientes = 0;
        int finalizados = 0;
        // Lógica simple para contar proyectos pendientes y finalizados
        for (Proyecto proyecto : proyectos) {
            // Se asume que un proyecto está "finalizado" si su fecha de fin ya pasó.
            if (proyecto.getFechaFin().before(new java.util.Date())) {
                finalizados++;
            } else {
                pendientes++;
            }
        }
        
        // Asigna los valores a los JTextField
        txtProyectosPendientes.setText(String.valueOf(pendientes));
        txtProyectosFinalizados.setText(String.valueOf(finalizados));
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar los contadores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProyectos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtProyectosPendientes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProyectosFinalizados = new javax.swing.JTextField();
        btnAgregarTarea = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTareas = new javax.swing.JTable();
        btnActualizarTarea = new javax.swing.JButton();
        btnEliminarTarea = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de proyectos");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Hola, bienvenido.");

        jTableProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Fecha inicio", "Fecha fin"
            }
        ));
        jScrollPane1.setViewportView(jTableProyectos);

        jLabel2.setText("Proyectos pendientes");

        jLabel3.setText("Proyectos finalizados");

        btnAgregarTarea.setText("Agregar tarea");
        btnAgregarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTareaActionPerformed(evt);
            }
        });

        jTableTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripción", "Fecha inicio", "Fecha fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableTareas);

        btnActualizarTarea.setText("Actualizar tarea");
        btnActualizarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTareaActionPerformed(evt);
            }
        });

        btnEliminarTarea.setText("Eliminar tarea");
        btnEliminarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTareaActionPerformed(evt);
            }
        });

        jMenu1.setText("Mis proyectos");

        jMenuItem1.setText("Crear proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Mis proyectos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProyectosFinalizados, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProyectosPendientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarTarea)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnAgregarTarea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtProyectosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTarea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProyectosFinalizados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizarTarea)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CrearProyectoDialog dialog = new CrearProyectoDialog(this, true);
        dialog.setVisible(true);
        loadProjectsInTable();
        updateProjectCounters();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        MisProyectosDialog dialog = new MisProyectosDialog(this, true);
        dialog.setVisible(true);    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnAgregarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTareaActionPerformed
    if (proyectoSeleccionado != null) {
        CrearTareaDialog dialog = new CrearTareaDialog(this, true, proyectoSeleccionado);
        dialog.setVisible(true);
        // Después de que el diálogo se cierra, vuelve a cargar las tareas
        loadTasksInTable(proyectoSeleccionado.getId());

    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un proyecto primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnAgregarTareaActionPerformed

    private void btnActualizarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTareaActionPerformed
        int filaSeleccionada = jTableTareas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtenemos los datos de la fila seleccionada
            int id = (int) jTableTareas.getValueAt(filaSeleccionada, 0);
            String nombre = (String) jTableTareas.getValueAt(filaSeleccionada, 1);
            String descripcion = (String) jTableTareas.getValueAt(filaSeleccionada, 2);
            Date fechaInicio = (Date) jTableTareas.getValueAt(filaSeleccionada, 3);
            Date fechaFin = (Date) jTableTareas.getValueAt(filaSeleccionada, 4);
            String estado = (String) jTableTareas.getValueAt(filaSeleccionada, 5);

            // Creamos un objeto Tarea con los datos de la fila
            Tarea tareaSeleccionada = new Tarea(id, nombre, descripcion, fechaInicio, fechaFin, estado, proyectoSeleccionado.getId());
            
            CrearTareaDialog dialog = new CrearTareaDialog(this, true, tareaSeleccionada);
            dialog.setVisible(true);
            loadTasksInTable(proyectoSeleccionado.getId());
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione la tarea a actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarTareaActionPerformed

    private void btnEliminarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTareaActionPerformed
        int filaSeleccionada = jTableTareas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar la tarea seleccionada?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    int tareaId = (int) jTableTareas.getValueAt(filaSeleccionada, 0); 
                    tareaBD.deleteTarea(tareaId);
                    loadTasksInTable(proyectoSeleccionado.getId());
                    JOptionPane.showMessageDialog(this, "Tarea eliminada con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione la tarea a eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarTareaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarTarea;
    private javax.swing.JButton btnAgregarTarea;
    private javax.swing.JButton btnEliminarTarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProyectos;
    private javax.swing.JTable jTableTareas;
    private javax.swing.JTextField txtProyectosFinalizados;
    private javax.swing.JTextField txtProyectosPendientes;
    // End of variables declaration//GEN-END:variables
}
