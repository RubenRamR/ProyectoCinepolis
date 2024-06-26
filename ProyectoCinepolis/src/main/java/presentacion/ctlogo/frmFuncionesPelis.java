/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.ctlogo;

import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.FuncionNegocio;
import negocio.IFuncionNegocio;
import negocio.IPeliculaNegocio;
import negocio.NegocioException;
import negocio.PeliculaNegocio;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.IConexionBD;
import persistencia.IFuncionDAO;
import persistencia.IPeliculaDAO;
import persistencia.PeliculaDAO;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author caarl
 */
public class frmFuncionesPelis extends javax.swing.JFrame {
    private IPeliculaNegocio peliculaNegocio;
    private IFuncionNegocio funcionNegocio;
    private IConexionBD conexionBD;
    private IPeliculaDAO peliculaDAO;
    private IFuncionDAO funcionDAO;
    private PeliculaDTO pelicula;
    private int idPelicula;
    private int idSucursal;
    private int pagina;
    private int limit;
    private int offset;
    frmMenuCatalogo frameAnterior; 
    private int idCliente;
    
    
    
    public frmFuncionesPelis(frmMenuCatalogo frameAnterior, int idSucursal, int idPelicula, int idCliente) {
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.idPelicula = idPelicula;
        this.frameAnterior = frameAnterior;
        
        pagina = 1;
        limit = 5;
        offset = 0;
        
        conexionBD = new ConexionBD();
        peliculaDAO = new PeliculaDAO(conexionBD);
        funcionDAO = new FuncionDAO(conexionBD);
        
        funcionNegocio = new FuncionNegocio(funcionDAO);
        peliculaNegocio = new PeliculaNegocio(peliculaDAO);
        
        try {
            pelicula = peliculaNegocio.consultarPeliculaPorID(idPelicula);
        } catch (NegocioException ex) {
            System.out.println("Error en " + ex.getMessage());
        }

        initComponents();
        cargarMetodosIniciales();
        inicializarCamposTexto();

        
    }
    
    private void inicializarCamposTexto(){
        txtNombre.setText(pelicula.getTitulo());
        txtDuracion.setText(pelicula.getDuracion().toString());
        comboGenero.setSelectedItem(pelicula.getGenero());
        textAreaSinopsis.setText(pelicula.getSinopsis());
        txtPaisOrigen.setText(pelicula.getPaisOrigen());
        txtTrailer.setText(pelicula.getTrailerLink());
        comboClasificacion.setSelectedItem( (Object) pelicula.getClasificacion());
        labelid.setText(String.valueOf(idPelicula));
    }
    
