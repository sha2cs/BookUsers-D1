package Bookusers;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.Normalizer;
import java.io.File;

public class FormRegistro {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblTipodeidentificacion;
    private JComboBox cmbTipoId;
    private JTextField txtNumeroIdentificacion;
    private JLabel lblPrimerNombre;
    private JTextField txtPrimerNombre;
    private JLabel lblSegundoNombre;
    private JTextField txtSegundoNombre;
    private JLabel lblPrimerApellido;
    private JTextField txtPrimerApellido;
    private JLabel lblSegundoApellido;
    private JTextField txtSegundoApellido;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JLabel lblFoto;
    private JButton btnSeleccionarfoto;
    private JLabel lblVistaPreviaFoto;
    private JButton btnGuardar;

    private ArrayList<Usuario> usuarios;
    private String rutaFoto = "";
    private FormMenu menu;

    public FormRegistro(ArrayList<Usuario> usuarios, FormMenu menu) {
        this.usuarios = usuarios;
        this.menu = menu;
        agregarListenersUsername();
        btnSeleccionarfoto.addActionListener(e -> seleccionarFoto());
        btnGuardar.addActionListener(e -> guardarUsuario());
    }
    private String normalizar(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{M}", "");
        texto = texto.replace("ñ", "n").replace("Ñ", "N");
        texto = texto.replaceAll("[^a-zA-Z]", "");
        return texto.toLowerCase();
    }
     private void actualizarUsername() {
        String nombre = normalizar(txtPrimerNombre.getText().trim());
        String apellido1 = normalizar(txtPrimerApellido.getText().trim());
        String apellido2 = normalizar(txtSegundoApellido.getText().trim());

        String username = "";

        if (!nombre.isEmpty() && !apellido1.isEmpty()) {
            username = apellido1.substring(0, 1);

            if (!apellido2.isEmpty()) {
                username += apellido2.substring(0, 1);
            }

            username += nombre;
        }

        txtUsername.setText(username);
    }
    private void agregarListenersUsername() {
        DocumentListener listener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarUsername();
            }
        };

        txtPrimerNombre.getDocument().addDocumentListener(listener);
        txtPrimerApellido.getDocument().addDocumentListener(listener);
        txtSegundoApellido.getDocument().addDocumentListener(listener);
    }

    private void seleccionarFoto() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes JPG y PNG", "jpg", "jpeg", "png"
        ));

        int resultado = selector.showOpenDialog(panelPrincipal);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            rutaFoto = archivo.getAbsolutePath();


            ImageIcon imagen = new ImageIcon(rutaFoto);

            ImageIcon imagenEscalada = new ImageIcon(
                    imagen.getImage().getScaledInstance(
                            120, 120, java.awt.Image.SCALE_SMOOTH
                    )
            );

            lblVistaPreviaFoto.setIcon(imagenEscalada);

        }
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    private void guardarUsuario() {

        String numeroId = txtNumeroIdentificacion.getText().trim();
        String primerNombre = txtPrimerNombre.getText().trim();
        String primerApellido = txtPrimerApellido.getText().trim();
        String password = new String(txtPassword.getPassword());
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (numeroId.isEmpty() || primerNombre.isEmpty()
                || primerApellido.isEmpty() || password.isEmpty()
                || telefono.isEmpty() || correo.isEmpty()) {

            JOptionPane.showMessageDialog(panelPrincipal,
                    "Por favor, completa todos los campos obligatorios.");

            return;
        }
        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(panelPrincipal,
                    "El correo electrónico no es válido.");
            return;
        }
        String tipoId = cmbTipoId.getSelectedItem().toString();
        String segundoNombre = txtSegundoNombre.getText().trim();
        String segundoApellido = txtSegundoApellido.getText().trim();
        String username = txtUsername.getText().trim();

        int id = menu.obtenerSiguienteId();

        Usuario nuevoUsuario = new Usuario(
                id,
                tipoId,
                numeroId,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                username,
                password,
                telefono,
                correo,
                rutaFoto
        );

        usuarios.add(nuevoUsuario);

        JOptionPane.showMessageDialog(panelPrincipal,
                "Usuario registrado correctamente.");

        txtNumeroIdentificacion.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtUsername.setText("");
        lblVistaPreviaFoto.setIcon(null);
        rutaFoto = "";
        cmbTipoId.setSelectedIndex(0);

        actualizarUsername();

    }

}



