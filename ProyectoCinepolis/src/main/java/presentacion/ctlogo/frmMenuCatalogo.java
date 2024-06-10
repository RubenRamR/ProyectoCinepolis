/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.ctlogo;

import dtos.PeliculaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.IPeliculaNegocio;
import negocio.NegocioException;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author caarl
 */
public class frmMenuCatalogo extends javax.swing.JFrame {
    
    JFrame frameAnterior;
    private IPeliculaNegocio peliculaNegocio;
    private List<PeliculaDTO> listaPeliculas;
    
    public frmMenuCatalogo(JFrame frameAnterior, IPeliculaNegocio peliculaNegocio) {
        this.peliculaNegocio = peliculaNegocio;
        this.frameAnterior = frameAnterior;
        inicializarListaPelis();
        initComponents();
        cargarMetodosIniciales();
    }
    
    private void llenarTablaAlumnos(List<PeliculaDTO> peliculasLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblCatalogo.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }
        if (peliculasLista != null) {
            peliculasLista.forEach(row -> {
                Object[] fila = new Object[5];
                fila[0] = row.getId();
                fila[1] = row.getTitulo();
                modeloTabla.addRow(fila);
            });
        }
    }
     
    private void cargarPeliculasEnTabla() {
        try {
            List<PeliculaDTO> alumnos = this.peliculaNegocio.consultarPeliculasPorSucursal(1, 3, 0);
            this.llenarTablaAlumnos(alumnos);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void inicializarListaPelis() {
        try {
            listaPeliculas = peliculaNegocio.consultarPeliculasPorSucursal(1, 3, 0);
        } catch (NegocioException ex) {
            System.out.println("Error en " + ex.getMessage());
        }
    }
    
    private void cargarMetodosIniciales(){
        this.cargarConfiguracionInicialTablaPelicula();
        this.cargarPeliculasEnTabla();
    }
    
    private void cargarConfiguracionInicialTablaPelicula() {
        ActionListener onVerFuncionesClickListener = (ActionEvent e) -> {
            verFunciones();
        };
        int indiceColumnaVerFunciones = 2;
        TableColumnModel modeloColumnas = this.tblCatalogo.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaVerFunciones).setCellRenderer(new JButtonRenderer("Ver funciones"));
        modeloColumnas.getColumn(indiceColumnaVerFunciones).setCellEditor(new JButtonCellEditor("Ver funciones",onVerFuncionesClickListener));
    }

    public void verFunciones(){
        frmFuncionesPelis fun = new frmFuncionesPelis();
        fun.setVisible(true);
        this.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePane1 = new javax.swing.JPanel();
        botonPeliculas1 = new javax.swing.JPanel();
        labelPeliculas1 = new javax.swing.JLabel();
        indicador6 = new javax.swing.JPanel();
        iconoPeliculas1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        info1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnCatalogoClientes = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();
        btnAgregarPeliculas = new javax.swing.JButton();
        btnAgregarPeliculas1 = new javax.swing.JButton();
        btnIrPorSucursal = new javax.swing.JButton();
        panelHerramientas1 = new javax.swing.JPanel();
        iconoMinimizar1 = new javax.swing.JLabel();
        iconoCerrar1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCatalogo = new javax.swing.JTable();
        comboSucursales = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidePane1.setBackground(new java.awt.Color(0, 0, 102));
        sidePane1.setForeground(new java.awt.Color(255, 255, 255));
        sidePane1.setAutoscrolls(true);

        botonPeliculas1.setBackground(new java.awt.Color(0, 0, 51));
        botonPeliculas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonPeliculas1MousePressed(evt);
            }
        });

        labelPeliculas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPeliculas1.setForeground(new java.awt.Color(204, 204, 204));
        labelPeliculas1.setText("Películas");

        indicador6.setOpaque(false);
        indicador6.setPreferredSize(new java.awt.Dimension(3, 0));

        javax.swing.GroupLayout indicador6Layout = new javax.swing.GroupLayout(indicador6);
        indicador6.setLayout(indicador6Layout);
        indicador6Layout.setHorizontalGroup(
            indicador6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        indicador6Layout.setVerticalGroup(
            indicador6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        iconoPeliculas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        iconoPeliculas1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout botonPeliculas1Layout = new javax.swing.GroupLayout(botonPeliculas1);
        botonPeliculas1.setLayout(botonPeliculas1Layout);
        botonPeliculas1Layout.setHorizontalGroup(
            botonPeliculas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonPeliculas1Layout.createSequentialGroup()
                .addComponent(indicador6, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoPeliculas1)
                .addGap(1, 1, 1)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPeliculas1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botonPeliculas1Layout.setVerticalGroup(
            botonPeliculas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(indicador6, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
            .addComponent(iconoPeliculas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(botonPeliculas1Layout.createSequentialGroup()
                .addGroup(botonPeliculas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(botonPeliculas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(botonPeliculas1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel7))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botonPeliculas1Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(labelPeliculas1)))
                    .addGroup(botonPeliculas1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        info1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                info1MouseClicked(evt);
            }
        });

        jLabel3.setMinimumSize(new java.awt.Dimension(300, 300));

        btnCatalogoClientes.setBackground(new java.awt.Color(0, 0, 102));
        btnCatalogoClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnCatalogoClientes.setText("Catalogo Clientes");
        btnCatalogoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoClientesActionPerformed(evt);
            }
        });

        btnCerrarSesion1.setBackground(new java.awt.Color(0, 0, 102));
        btnCerrarSesion1.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion1.setText("Cerrar Sesión");
        btnCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion1ActionPerformed(evt);
            }
        });

        btnAgregarPeliculas.setBackground(new java.awt.Color(0, 0, 102));
        btnAgregarPeliculas.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPeliculas.setText("Agregar Péliculas");
        btnAgregarPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPeliculasActionPerformed(evt);
            }
        });

        btnAgregarPeliculas1.setBackground(new java.awt.Color(0, 0, 102));
        btnAgregarPeliculas1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPeliculas1.setText("Modulo Reportes");
        btnAgregarPeliculas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPeliculas1ActionPerformed(evt);
            }
        });

        btnIrPorSucursal.setBackground(new java.awt.Color(0, 0, 102));
        btnIrPorSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnIrPorSucursal.setText("Ver peliculas");
        btnIrPorSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrPorSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePane1Layout = new javax.swing.GroupLayout(sidePane1);
        sidePane1.setLayout(sidePane1Layout);
        sidePane1Layout.setHorizontalGroup(
            sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonPeliculas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sidePane1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btnCatalogoClientes))
                            .addGroup(sidePane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarPeliculas)
                                    .addComponent(btnAgregarPeliculas1)))))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(info1))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2)))
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIrPorSucursal)
                    .addComponent(btnCerrarSesion1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidePane1Layout.setVerticalGroup(
            sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addComponent(btnCatalogoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnAgregarPeliculas)
                        .addGap(29, 29, 29)))
                .addGap(1, 1, 1)
                .addComponent(btnAgregarPeliculas1)
                .addGap(78, 78, 78)
                .addComponent(botonPeliculas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(btnIrPorSucursal)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(info1)
                .addGap(93, 93, 93)
                .addComponent(btnCerrarSesion1)
                .addGap(8, 8, 8))
        );

        panelHerramientas1.setBackground(new java.awt.Color(0, 0, 102));
        panelHerramientas1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHerramientas1MouseDragged(evt);
            }
        });
        panelHerramientas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelHerramientas1MousePressed(evt);
            }
        });

        iconoMinimizar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconoMinimizar1MouseClicked(evt);
            }
        });

        iconoCerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconoCerrar1MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cinepolis");

        javax.swing.GroupLayout panelHerramientas1Layout = new javax.swing.GroupLayout(panelHerramientas1);
        panelHerramientas1.setLayout(panelHerramientas1Layout);
        panelHerramientas1Layout.setHorizontalGroup(
            panelHerramientas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHerramientas1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoMinimizar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoCerrar1)
                .addContainerGap(242, Short.MAX_VALUE))
        );
        panelHerramientas1Layout.setVerticalGroup(
            panelHerramientas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHerramientas1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHerramientas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoCerrar1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconoMinimizar1))
                .addGap(16, 16, 16))
            .addGroup(panelHerramientas1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSiguiente.setText(">");

        btnAtras.setText("<");

        jLabel1.setText("Pagina No. 1");

        tblCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Titulo", "Ver Funcion"
            }
        ));
        jScrollPane2.setViewportView(tblCatalogo);

        comboSucursales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cinepolis bella vista", "Cinepolis de hermosillo", "Cinepolis de navojoa", "Cinepolis de culiacan", "Cinepolis de mazatlan" }));
        comboSucursales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboSucursalesFocusGained(evt);
            }
        });
        comboSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSucursalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidePane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelHerramientas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(comboSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAtras)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelHerramientas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(sidePane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(3, 3, 3)))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonPeliculas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPeliculas1MousePressed

    }//GEN-LAST:event_botonPeliculas1MousePressed

    private void info1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_info1MouseClicked

    }//GEN-LAST:event_info1MouseClicked

    private void btnCatalogoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoClientesActionPerformed

    }//GEN-LAST:event_btnCatalogoClientesActionPerformed

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        frameAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed

    private void iconoMinimizar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoMinimizar1MouseClicked

    }//GEN-LAST:event_iconoMinimizar1MouseClicked

    private void iconoCerrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoCerrar1MouseClicked

    }//GEN-LAST:event_iconoCerrar1MouseClicked

    private void panelHerramientas1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHerramientas1MouseDragged

    }//GEN-LAST:event_panelHerramientas1MouseDragged

    private void panelHerramientas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHerramientas1MousePressed

    }//GEN-LAST:event_panelHerramientas1MousePressed

    private void btnAgregarPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPeliculasActionPerformed

        frmAgregarPeli fap = new frmAgregarPeli(this.peliculaNegocio);
        fap.setVisible(true);
    }//GEN-LAST:event_btnAgregarPeliculasActionPerformed

    private void btnAgregarPeliculas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPeliculas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarPeliculas1ActionPerformed

    private void btnIrPorSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrPorSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIrPorSucursalActionPerformed

    private void comboSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSucursalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSucursalesActionPerformed

    private void comboSucursalesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboSucursalesFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSucursalesFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel botonPeliculas1;
    private javax.swing.JButton btnAgregarPeliculas;
    private javax.swing.JButton btnAgregarPeliculas1;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCatalogoClientes;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnIrPorSucursal;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> comboSucursales;
    private javax.swing.JLabel iconoCerrar1;
    private javax.swing.JLabel iconoMinimizar1;
    private javax.swing.JLabel iconoPeliculas1;
    public javax.swing.JPanel indicador6;
    private javax.swing.JLabel info1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelPeliculas1;
    private javax.swing.JPanel panelHerramientas1;
    private javax.swing.JPanel sidePane1;
    private javax.swing.JTable tblCatalogo;
    // End of variables declaration//GEN-END:variables
}
