import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Registro extends JFrame {
    private ArrayList<Usuario> listaUsuarios;
    private int contadorId;
    private String rutaImagenSeleccionada = "";

    private Usuario usuarioAEditar = null;

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
    private JButton btnFoto;
    private JLabel lblFotoPreview; // Ojo: cambiamos el nombre para la vista previa
    private JButton btnGuardar;

    public Registro(ArrayList<Usuario> listaUsuarios, int contadorId) {
        this.listaUsuarios = listaUsuarios;
        this.contadorId = contadorId;
        this.usuarioAEditar = null;
        iniciarVentana();
    }
    public Registro(ArrayList<Usuario> listaUsuarios, Usuario usuarioAEditar) {
        this.listaUsuarios = listaUsuarios;
        this.usuarioAEditar = usuarioAEditar;
        this.contadorId = usuarioAEditar.getId();
        iniciarVentana();
        cargarDatosParaEditar();
    }

    private void iniciarVentana() {
        setContentPane(panelPrincipal);
        setTitle(usuarioAEditar == null ? "BookUsers - Nuevo Registro" : "BookUsers - Editar Usuario");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        llenarComboBox();
        configurarEventos();
        configurarUsernameAutomatico();
    }
    private void llenarComboBox() {
        cmbTipoId.addItem("11 - Registro civil");
        cmbTipoId.addItem("12 - Tarjeta de identidad");
        cmbTipoId.addItem("13 - Cédula de ciudadanía");
        cmbTipoId.addItem("21 - Tarjeta de extranjería");
        cmbTipoId.addItem("22 - Cédula de extranjería");
        cmbTipoId.addItem("41 - Pasaporte");
    }

    private void configurarEventos() {
        btnFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

                int resultado = fileChooser.showOpenDialog(null);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    rutaImagenSeleccionada = archivoSeleccionado.getAbsolutePath();

                    ImageIcon iconoOriginal = new ImageIcon(rutaImagenSeleccionada);
                    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    lblFotoPreview.setIcon(new ImageIcon(imagenEscalada));
                    lblFotoPreview.setText("");
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });
    }

    private void cargarDatosParaEditar() {
        cmbTipoId.setSelectedItem(usuarioAEditar.getTipoIdentificacion());
        txtNumeroId.setText(usuarioAEditar.getNumeroIdentificacion());
        txtPrimerNombre.setText(usuarioAEditar.getPrimerNombre());
        txtSegundoNombre.setText(usuarioAEditar.getSegundoNombre());
        txtPrimerApellido.setText(usuarioAEditar.getPrimerApellido());
        txtSegundoApellido.setText(usuarioAEditar.getSegundoApellido());
        txtTelefono.setText(usuarioAEditar.getTelefono());
        txtCorreo.setText(usuarioAEditar.getCorreo());
        lblUsername.setText(usuarioAEditar.getUsername());

        rutaImagenSeleccionada = usuarioAEditar.getRutaFoto();
        if (rutaImagenSeleccionada != null && !rutaImagenSeleccionada.isEmpty()) {
            ImageIcon icono = new ImageIcon(rutaImagenSeleccionada);
            lblFotoPreview.setIcon(new ImageIcon(icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        }
    }

    private void guardarUsuario() {
        if (txtNumeroId.getText().isEmpty() || txtPrimerNombre.getText().isEmpty() ||
                txtPrimerApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() || txtPassword.getPassword().length == 0) {

            JOptionPane.showMessageDialog(this, "Llene todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtCorreo.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "El correo debe contener un '@'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoId = (String) cmbTipoId.getSelectedItem();
        String numId = txtNumeroId.getText();
        String primerNom = txtPrimerNombre.getText();
        String segundoNom = txtSegundoNombre.getText();
        String primerApe = txtPrimerApellido.getText();
        String segundoApe = txtSegundoApellido.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String password = new String(txtPassword.getPassword());
        String username = lblUsername.getText(); // Ya lo generamos automáticamente

        if (usuarioAEditar == null) {
            Usuario nuevoUsuario = new Usuario(contadorId, tipoId, numId, primerNom, segundoNom,
                    primerApe, segundoApe, username, password,
                    telefono, correo, rutaImagenSeleccionada);
            listaUsuarios.add(nuevoUsuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
        } else {
            usuarioAEditar.setTipoIdentificacion(tipoId);
            usuarioAEditar.setNumeroIdentificacion(numId);
            usuarioAEditar.setPrimerNombre(primerNom);
            usuarioAEditar.setSegundoNombre(segundoNom);
            usuarioAEditar.setPrimerApellido(primerApe);
            usuarioAEditar.setSegundoApellido(segundoApe);
            usuarioAEditar.setUsername(username);
            usuarioAEditar.setPassword(password);
            usuarioAEditar.setTelefono(telefono);
            usuarioAEditar.setCorreo(correo);
            usuarioAEditar.setRutaFoto(rutaImagenSeleccionada);

            JOptionPane.showMessageDialog(this, "Usuario actualizado con éxito.");
        }

        this.dispose();
    }

    private void configurarUsernameAutomatico() {
        DocumentListener listener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarUsername(); }
            public void removeUpdate(DocumentEvent e) { actualizarUsername(); }
            public void changedUpdate(DocumentEvent e) { actualizarUsername(); }
        };
        txtPrimerNombre.getDocument().addDocumentListener(listener);
        txtPrimerApellido.getDocument().addDocumentListener(listener);
        txtNumeroId.getDocument().addDocumentListener(listener);
    }

    private void actualizarUsername() {
        String nombre = txtPrimerNombre.getText();
        String apellido = txtPrimerApellido.getText();
        String id = txtNumeroId.getText();

        if (!nombre.isEmpty() && !apellido.isEmpty()) {
            String inicial = nombre.substring(0, 1).toLowerCase();
            lblUsername.setText(inicial + apellido.toLowerCase() + id);
        } else {
            lblUsername.setText("");
        }
    }
}