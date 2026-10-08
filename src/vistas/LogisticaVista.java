package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class LogisticaVista extends JFrame {

    public String[] encabezados = {
        "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"
    };

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JTextField txtDistancia;

    private JComboBox<String> cmbTipoEnvio;

    private JButton btnGuardarEnvio;
    private JButton btnQuitarEnvio;

    public LogisticaVista() {

        setSize(600, 400);
        setTitle("Operador Logístico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JToolBar tbLogistica = new JToolBar();

        JButton btnAgregarEnvio = new JButton();
        btnAgregarEnvio.setIcon(
                new ImageIcon(getClass().getResource(
                        "/iconos/AgregarEnvio.png")));
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.setPreferredSize(new Dimension(75, 55));
        btnAgregarEnvio.addActionListener(evento -> agregarEnvio());
        tbLogistica.add(btnAgregarEnvio);

        btnQuitarEnvio = new JButton();
        btnQuitarEnvio.setIcon(
                new ImageIcon(getClass().getResource(
                        "/iconos/QuitarEnvio.png")));
        btnQuitarEnvio.setToolTipText("Quitar Envío");
        btnQuitarEnvio.setPreferredSize(new Dimension(75, 55));
        btnQuitarEnvio.addActionListener(evento -> quitarEnvio());
        tbLogistica.add(btnQuitarEnvio);

        /*
         * Panel de edición.
         * Se fija su tamaño mínimo para que Guardar y Cancelar
         * nunca queden ocultos.
         */
        pnlEditarEnvio = new JPanel(null);
        Dimension tamanoPanel = new Dimension(600, 115);
        pnlEditarEnvio.setPreferredSize(tamanoPanel);
        pnlEditarEnvio.setMinimumSize(tamanoPanel);
        pnlEditarEnvio.setMaximumSize(tamanoPanel);

        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox<>();
        cmbTipoEnvio.setBounds(320, 10, 110, 25);
        cmbTipoEnvio.setModel(
                new DefaultComboBoxModel<>(
                        new String[] {
                                "Terrestre",
                                "Aéreo",
                                "Marítimo"
                        }));
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 110, 25);
        pnlEditarEnvio.add(txtDistancia);

        btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        btnGuardarEnvio.addActionListener(evento -> guardarEnvio());
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(330, 70, 100, 25);
        btnCancelarEnvio.addActionListener(evento -> cancelarEnvio());
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false);

        tblEnvios = new JTable();
        tblEnvios.setModel(
                new DefaultTableModel(null, encabezados));

        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        /*
         * BorderLayout evita que el panel de edición sea comprimido.
         * NORTH = formulario completo.
         * CENTER = tabla.
         */
        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.add(pnlEditarEnvio, BorderLayout.NORTH);
        pnlPrincipal.add(spListaEnvios, BorderLayout.CENTER);

        getContentPane().add(
                tbLogistica,
                BorderLayout.NORTH);

        getContentPane().add(
                pnlPrincipal,
                BorderLayout.CENTER);
    }

    public void setGuardarEnvioClick(ActionListener listener) {
        for (ActionListener actual :
                btnGuardarEnvio.getActionListeners()) {
            btnGuardarEnvio.removeActionListener(actual);
        }

        btnGuardarEnvio.addActionListener(listener);
    }

    public void setEliminarEnvioClick(ActionListener listener) {
        for (ActionListener actual :
                btnQuitarEnvio.getActionListeners()) {
            btnQuitarEnvio.removeActionListener(actual);
        }

        btnQuitarEnvio.addActionListener(listener);
    }

    public String getTipoEnvioSeleccionado() {
        return (String) cmbTipoEnvio.getSelectedItem();
    }

    public String getCodigo() {
        return txtNumero.getText().trim();
    }

    public String getCliente() {
        return txtCliente.getText().trim();
    }

    public double getPeso() {
        return Double.parseDouble(txtPeso.getText().trim());
    }

    public double getDistancia() {
        return Double.parseDouble(txtDistancia.getText().trim());
    }

    public int getFilaEnvioSeleccionado() {
        return tblEnvios.getSelectedRow();
    }

    public void mostrarEnvios(
            Object[][] datos,
            String[] encabezados) {

        tblEnvios.setModel(
                new DefaultTableModel(datos, encabezados));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION;
    }

    public void agregarEnvio() {
        pnlEditarEnvio.setVisible(true);
        pnlEditarEnvio.revalidate();
        pnlEditarEnvio.repaint();
        txtNumero.requestFocus();
    }

    public void ocultarPanelEditarEnvio() {
        pnlEditarEnvio.setVisible(false);
    }

    public void limpiarCampos() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
    }

    public void cancelarEnvio() {
        limpiarCampos();
        ocultarPanelEditarEnvio();
    }

    public void quitarEnvio() {
        // La acción la controla LogisticaControlador.
    }

    public void guardarEnvio() {
        // La acción la controla LogisticaControlador.
    }
}
