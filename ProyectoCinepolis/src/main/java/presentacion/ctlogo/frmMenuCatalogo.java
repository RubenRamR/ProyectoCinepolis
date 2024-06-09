/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.ctlogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableColumnModel;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author caarl
 */
public class frmMenuCatalogo extends javax.swing.JFrame {

    /**
     * Creates new form frmMenuCatalogo
     */
    public frmMenuCatalogo() {
        initComponents();
        cargarConfiguracionInicialTablaPelicula();
    }
    
    private void cargarConfiguracionInicialTablaPelicula() {
        ActionListener onVerFuncionesClickListener = (ActionEvent e) ->
        {
            verFunciones();
        };
        int indiceColumnaVerFunciones = 1;
        TableColumnModel modeloColumnas = this.tblCatalogo.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaVerFunciones)
                .setCellRenderer(new JButtonRenderer("Ver funciones"));
        modeloColumnas.getColumn(indiceColumnaVerFunciones)
                .setCellEditor(new JButtonCellEditor("Ver funciones",
                        onVerFuncionesClickListener));
    }
    
    public void verFunciones(){
        frmFuncionesPelis fun = new frmFuncionesPelis();
        
        fun.setVisible(true);
        
        this.setVisible(false);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePane1 = new javax.swing.JPanel();
        botonPrincipal1 = new javax.swing.JPanel();
        labelPrincipal1 = new javax.swing.JLabel();
        iconoPrincipal1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        botonClientes1 = new javax.swing.JPanel();
        indicador5 = new javax.swing.JPanel();
        labelClientes1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        iconoClientes1 = new javax.swing.JLabel();
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
        btnAgregarFunciones = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnCerrarSesion1 = new javax.swing.JButton();
        btnAgregarPeliculas = new javax.swing.JButton();
        panelHerramientas1 = new javax.swing.JPanel();
        iconoMinimizar1 = new javax.swing.JLabel();
        iconoCerrar1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCatalogo = new javax.swing.JTable();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidePane1.setBackground(new java.awt.Color(0, 0, 102));
        sidePane1.setForeground(new java.awt.Color(255, 255, 255));
        sidePane1.setAutoscrolls(true);

        botonPrincipal1.setBackground(new java.awt.Color(0, 0, 102));
        botonPrincipal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonPrincipal1MousePressed(evt);
            }
        });

        labelPrincipal1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPrincipal1.setForeground(new java.awt.Color(204, 204, 204));
        labelPrincipal1.setText("Principal");

        iconoPrincipal1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        iconoPrincipal1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout botonPrincipal1Layout = new javax.swing.GroupLayout(botonPrincipal1);
        botonPrincipal1.setLayout(botonPrincipal1Layout);
        botonPrincipal1Layout.setHorizontalGroup(
            botonPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonPrincipal1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoPrincipal1)
                .addGap(0, 0, 0)
                .addComponent(labelPrincipal1)
                .addContainerGap())
        );
        botonPrincipal1Layout.setVerticalGroup(
            botonPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoPrincipal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelPrincipal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(botonPrincipal1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botonPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonClientes1.setBackground(new java.awt.Color(0, 0, 102));
        botonClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonClientes1MousePressed(evt);
            }
        });

        indicador5.setOpaque(false);
        indicador5.setPreferredSize(new java.awt.Dimension(3, 0));

        javax.swing.GroupLayout indicador5Layout = new javax.swing.GroupLayout(indicador5);
        indicador5.setLayout(indicador5Layout);
        indicador5Layout.setHorizontalGroup(
            indicador5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        indicador5Layout.setVerticalGroup(
            indicador5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        labelClientes1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelClientes1.setForeground(new java.awt.Color(204, 204, 204));

        iconoClientes1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        iconoClientes1.setForeground(new java.awt.Color(204, 204, 204));
        iconoClientes1.setText("Clientes");

        javax.swing.GroupLayout botonClientes1Layout = new javax.swing.GroupLayout(botonClientes1);
        botonClientes1.setLayout(botonClientes1Layout);
        botonClientes1Layout.setHorizontalGroup(
            botonClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonClientes1Layout.createSequentialGroup()
                .addComponent(indicador5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconoClientes1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelClientes1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        botonClientes1Layout.setVerticalGroup(
            botonClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(indicador5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(labelClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(botonClientes1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botonClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoClientes1)
                    .addComponent(jLabel19)
                    .addComponent(jLabel6))
                .addContainerGap(19, Short.MAX_VALUE))
        );

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

        btnAgregarFunciones.setBackground(new java.awt.Color(0, 0, 102));
        btnAgregarFunciones.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarFunciones.setText("Agregar Funciones");
        btnAgregarFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFuncionesActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sucursal Bella Vista", "Sucursal Sendero", " ", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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

        javax.swing.GroupLayout sidePane1Layout = new javax.swing.GroupLayout(sidePane1);
        sidePane1.setLayout(sidePane1Layout);
        sidePane1Layout.setHorizontalGroup(
            sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addComponent(botonPrincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(botonPeliculas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(info1))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarFunciones)
                    .addComponent(btnCerrarSesion1)
                    .addComponent(btnAgregarPeliculas))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        sidePane1Layout.setVerticalGroup(
            sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addGap(32, 32, 32)
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addComponent(botonPrincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(botonPeliculas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(info1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarFunciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarPeliculas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(208, Short.MAX_VALUE))
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

        tblCatalogo.setBackground(new java.awt.Color(65, 65, 65));
        tblCatalogo.setForeground(new java.awt.Color(65, 65, 65));
        tblCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Pelicula", "Ver funciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCatalogo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCatalogo.setSelectionForeground(new java.awt.Color(211, 211, 211));
        jScrollPane1.setViewportView(tblCatalogo);

        btnSiguiente.setText(">");

        btnAtras.setText("<");

        jLabel1.setText("Pagina No. 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidePane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelHerramientas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(565, Short.MAX_VALUE)
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
                            .addGap(37, 37, 37)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(76, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(sidePane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(3, 3, 3)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPrincipal1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPrincipal1MousePressed

    }//GEN-LAST:event_botonPrincipal1MousePressed

    private void botonClientes1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonClientes1MousePressed

    }//GEN-LAST:event_botonClientes1MousePressed

    private void botonPeliculas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPeliculas1MousePressed

    }//GEN-LAST:event_botonPeliculas1MousePressed

    private void info1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_info1MouseClicked

    }//GEN-LAST:event_info1MouseClicked

    private void btnAgregarFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFuncionesActionPerformed

    }//GEN-LAST:event_btnAgregarFuncionesActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        // TODO add your handling code here:
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
        frmAgregarPeli agpeli = new frmAgregarPeli();

        // Hace visible el nuevo formulario
        agpeli.setVisible(true);

        // Oculta el formulario actual
        this.setVisible(false);
    }//GEN-LAST:event_btnAgregarPeliculasActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenuCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuCatalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuCatalogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel botonClientes1;
    public javax.swing.JPanel botonPeliculas1;
    public javax.swing.JPanel botonPrincipal1;
    private javax.swing.JButton btnAgregarFunciones;
    private javax.swing.JButton btnAgregarPeliculas;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel iconoCerrar1;
    private javax.swing.JLabel iconoClientes1;
    private javax.swing.JLabel iconoMinimizar1;
    private javax.swing.JLabel iconoPeliculas1;
    private javax.swing.JLabel iconoPrincipal1;
    public javax.swing.JPanel indicador5;
    public javax.swing.JPanel indicador6;
    private javax.swing.JLabel info1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelClientes1;
    private javax.swing.JLabel labelPeliculas1;
    private javax.swing.JLabel labelPrincipal1;
    private javax.swing.JPanel panelHerramientas1;
    private javax.swing.JPanel sidePane1;
    private javax.swing.JTable tblCatalogo;
    // End of variables declaration//GEN-END:variables
}
