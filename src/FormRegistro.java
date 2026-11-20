import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormRegistro extends JFrame{
    private JTextField txtNumeroId;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblUsername;
    private JLabel lblFoto;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbTipoId;
    private JTextField txtPrimerNombre;
    private JPanel panelPrincipal;
    private JButton btnGuardar;

    //arrayList de forMenu
    private ArrayList<Usuario> listaUsuarios;
    private FormMenu menuPrincipal;

    // Constructor de la ventana de Registro
    public FormRegistro(ArrayList<Usuario> listaUsuarios, FormMenu menuPrincipal) {
        this.listaUsuarios = listaUsuarios;
        this.menuPrincipal = menuPrincipal;
        setContentPane(panelPrincipal);

        //titulo de la ventana registro
        setTitle("BookUsers · Registrar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(450, 650);
        setLocationRelativeTo(menuPrincipal);

        // Escuchador en tiempo real para las cajas de texto
        javax.swing.event.DocumentListener escucharEscritura = new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                actualizarUsernameEnPantalla(); }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                actualizarUsernameEnPantalla(); }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                actualizarUsernameEnPantalla(); }
        };

        txtPrimerNombre.getDocument().addDocumentListener(escucharEscritura);
        txtPrimerApellido.getDocument().addDocumentListener(escucharEscritura);
        txtSegundoApellido.getDocument().addDocumentListener(escucharEscritura);

        // Acción botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura de los datos
                String tipoId = (String) cmbTipoId.getSelectedItem();
                String numeroId = txtNumeroId.getText().trim();
                String primerNombre = txtPrimerNombre.getText().trim();
                String segundoNombre = txtSegundoNombre.getText().trim();
                String primerApellido = txtPrimerApellido.getText().trim();
                String segundoApellido = txtSegundoApellido.getText().trim();
                String username = lblUsername.getText();
                String password = new String(txtPassword.getPassword());
                String telefono = txtTelefono.getText().trim();
                String correo = txtCorreo.getText().trim();

                // Se toma la ruta de la foto
                String rutaFoto = lblFoto.getToolTipText();
                if (rutaFoto == null) {
                    rutaFoto = "sin_foto.png";
                }

                if (numeroId.isEmpty() || primerNombre.isEmpty() || primerApellido.isEmpty() ||
                        password.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {

                    JOptionPane.showMessageDialog(FormRegistro.this,
                            "Todos los campos con (*) son obligatorios. Por favor verifique.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Confirmar que se aya puesto el arroba
                if (!correo.contains("@")) {
                    JOptionPane.showMessageDialog(FormRegistro.this,
                            "El correo electronico debe contener @",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener el id de formMenu
                int idAsignado = menuPrincipal.obtenerYProgresarId();

                // Creacion de el objeto usuario
                Usuario nuevoUsuario = new Usuario(
                        idAsignado, tipoId, numeroId, primerNombre, segundoNombre,
                        primerApellido, segundoApellido, username, password, telefono, correo, rutaFoto
                );
                listaUsuarios.add(nuevoUsuario);

                // Mensaje de confirmacion de guardado exitoso
                JOptionPane.showMessageDialog(FormRegistro.this,
                        "¡Usuario registrado con éxito!\nID Asignado: " + idAsignado + "\nUsername: "
                                + username, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                FormRegistro.this.dispose();
            }
        });

        // Acción botón Cargar Foto
        btnCargarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buscador de archivos
                JFileChooser buscador = new JFileChooser();

                // Filtrar para solo archivos .png y .jpg
                javax.swing.filechooser.FileNameExtensionFilter filtro =
                        new javax.swing.filechooser.FileNameExtensionFilter("Imagenes (PNG, JPG)",
                                "png", "jpg", "jpeg");buscador.setFileFilter(filtro);

                //ventana flotante del explorador
                int resultado = buscador.showOpenDialog(FormRegistro.this);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    String rutaImagen = buscador.getSelectedFile().getAbsolutePath();

                    //Escalar la imagen
                    ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
                    //se asigna un tamaño fijo
                    java.awt.Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);

                    // Colocar la foto en el JLabel
                    lblFoto.setIcon(new ImageIcon(imagenEscalada));
                    lblFoto.setToolTipText(rutaImagen);
                }
            }
        });
    }
    // Metodo para limpiar el texto
    private String limpiarTexto(String texto) {
        if (texto == null) return "";
        texto = texto.toLowerCase().trim();
        texto = texto.replace("ñ", "n");

        String textoNormalizado = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD);
        textoNormalizado = textoNormalizado.replaceAll("[^\\p{ASCII}]", "");

        return textoNormalizado.replaceAll("\\s+", "");
    }
    //Metodo para la creacion de el username
    private void actualizarUsernameEnPantalla() {
        String pNombre = limpiarTexto(txtPrimerNombre.getText());
        String pApellido = limpiarTexto(txtPrimerApellido.getText());
        String sApellido = limpiarTexto(txtSegundoApellido.getText());

        if (!pApellido.isEmpty() && !pNombre.isEmpty()) {
            String resultadoUsername = "";

            //Primera letra del primer apellido
            resultadoUsername += pApellido.substring(0, 1);

            //Primera letra del segundo apellido
            if (!sApellido.isEmpty()) {
                resultadoUsername += sApellido.substring(0, 1);
            }
            resultadoUsername += pNombre;
            lblUsername.setText(resultadoUsername);
        } else {
            lblUsername.setText("(Se genera automaticamente)");
        }
    }

    }



