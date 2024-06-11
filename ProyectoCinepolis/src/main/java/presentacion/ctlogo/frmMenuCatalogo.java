/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.ctlogo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dtos.PeliculaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.ClienteNegocio;
import negocio.IClienteNegocio;
import negocio.IPeliculaNegocio;
import negocio.ISucursalNegocio;
import negocio.NegocioException;
import negocio.PeliculaNegocio;
import negocio.SucursalNegocio;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.SucursalDAO;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author caarl
 */
public class frmMenuCatalogo extends javax.swing.JFrame {

    JFrame frameAnterior;
    private IPeliculaNegocio peliculaNegocio;
    private IConexionBD conexionBD;
    private int idSucursal;
    private int idPelicula;
    private int limit;
    private int offset;
    private int pagina;
    private int idCliente;

    public frmMenuCatalogo(JFrame frameAnterior, IPeliculaNegocio peliculaNegocio, int idCliente) {
        this.idCliente = idCliente;
        this.peliculaNegocio = peliculaNegocio;
        this.frameAnterior = frameAnterior;

        idPelicula = 1;
        idSucursal = 1;
        limit = 5;
        offset = 0;
        pagina = 1;

        initComponents();
        cargarMetodosIniciales();
        conseguirGananciasDeLaSucursal();
        conseguirGananciasDeLaPeliculaSeleccionada();
    }

    public void conseguirGananciasDeLaSucursal() {
        conexionBD = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDAO);

