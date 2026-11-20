import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;

public class FormRegistro extends JFrame {

    private JPanel panelPrincipal;
    private JComboBox<String> cmbTipoId;
    private JTextField txtNumeroId;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblFoto;
    private JButton btnGuardar;

    private ArrayList<Usuario> usuarios;
    private Usuario usuarioEditando;
    private String rutaFoto = "";

    // Modo registro: usuario nuevo
    public FormRegistro(ArrayList<Usuario> usuarios) {
        this(usuarios, null);
    }

    // Modo actualizar: usuario existente (si es null, es un registro nuevo)
    public FormRegistro(ArrayList<Usuario> usuarios, Usuario usuarioEditando) {
        this.usuarios = usuarios;
        this.usuarioEditando = usuarioEditando;

        setTitle(usuarioEditando == null
                ? "BookUsers · Registro"
                : "BookUsers · Actualizar usuario");
        setContentPane(panelPrincipal);
        Tema.aplicar(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 650);
        setLocationRelativeTo(null);

        llenarTiposId();

        DocumentListener escucha = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarUsername(); }
            public void removeUpdate(DocumentEvent e) { actualizarUsername(); }
            public void changedUpdate(DocumentEvent e) { actualizarUsername(); }
        };
        txtPrimerNombre.getDocument().addDocumentListener(escucha);
        txtPrimerApellido.getDocument().addDocumentListener(escucha);
        txtSegundoApellido.getDocument().addDocumentListener(escucha);

        btnCargarFoto.addActionListener(e -> cargarFoto());
        btnGuardar.addActionListener(e -> guardar());

        if (usuarioEditando != null) {
            cargarDatos();
        }
    }

    private void llenarTiposId() {
        cmbTipoId.addItem("11 - Registro civil de nacimiento");
        cmbTipoId.addItem("12 - Tarjeta de identidad");
        cmbTipoId.addItem("13 - Cédula de ciudadanía");
        cmbTipoId.addItem("21 - Tarjeta de extranjería");
        cmbTipoId.addItem("22 - Cédula de extranjería");
        cmbTipoId.addItem("41 - Pasaporte");
        cmbTipoId.addItem("42 - Documento de identificación extranjero");
        cmbTipoId.addItem("47 - Permiso Especial de Permanencia (PEP)");
        cmbTipoId.addItem("91 - NUIP");
    }

    private void cargarDatos() {
        Usuario u = usuarioEditando;
        cmbTipoId.setSelectedItem(u.getTipoIdentificacion());
        txtNumeroId.setText(u.getNumeroIdentificacion());
        txtPrimerNombre.setText(u.getPrimerNombre());
        txtSegundoNombre.setText(u.getSegundoNombre());
        txtPrimerApellido.setText(u.getPrimerApellido());
        txtSegundoApellido.setText(u.getSegundoApellido());
        txtPassword.setText(u.getPassword());
        txtTelefono.setText(u.getTelefono());
        txtCorreo.setText(u.getCorreo());
        rutaFoto = u.getRutaFoto();
        mostrarFotoGuardada();
        btnGuardar.setText("Guardar cambios");
    }

    private void mostrarFotoGuardada() {
        if (rutaFoto == null || rutaFoto.isEmpty() || !new File(rutaFoto).exists()) {
            return;
        }
        ImageIcon icono = new ImageIcon(rutaFoto);
        Image escalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblFoto.setText("");
        lblFoto.setIcon(new ImageIcon(escalada));
    }

    private String limpiar(String texto) {
        String n = Normalizer.normalize(texto, Normalizer.Form.NFD);
        n = n.replaceAll("\\p{M}", "");
        return n.toLowerCase().replace(" ", "");
    }

    private String primeraLetra(String texto) {
        String t = limpiar(texto);
        return t.isEmpty() ? "" : t.substring(0, 1);
    }

    private void actualizarUsername() {
        String u = primeraLetra(txtPrimerApellido.getText())
                + primeraLetra(txtSegundoApellido.getText())
                + limpiar(txtPrimerNombre.getText());
        lblUsername.setText(u.isEmpty() ? "-" : u);
    }

    private void cargarFoto() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter(
                "Imágenes (.png, .jpg)", "png", "jpg", "jpeg"));
        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
            if (icono.getIconWidth() <= 0) {
                JOptionPane.showMessageDialog(this, "No se pudo leer la imagen.");
                return;
            }
            Image escalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblFoto.setText("");
            lblFoto.setIcon(new ImageIcon(escalada));
            rutaFoto = archivo.getAbsolutePath();
        }
    }

    private void guardar() {
        String tipo = (String) cmbTipoId.getSelectedItem();
        String numero = txtNumeroId.getText().trim();
        String primerNombre = txtPrimerNombre.getText().trim();
        String segundoNombre = txtSegundoNombre.getText().trim();
        String primerApellido = txtPrimerApellido.getText().trim();
        String segundoApellido = txtSegundoApellido.getText().trim();
        String password = new String(txtPassword.getPassword());
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (numero.isEmpty() || primerNombre.isEmpty() || primerApellido.isEmpty()
                || password.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.");
            return;
        }

        boolean permiteLetras = tipo.startsWith("41") || tipo.startsWith("42");
        if (!permiteLetras && !numero.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "El número de identificación solo debe tener dígitos.");
            return;
        }

        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(this, "El correo debe contener @.");
            return;
        }

        if (usuarioEditando == null) {
            Usuario u = new Usuario(Usuario.siguienteId(), tipo, numero,
                    primerNombre, segundoNombre, primerApellido, segundoApellido,
                    lblUsername.getText(), password, telefono, correo, rutaFoto);
            usuarios.add(u);
            JOptionPane.showMessageDialog(this, "Usuario guardado: " + u.getUsername());
            limpiarFormulario();
        } else {
            // Se modifica el mismo objeto: el id no cambia
            usuarioEditando.setTipoIdentificacion(tipo);
            usuarioEditando.setNumeroIdentificacion(numero);
            usuarioEditando.setPrimerNombre(primerNombre);
            usuarioEditando.setSegundoNombre(segundoNombre);
            usuarioEditando.setPrimerApellido(primerApellido);
            usuarioEditando.setSegundoApellido(segundoApellido);
            usuarioEditando.setUsername(lblUsername.getText());
            usuarioEditando.setPassword(password);
            usuarioEditando.setTelefono(telefono);
            usuarioEditando.setCorreo(correo);
            usuarioEditando.setRutaFoto(rutaFoto);
            JOptionPane.showMessageDialog(this,
                    "Usuario actualizado: " + usuarioEditando.getUsername());
            dispose();
        }
    }

    private void limpiarFormulario() {
        cmbTipoId.setSelectedIndex(0);
        txtNumeroId.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        lblFoto.setIcon(null);
        lblFoto.setText("Sin foto");
        rutaFoto = "";
    }
}