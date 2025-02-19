package somic.v1.panel.nitPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.stereotype.Component;

import somic.v1.articulo.application.service.ArticuloService;
import somic.v1.articulo.domain.entities.articulo;
import somic.v1.factura.application.service.FacturaService;
import somic.v1.factura.domain.entities.factura;
import somic.v1.nit.application.service.NitService;
import somic.v1.nit.domain.entities.nit;

@Component
public class SearchPanel extends JPanel {

    private final NitService nitService;
    private final ArticuloService articuloService;
    // Se inyecta el servicio para guardar la factura en la base de datos
    private final FacturaService facturaService;

    // Campos para búsqueda de NIT
    private JTextField tfNitDocumento;
    private JLabel lbNitNombre;
    private JLabel lbNitCupo;
    private JLabel lbNitPlazo;
    private JLabel lbNitFechaVencimiento;
    // Nuevos campos para mostrar cartera y disponible
    private JLabel lbNitCartera;
    private JLabel lbNitDisponible;

    // Campos para búsqueda de Artículo
    private JTextField tfArtCodigo;
    private JLabel lbArtNombre;
    private JLabel lbArtLaboratorio;
    private JLabel lbArtSaldo;
    private JTextField tfArtCosto; // Campo costo editable según naturaleza
    private JLabel lbArtPrecioVenta;
    private JLabel lblArtPrecioVentaTitle;
    private JComboBox<String> cbNaturaleza;
    private JTextField tfUnidades;
    // Nuevos campos para Totales Venta y Totales Costo
    private JTextField tfTotalesVenta;
    private JTextField tfTotalesCosto;

    // Al inicio de la clase, con las otras variables
    private javax.swing.table.DefaultTableModel facturaTableModel;
    private javax.swing.JTable facturaTable;

    public SearchPanel(NitService nitService, ArticuloService articuloService, FacturaService facturaService) {
        this.nitService = nitService;
        this.articuloService = articuloService;
        this.facturaService = facturaService;
        initUI();
    }