        try
        {
            lblGanancias.setText(String.valueOf(sucursalNegocio.calcularGananciasPorSucursal(idSucursal)));
        } catch (NegocioException ex)
        {
            System.out.println("Erroren :" + ex.getMessage());
        }

    }

    public void conseguirGananciasDeLaPeliculaSeleccionada() {
        // Obtener la fila seleccionada
        int selectedRow = tblCatalogo.getSelectedRow();
        if (selectedRow != -1)
        {
            // Obtener el ID de la película desde la fila seleccionada
            idPelicula = (int) tblCatalogo.getValueAt(selectedRow, 0);

            conexionBD = new ConexionBD();
            IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
            IPeliculaNegocio peliculaNegocio = new PeliculaNegocio(peliculaDAO);

            try
            {
                lblGananciasPelicula.setText(String.valueOf(peliculaNegocio.calcularGananciasPorPelicula(idPelicula)));
            } catch (NegocioException ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void llenarTablaPeliculas(List<PeliculaDTO> peliculasLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblCatalogo.getModel();
        if (modeloTabla.getRowCount() > 0)
        {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--)
            {
                modeloTabla.removeRow(i);
            }
        }
        if (peliculasLista != null)
        {
            peliculasLista.forEach(row ->
            {
                Object[] fila = new Object[3];
                fila[0] = row.getId();
                fila[1] = row.getTitulo();
                modeloTabla.addRow(fila);
            });
        }
    }

    public void cargarPeliculasEnTabla() {
        try
        {
            List<PeliculaDTO> peliculas = this.peliculaNegocio.consultarPeliculasPorSucursal(idSucursal, limit, offset);
            this.llenarTablaPeliculas(peliculas);
        } catch (NegocioException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void cargarMetodosIniciales() {
        this.cargarConfiguracionInicialTablaPelicula();
        this.cargarPeliculasEnTabla();

        tblCatalogo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting())
                {
                    conseguirGananciasDeLaPeliculaSeleccionada();
                }
            }
        });
    }

    private void cargarConfiguracionInicialTablaPelicula() {
        ActionListener onVerFuncionesClickListener = (ActionEvent e) ->
        {
            verFunciones();
        };
        int indiceColumnaVerFunciones = 2;
        TableColumnModel modeloColumnas = this.tblCatalogo.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaVerFunciones).setCellRenderer(new JButtonRenderer("Ver funciones"));
        modeloColumnas.getColumn(indiceColumnaVerFunciones).setCellEditor(new JButtonCellEditor("Ver funciones", onVerFuncionesClickListener));
    }

    public void verFunciones() {
        int idPelicula = (int) tblCatalogo.getValueAt(tblCatalogo.getSelectedRow(), 0);
        frmFuncionesPelis fun = new frmFuncionesPelis(this, idSucursal, idPelicula, idCliente);
        fun.setVisible(true);

        this.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePane1 = new javax.swing.JPanel();
        botonPeliculas1 = new javax.swing.JPanel();
        indicador6 = new javax.swing.JPanel();
        iconoPeliculas1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        labelPeliculas1 = new javax.swing.JLabel();
        info1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnCatalogoClientes = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();
        btnAgregarPeliculas = new javax.swing.JButton();
        btnGenerarPDFSucursal = new javax.swing.JButton();
        btnGenerarPDFSucursal1 = new javax.swing.JButton();
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
        lblPagina = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblGanancias = new javax.swing.JLabel();
        btnUbicarme = new javax.swing.JButton();
        LblGanPel = new javax.swing.JLabel();
        lblGananciasPelicula = new javax.swing.JLabel();

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

        labelPeliculas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPeliculas1.setForeground(new java.awt.Color(204, 204, 204));
        labelPeliculas1.setText("Películas");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPeliculas1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        botonPeliculas1Layout.setVerticalGroup(
            botonPeliculas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(indicador6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
            .addComponent(iconoPeliculas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(botonPeliculas1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7))
            .addGroup(botonPeliculas1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botonPeliculas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPeliculas1)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        btnGenerarPDFSucursal.setBackground(new java.awt.Color(0, 0, 102));
        btnGenerarPDFSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDFSucursal.setText("PDF Ganacias por pelicula");
        btnGenerarPDFSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFSucursalActionPerformed(evt);
            }
        });

        btnGenerarPDFSucursal1.setBackground(new java.awt.Color(0, 0, 102));
        btnGenerarPDFSucursal1.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDFSucursal1.setText("PDF Ganacias por sucursal");
        btnGenerarPDFSucursal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFSucursal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidePane1Layout = new javax.swing.GroupLayout(sidePane1);
        sidePane1.setLayout(sidePane1Layout);
        sidePane1Layout.setHorizontalGroup(
            sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCatalogoClientes)
                            .addComponent(btnAgregarPeliculas)))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonPeliculas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(info1)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarPDFSucursal1)
                    .addComponent(btnGenerarPDFSucursal)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnCerrarSesion1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidePane1Layout.setVerticalGroup(
            sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePane1Layout.createSequentialGroup()
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel16))
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonPeliculas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(sidePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePane1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCatalogoClientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarPeliculas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnGenerarPDFSucursal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarPDFSucursal)
                .addGap(185, 185, 185)
                .addComponent(btnCerrarSesion1)
                .addGap(169, 169, 169)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(info1)
                .addGap(152, 152, 152))
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
                .addContainerGap(263, Short.MAX_VALUE))
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
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAtras.setText("<");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel1.setText("Pagina No.");

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

        lblPagina.setText("1");

        jLabel5.setText("Ganancias de esta sucursal:");

        btnUbicarme.setBackground(new java.awt.Color(255, 51, 255));
        btnUbicarme.setText("Ubicarme");

        LblGanPel.setText("Ganancias de la pelicula seleccionada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(lblGanancias, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel5)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblGanPel)
                                    .addComponent(btnUbicarme)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(lblGananciasPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54)))))
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidePane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelHerramientas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicarme))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LblGanPel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblGanancias, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGananciasPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(jLabel1)
                    .addComponent(lblPagina)
                    .addComponent(btnSiguiente))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(panelHerramientas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 421, Short.MAX_VALUE))
                        .addComponent(sidePane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonPeliculas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPeliculas1MousePressed

    }//GEN-LAST:event_botonPeliculas1MousePressed

    private void info1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_info1MouseClicked

    }//GEN-LAST:event_info1MouseClicked

    private void btnCatalogoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoClientesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);

        frmCatalogoClientes fcc = new frmCatalogoClientes(clienteNegocio);
        fcc.setVisible(true);
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

        frmAgregarPeli fap = new frmAgregarPeli(this, this.peliculaNegocio, idSucursal);
        fap.setVisible(true);
    }//GEN-LAST:event_btnAgregarPeliculasActionPerformed

    private void btnGenerarPDFSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarPDFSucursalActionPerformed

    private void comboSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSucursalesActionPerformed
        // TODO add your handling code here:
        idSucursal = comboSucursales.getSelectedIndex() + 1;
        conseguirGananciasDeLaSucursal();
        cargarPeliculasEnTabla();
    }//GEN-LAST:event_comboSucursalesActionPerformed

    private void comboSucursalesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboSucursalesFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSucursalesFocusGained

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        offset = offset + limit;
        pagina = pagina + 1;
        lblPagina.setText(String.valueOf(pagina));
        cargarPeliculasEnTabla();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (offset - limit >= 0)
        {
            offset = offset - limit;
            pagina = pagina - 1;
            lblPagina.setText(String.valueOf(pagina));
            cargarPeliculasEnTabla();
        } else
        {
            JOptionPane.showMessageDialog(this, "Pagina minima alcanzada ");
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnGenerarPDFSucursal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFSucursal1ActionPerformed
        String sucursalSeleccionada = (String) comboSucursales.getSelectedItem();
        String ganancias = lblGanancias.getText();

        Document document = new Document(PageSize.A4); // Tamaño A4 para formato estándar
        try
        {
            PdfWriter.getInstance(document, new FileOutputStream("Reporte.pdf"));
            document.open();
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font fontContenido = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            // Título del documento
            Paragraph titulo = new Paragraph("Reporte de Ganancias", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" ")); // Espacio en blanco

            // Crear una tabla para los detalles del reporte
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Configurar las celdas de la tabla
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Sucursal:", fontContenido));
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(sucursalSeleccionada, fontContenido));
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Ganancias:", fontContenido));
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(ganancias, fontContenido));
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            document.add(table);
        } catch (DocumentException | FileNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            document.close();
            JOptionPane.showMessageDialog(null, "Se creó el archivo 'Reporte.pdf' en la carpeta del proyecto");
        }
    }//GEN-LAST:event_btnGenerarPDFSucursal1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblGanPel;
    public javax.swing.JPanel botonPeliculas1;
    private javax.swing.JButton btnAgregarPeliculas;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCatalogoClientes;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnGenerarPDFSucursal;
    private javax.swing.JButton btnGenerarPDFSucursal1;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUbicarme;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelPeliculas1;
    private javax.swing.JLabel lblGanancias;
    private javax.swing.JLabel lblGananciasPelicula;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JPanel panelHerramientas1;
    private javax.swing.JPanel sidePane1;
    private javax.swing.JTable tblCatalogo;
    // End of variables declaration//GEN-END:variables
}
