import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class BookUsers {
    private JPanel panelBookUsers;
    private JTextField primernombre;
    private JTextField Correo;
    private JButton Guardar;
    private JTextField Numeroid;
    private JComboBox <String>Tipoid;
    private JTextField segundonombre;
    private JTextField primerapellido;
    private JTextField segundoapellido;
    private JButton foto;
    private JLabel fotoLabel;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JTextField telefonoField;

    private String rutaFoto = "";

    public BookUsers() {

        foto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("Imágenes (.png, .jpg)", "png", "jpg", "jpeg"));
                chooser.setAcceptAllFileFilterUsed(false);

                int resultado = chooser.showOpenDialog(panelBookUsers);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivo = chooser.getSelectedFile();
                    rutaFoto = archivo.getAbsolutePath();

                    ImageIcon icono = new ImageIcon(rutaFoto);
                    Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    fotoLabel.setIcon(new ImageIcon(img));
                    fotoLabel.setText("");
                }
            }
        });
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = primernombre.getText().trim();
                String Snombre = segundonombre.getText().trim();
                String apellido = primerapellido.getText().trim();
                String Sapellido = segundoapellido.getText().trim();
                String correo = Correo.getText().trim();
                String Tipo = (String) Tipoid.getSelectedItem();
                String Numero = Numeroid.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String telefono = telefonoField.getText().trim();


                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()
                        || Numero.isEmpty() || password.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Falta nombre, primer apellido, correo, número, contraseña o teléfono");
                    return;
                }


                if (!correo.contains("@")) {
                    JOptionPane.showMessageDialog(null, "El correo debe contener @");
                    return;
                }


                if (!Tipo.equals("Pasaporte") && !Tipo.equals("Documento extranjero") && !Numero.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "El número de identificación debe ser solo dígitos");
                    return;
                }


                if (!telefono.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe ser solo dígitos");
                    return;
                }


                String username = (nombre + "." + apellido).toLowerCase().replace(" ", "");
                if (Numero.length() >= 3) {
                    username = username + Numero.substring(Numero.length() - 3);
                } else {
                    username = username + Numero;
                }
                usernameLabel.setText(username);


                Datos.agregarUsuario(Tipo, Numero, nombre, Snombre, apellido, Sapellido,
                        username, password, telefono, correo, rutaFoto);

                JOptionPane.showMessageDialog(null, "Guardado: " + nombre + " - Usuario: " + username);

                primernombre.setText("");
                segundonombre.setText("");
                primerapellido.setText("");
                segundoapellido.setText("");
                Correo.setText("");
                Numeroid.setText("");
                passwordField.setText("");
                telefonoField.setText("");
                Tipoid.setSelectedIndex(0);
                usernameLabel.setText("");
                fotoLabel.setText("");
                fotoLabel.setIcon(null);
                rutaFoto = "";
            }
        });
    }

    public BookUsers(JDialog bookUsers) {
    }

    public JPanel getPanel() {
        return panelBookUsers;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }
}