package Clases;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class CrearUsuario extends JFrame{
    private JPanel panelPrincipal;
    private JComboBox<String> cmbTipoDoc;
    private JTextField txtNumDoc;
    private JTextField txtNombre1;
    private JTextField txtNombre2;
    private JTextField txtApellido1;
    private JTextField txtApellido2;
    private JPasswordField txtPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnSubir;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    private String rutaFoto = "";


    public CrearUsuario(){
        setContentPane(panelPrincipal);
        setTitle("Registro de Usuarios - BookUsers-D1");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cmbTipoDoc.addItem("Cédula de Ciudadanía (CC)");
        cmbTipoDoc.addItem("Tarjeta de Identidad (TI)");
        cmbTipoDoc.addItem("Registro Civil (RC)");
        cmbTipoDoc.addItem("Cédula de Extranjería (CE)");
        cmbTipoDoc.addItem("Pasaporte (PA)");
        cmbTipoDoc.addItem("Permiso por Protección Temporal (PPT)");

        // 2. Botón Subir (Filtro solo PNG, JPG, JPEG Y GIF)
        btnSubir.addActionListener(e -> {
            JFileChooser selector = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (PNG, JPG, JPEG, GIF)", "png", "jpg", "jpeg, GIF");
            selector.setFileFilter(filtro);
            selector.setAcceptAllFileFilterUsed(false);

            if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivo = selector.getSelectedFile();
                rutaFoto = archivo.getAbsolutePath();
                btnSubir.setText(archivo.getName()); // Cambia el texto del botón al nombre de la foto
            }
        });

        // 3. Botón Registrar (Guardar en el ArrayList)
        btnRegistrar.addActionListener(e -> {
            if (txtNumDoc.getText().isEmpty() || txtNombre1.getText().isEmpty() || txtApellido1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa al menos Documento, Primer Nombre y Primer Apellido.");
                return;
            }

            // Validar que el número de documento no esté repetido
            String numDocIngresado = txtNumDoc.getText().trim();
            for (Usuario u : Usuario.listaUsuarios) {
                if (u.getNumeroIdentificacion().equalsIgnoreCase(numDocIngresado)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ya existe un usuario con el número de documento: " + numDocIngresado,
                            "Documento duplicado",
                            JOptionPane.WARNING_MESSAGE
                    );
                    txtNumDoc.requestFocus();
                    return;
                }
            }

            Usuario nuevo = new Usuario(
                    cmbTipoDoc.getSelectedItem().toString(),
                    txtNumDoc.getText(),
                    txtNombre1.getText(),
                    txtNombre2.getText(),
                    txtApellido1.getText(),
                    txtApellido2.getText(),
                    txtPassword.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText(),
                    rutaFoto
            );

            Usuario.listaUsuarios.add(nuevo);

            JOptionPane.showMessageDialog(this,
                    "¡Usuario registrado!\nID: " + nuevo.getId() + "\nUsername: " + nuevo.getUsername());

            limpiarCampos();
        });

        // 4. Botón Limpiar
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos() {
        txtNumDoc.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        btnSubir.setText("Subir");
        rutaFoto = "";
    }
}