    private void llenarTablaFunciones(List<FuncionDTO> funcionesLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblFuncionesPeli.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }
        if (funcionesLista != null) {
            funcionesLista.forEach(row -> {
                Object[] fila = new Object[5];
                fila[0] = row.getId();
                fila[1] = row.getDia();
                fila[2] = row.getInicio();
                modeloTabla.addRow(fila);
            });
        }
    }
     
    private void cargarFuncionesEnTabla() {
        try {
            List<FuncionDTO> funcion = this.funcionNegocio.consultarFuncionesPorPeliculaYSucursal(idSucursal, idPelicula, limit, offset);
            this.llenarTablaFunciones(funcion);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void cargarMetodosIniciales(){
        this.cargarConfiguracionInicialTablaPelicula();
        this.cargarFuncionesEnTabla();
    }
    
    private void cargarConfiguracionInicialTablaPelicula() {
        ActionListener onComprarFuncionClickListener = (ActionEvent e) -> {
            comprarFuncion();
        };
        
        ActionListener onEliminarFuncionClickListener = (ActionEvent e) -> {
            eliminarFuncion();
        };
        int indiceColumnaComprarFuncion = 3;
        int indiceColumnaEliminarFuncion = 4;
        TableColumnModel modeloColumnas = this.tblFuncionesPeli.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaComprarFuncion).setCellRenderer(new JButtonRenderer("Comprar"));
        modeloColumnas.getColumn(indiceColumnaComprarFuncion).setCellEditor(new JButtonCellEditor("Comprar",onComprarFuncionClickListener));
        
        modeloColumnas.getColumn(indiceColumnaEliminarFuncion).setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminarFuncion).setCellEditor(new JButtonCellEditor("Eliminar",onEliminarFuncionClickListener));
    }

    public void comprarFuncion(){
        int idFuncion = (int) tblFuncionesPeli.getValueAt(tblFuncionesPeli.getSelectedRow(), 0);
        frmCompraBoletos fcb = new frmCompraBoletos(this.funcionNegocio, idFuncion, idCliente);
        fcb.setVisible(true);
    }
    
    public void eliminarFuncion(){
        int idFuncion = (int) tblFuncionesPeli.getValueAt(tblFuncionesPeli.getSelectedRow(), 0);
        FuncionDTO funcion = new FuncionDTO();
        funcion.setId(idFuncion);
        
        try {
            this.funcionNegocio.eliminarFuncion(funcion);
            cargarFuncionesEnTabla();
        } catch (NegocioException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaSinopsis = new javax.swing.JTextArea();
        comboClasificacion = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFuncionesPeli = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCrearFuncion = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPaisOrigen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTrailer = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelid = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textAreaSinopsis.setColumns(20);
        textAreaSinopsis.setRows(5);
        jScrollPane1.setViewportView(textAreaSinopsis);

        comboClasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "B15", "AB", "R" }));
        comboClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClasificacionActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel3.setFont(new java.awt.Font("Leelawadee", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cinepolis");

        btnRegresar.setBackground(new java.awt.Color(0, 0, 102));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegresar))
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        btnEditar.setText("Editar Pelicula");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tblFuncionesPeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Dia", "Inicio", "Comprar", "Eliminar"
            }
        ));
        jScrollPane2.setViewportView(tblFuncionesPeli);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Sinopsis:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Película: ");

        btnCrearFuncion.setText("Crear Funcion");
        btnCrearFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFuncionActionPerformed(evt);
            }
        });

        txtNombre.setText("Nombre");
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel1.setText("Clasificacion");

        jLabel5.setText("Duracion");

        jLabel6.setText("Genero");

        txtPaisOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisOrigenActionPerformed(evt);
            }
        });

        jLabel8.setText("Pais origen");

        txtTrailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrailerActionPerformed(evt);
            }
        });

        jLabel9.setText("Trailer");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID:");

        labelid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelid.setForeground(new java.awt.Color(255, 255, 255));
        labelid.setText("0");

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblPagina.setText("1");

        jLabel11.setText("Pagina");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Action", "Adventure", "Animation", "Crime", "Drama", "Fantasy", "Romance", "Sci-Fi" }));
        comboGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGeneroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(btnAtras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel9)
                                .addGap(156, 156, 156))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSiguiente)
                                .addGap(55, 55, 55))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(labelid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(btnCrearFuncion))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPaisOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(191, 191, 191)
                .addComponent(btnEditar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCrearFuncion))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(labelid))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblPagina)
                    .addComponent(btnAtras)
                    .addComponent(btnSiguiente))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaisOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        PeliculaDTO peliculaDTO = new PeliculaDTO();

        peliculaDTO.setId(idPelicula);
        peliculaDTO.setTitulo(txtNombre.getText());
        peliculaDTO.setClasificacion( (String) comboClasificacion.getSelectedItem());
        peliculaDTO.setDuracion(Time.valueOf(txtDuracion.getText()));
        peliculaDTO.setPaisOrigen(txtPaisOrigen.getText());
        peliculaDTO.setGenero((String) comboGenero.getSelectedItem());
        peliculaDTO.setTrailerLink(txtTrailer.getText());
        peliculaDTO.setSinopsis(textAreaSinopsis.getText());
        
        try {
            peliculaNegocio.editarPelicula(peliculaDTO);
            JOptionPane.showMessageDialog(this, "Se editó correctamente la pelicula ");
            frameAnterior.cargarPeliculasEnTabla();
        } catch (NegocioException ex) {
            System.out.println("Error en " + ex.getMessage());
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        frameAnterior.setVisible(true);
        frameAnterior.conseguirGananciasDeLaSucursal();
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void comboClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClasificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboClasificacionActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        offset = offset + limit;
        pagina = pagina + 1;
        lblPagina.setText(String.valueOf(pagina));
        cargarFuncionesEnTabla();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if(offset - limit >= 0){
            offset = offset - limit;
            pagina = pagina - 1;
            lblPagina.setText(String.valueOf(pagina));
            cargarFuncionesEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Pagina minima alcanzada ");
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnCrearFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFuncionActionPerformed

        frmAgregarFunciones faf = new frmAgregarFunciones(this, funcionNegocio, idPelicula);
        faf.setVisible(true);
        
    }//GEN-LAST:event_btnCrearFuncionActionPerformed

    private void comboGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGeneroActionPerformed

    private void txtPaisOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisOrigenActionPerformed

    private void txtTrailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrailerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrailerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCrearFuncion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> comboClasificacion;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelid;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JTable tblFuncionesPeli;
    private javax.swing.JTextArea textAreaSinopsis;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaisOrigen;
    private javax.swing.JTextField txtTrailer;
    // End of variables declaration//GEN-END:variables
}