    private void initUI() {
        // Se asigna el LookAndFeel Nimbus si se desea (opcional)
        // try {
        //     for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        //         if ("Nimbus".equals(info.getName())) {
        //             UIManager.setLookAndFeel(info.getClassName());
        //             break;
        //         }
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // Paleta de colores suave
        java.awt.Color fondoPrincipal = new java.awt.Color(245, 245, 245);
        java.awt.Color fondoPanel = new java.awt.Color(232, 240, 254);
        java.awt.Color bordePanel = new java.awt.Color(200, 200, 200);
        java.awt.Color botonColor = new java.awt.Color(200, 220, 240);

        // Panel principal con BoxLayout vertical y márgenes
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(fondoPrincipal);

        // PANEL: Buscar NIT
        JPanel nitPanel = new JPanel();
        nitPanel.setLayout(new BoxLayout(nitPanel, BoxLayout.Y_AXIS));
        nitPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(bordePanel), "Buscar NIT"));
        nitPanel.setBackground(fondoPanel);

        // Panel de ingreso de datos (Documento y botón Buscar)
        JPanel nitInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        nitInputPanel.setBackground(fondoPanel);
        nitInputPanel.add(new JLabel("Documento:"));
        tfNitDocumento = new JTextField(12);
        nitInputPanel.add(tfNitDocumento);
        JButton btnBuscarNit = new JButton("Buscar NIT");
        btnBuscarNit.setBackground(botonColor);
        nitInputPanel.add(btnBuscarNit);
        nitPanel.add(nitInputPanel);
        nitPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Panel de resultados del NIT organizado en 6 filas x 2 columnas
        JPanel nitResultPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        nitResultPanel.setBackground(fondoPanel);

        nitResultPanel.add(new JLabel("Nombre:"));
        lbNitNombre = new JLabel();
        nitResultPanel.add(lbNitNombre);

        nitResultPanel.add(new JLabel("Cupo:"));
        lbNitCupo = new JLabel();
        nitResultPanel.add(lbNitCupo);

        nitResultPanel.add(new JLabel("Plazo (días):"));
        lbNitPlazo = new JLabel();
        nitResultPanel.add(lbNitPlazo);

        nitResultPanel.add(new JLabel("Fecha de Vencimiento:"));
        lbNitFechaVencimiento = new JLabel();
        nitResultPanel.add(lbNitFechaVencimiento);

        nitResultPanel.add(new JLabel("Cartera:"));
        lbNitCartera = new JLabel();
        nitResultPanel.add(lbNitCartera);

        nitResultPanel.add(new JLabel("Disponible:"));
        lbNitDisponible = new JLabel();
        nitResultPanel.add(lbNitDisponible);

        nitPanel.add(nitResultPanel);
        nitPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // PANEL: Buscar Artículo
        JPanel artPanel = new JPanel();
        artPanel.setLayout(new BoxLayout(artPanel, BoxLayout.Y_AXIS));
        artPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(bordePanel), "Buscar Artículo"));
        artPanel.setBackground(fondoPanel);

        JPanel artInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        artInputPanel.setBackground(fondoPanel);
        artInputPanel.add(new JLabel("Código:"));
        tfArtCodigo = new JTextField(12);
        artInputPanel.add(tfArtCodigo);
        JButton btnBuscarArt = new JButton("Buscar Artículo");
        btnBuscarArt.setBackground(botonColor);
        artInputPanel.add(btnBuscarArt);
        artPanel.add(artInputPanel);
        artPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Panel de resultados del Artículo con 9 filas
        JPanel artResultPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        artResultPanel.setBackground(fondoPanel);
        artResultPanel.add(new JLabel("Nombre:"));
        lbArtNombre = new JLabel();
        artResultPanel.add(lbArtNombre);

        artResultPanel.add(new JLabel("Laboratorio:"));
        lbArtLaboratorio = new JLabel();
        artResultPanel.add(lbArtLaboratorio);

        artResultPanel.add(new JLabel("Saldo:"));
        lbArtSaldo = new JLabel();
        artResultPanel.add(lbArtSaldo);

        artResultPanel.add(new JLabel("Costo:"));
        tfArtCosto = new JTextField();
        tfArtCosto.setEditable(false);
        artResultPanel.add(tfArtCosto);

        // Fila de "Precio Venta"
        lblArtPrecioVentaTitle = new JLabel("Precio Venta:");
        artResultPanel.add(lblArtPrecioVentaTitle);
        lbArtPrecioVenta = new JLabel();
        artResultPanel.add(lbArtPrecioVenta);

        // Fila de "Naturaleza"
        artResultPanel.add(new JLabel("Naturaleza:"));
        cbNaturaleza = new JComboBox<>(new String[]{"+", "-"});
        artResultPanel.add(cbNaturaleza);

        // Fila de "Unidades"
        artResultPanel.add(new JLabel("Unidades:"));
        tfUnidades = new JTextField();
        artResultPanel.add(tfUnidades);

        // Nuevas filas para "Totales Venta" y "Totales Costo"
        artResultPanel.add(new JLabel("Totales Venta:"));
        tfTotalesVenta = new JTextField();
        tfTotalesVenta.setEditable(false);
        artResultPanel.add(tfTotalesVenta);

        artResultPanel.add(new JLabel("Totales Costo:"));
        tfTotalesCosto = new JTextField();
        tfTotalesCosto.setEditable(false);
        artResultPanel.add(tfTotalesCosto);

        artPanel.add(artResultPanel);
        artPanel.add(Box.createVerticalStrut(10));

        // Se agregan los paneles de búsqueda al panel principal
        add(nitPanel);
        add(Box.createVerticalStrut(15));
        add(artPanel);

        // Agregar botón "Agregar" fuera de los dos recuadros
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(botonColor);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(fondoPrincipal);
        btnPanel.add(btnAgregar);
        add(Box.createVerticalStrut(10));
        add(btnPanel);

        // Se inicializa la tabla de facturas con estética compatible
        initFacturaTable();

        // Acciones de botones
        btnBuscarNit.addActionListener(e -> buscarNit());
        btnBuscarArt.addActionListener(e -> buscarArticulo());
        // Acción del JComboBox Naturaleza
        cbNaturaleza.addActionListener(e -> {
            String seleccionado = (String) cbNaturaleza.getSelectedItem();
            if ("+".equals(seleccionado)) {
                tfArtCosto.setEditable(true);
                lblArtPrecioVentaTitle.setVisible(false);
                lbArtPrecioVenta.setVisible(false);
                tfTotalesVenta.setVisible(false);
                tfTotalesVenta.setText("");
                tfTotalesCosto.setVisible(true);
            } else if ("-".equals(seleccionado)) {
                tfArtCosto.setEditable(false);
                lblArtPrecioVentaTitle.setVisible(true);
                lbArtPrecioVenta.setVisible(true);
                tfTotalesCosto.setVisible(false);
                tfTotalesCosto.setText("");
                tfTotalesVenta.setVisible(true);
            }
            recalcularTotales();
        });
        // Actualiza los totales cuando se modifica el campo "Unidades"
        tfUnidades.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                recalcularTotales();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                recalcularTotales();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                recalcularTotales();
            }
        });
        // Acción del botón Agregar: recolecta datos y guarda la factura
        btnAgregar.addActionListener(e -> agregarFactura());
    }

    private void initFacturaTable() {
        facturaTableModel = new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Fecha", "Unidades", "Total Venta", "NIT", "Artículo"}
        );
        facturaTable = new javax.swing.JTable(facturaTableModel);
        add(new javax.swing.JScrollPane(facturaTable));
        loadFacturas(); // Cargar facturas al inicializar
    }

    private void buscarNit() {
        String documento = tfNitDocumento.getText().trim();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un documento válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Optional<nit> optNit = nitService.getNitByDocumento(documento);
        if (optNit.isPresent()) {
            nit cliente = optNit.get();
            lbNitNombre.setText(cliente.getNombre());
            lbNitCupo.setText(cliente.getCupo().toString());
            lbNitPlazo.setText(String.valueOf(cliente.getPlazo()));
            lbNitFechaVencimiento.setText(LocalDate.now().plusDays(cliente.getPlazo()).toString());
            
            // Calcular cartera: sumar totalVenta de todas las facturas asociadas al nit
            BigDecimal cartera = facturaService.getAllFacturas().stream()
                .filter(f -> f.getNit() != null 
                        && f.getNit().getDocumento() != null 
                        && f.getNit().getDocumento().trim().equalsIgnoreCase(documento))
                .map(f -> f.getTotalVenta() == null ? BigDecimal.ZERO : f.getTotalVenta())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            lbNitCartera.setText(cartera.toString());
            
            // Calcular Disponible: cupo - cartera
            BigDecimal disponible = cliente.getCupo().subtract(cartera);
            lbNitDisponible.setText(disponible.toString());
        } else {
            JOptionPane.showMessageDialog(this, "NIT no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void buscarArticulo() {
        String codigo = tfArtCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un código válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Optional<articulo> optArt = articuloService.getArticuloByCodigo(codigo);
        if (optArt.isPresent()) {
            articulo art = optArt.get();
            lbArtNombre.setText(art.getNombre());
            lbArtLaboratorio.setText(art.getLaboratorio());
            lbArtSaldo.setText(String.valueOf(art.getSaldo()));
            tfArtCosto.setText(art.getCosto().toString());
            lbArtPrecioVenta.setText(art.getPrecioVenta().toString());
            recalcularTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Artículo no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void recalcularTotales() {
        try {
            int unidades = Integer.parseInt(tfUnidades.getText());
            String naturaleza = (String) cbNaturaleza.getSelectedItem();
            if ("-".equals(naturaleza)) {
                // Cuando la naturaleza es "-", se calcula Total Venta (Precio Venta x Unidades)
                double precioVenta = Double.parseDouble(lbArtPrecioVenta.getText());
                double totalVenta = precioVenta * unidades;
                // Se formatea usando Locale.US para garantizar que el separador decimal sea el punto.
                tfTotalesVenta.setText(String.format(java.util.Locale.US, "%.2f", totalVenta));
                tfTotalesCosto.setText("");
            } else if ("+".equals(naturaleza)) {
                // Cuando la naturaleza es "+", se calcula Total Costo (Costo x Unidades)
                double costo = Double.parseDouble(tfArtCosto.getText());
                double totalCosto = costo * unidades;
                tfTotalesCosto.setText(String.format(java.util.Locale.US, "%.2f", totalCosto));
                tfTotalesVenta.setText("");
            }
        } catch (NumberFormatException | NullPointerException e) {
            tfTotalesVenta.setText("");
            tfTotalesCosto.setText("");
        }
    }
    
    private void agregarFactura() {
        String docNit = tfNitDocumento.getText().trim();
        String codigoArt = tfArtCodigo.getText().trim();
        if (docNit.isEmpty() || codigoArt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El documento del NIT y el código del artículo son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Optional<nit> optNit = nitService.getNitByDocumento(docNit);
        Optional<articulo> optArt = articuloService.getArticuloByCodigo(codigoArt);
        if (!optNit.isPresent() || !optArt.isPresent()) {
            JOptionPane.showMessageDialog(this, "NIT o Artículo no encontrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int unidades;
        try {
            unidades = Integer.parseInt(tfUnidades.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo Unidades debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verificar que la naturaleza seleccionada sea "-"
        String naturaleza = (String) cbNaturaleza.getSelectedItem();
        if (!"-".equals(naturaleza)) {
            JOptionPane.showMessageDialog(this, "No se puede guardar la factura, ya que la naturaleza seleccionada no genera Total de Ventas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar que Unidades no exceda el Saldo (stock) del artículo
        int saldo;
        try {
            saldo = Integer.parseInt(lbArtSaldo.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener el saldo del artículo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (unidades > saldo) {
            JOptionPane.showMessageDialog(this, "El campo Unidades (" + unidades + ") no puede superar el saldo disponible del artículo (" + saldo + ").", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Calcular el Total de Ventas (Precio Venta x Unidades)
        BigDecimal totalVentaValue;
        String totalVentaText = tfTotalesVenta.getText().trim();
        if (totalVentaText.isEmpty()) {
            totalVentaValue = BigDecimal.ZERO;
        } else {
            try {
                totalVentaValue = new BigDecimal(totalVentaText);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al calcular el Total de Ventas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Verificar que Total Ventas no supere el crédito Disponible del nit
        // Se recalcule la cartera (suma de totalVenta de las facturas existentes para este nit)
        nit cliente = optNit.get();
        BigDecimal cartera = facturaService.getAllFacturas().stream()
                .filter(f -> f.getNit() != null 
                        && f.getNit().getDocumento() != null 
                        && f.getNit().getDocumento().trim().equalsIgnoreCase(docNit))
                .map(f -> f.getTotalVenta() == null ? BigDecimal.ZERO : f.getTotalVenta())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal disponible = cliente.getCupo().subtract(cartera);
        if (totalVentaValue.compareTo(disponible) > 0) {
            JOptionPane.showMessageDialog(this, "El Total de Ventas (" + totalVentaValue 
                    + ") excede el crédito disponible (" + disponible + ").", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LocalDate fecha = LocalDate.now();
        
        // Crear la nueva factura y guardarla en la base de datos
        factura fac = new factura();
        fac.setFecha(fecha);
        fac.setUnidades(unidades);
        fac.setTotalVenta(totalVentaValue);
        fac.setNit(cliente);
        fac.setArt(optArt.get());
        
        try {
            facturaService.save(fac);
            JOptionPane.showMessageDialog(this, "Factura agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            loadFacturas(); // Actualiza la tabla de facturas
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la factura en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        tfNitDocumento.setText("");
        lbNitNombre.setText("");
        lbNitCupo.setText("");
        lbNitPlazo.setText("");
        lbNitFechaVencimiento.setText("");
        tfArtCodigo.setText("");
        lbArtNombre.setText("");
        lbArtLaboratorio.setText("");
        lbArtSaldo.setText("");
        tfArtCosto.setText("");
        lbArtPrecioVenta.setText("");
        tfUnidades.setText("");
        tfTotalesVenta.setText("");
        tfTotalesCosto.setText("");
    }

    private void loadFacturas() {
        facturaTableModel.setRowCount(0); // Limpia el contenido
        facturaService.getAllFacturas().forEach(fac -> {
             facturaTableModel.addRow(new Object[]{
                 fac.getId(),
                 fac.getFecha(),
                 fac.getUnidades(),
                 fac.getTotalVenta(),
                 fac.getNit() != null ? fac.getNit().getDocumento() : "",
                 fac.getArt() != null ? fac.getArt().getCodigo() : ""
             });
        });
    }
}