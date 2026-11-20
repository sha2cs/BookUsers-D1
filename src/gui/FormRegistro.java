package gui;

import ui.User;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FormRegistro {

    // ATRIBUTOS

    private String username;
    private String pAt;
    private String sAt;
    private String pNt;
    private String url;

    // FRAME

    private JFrame frameTwo;
    private FormMenu mV;
    private ArrayList<User> users;

    // COMPONENTES

    private JPanel panelTwo;

    private JComboBox comboTi;

    private JTextField txtIde;
    private JTextField txtPn;
    private JTextField txtSn;
    private JTextField txtPa;
    private JTextField txtSa;
    private JPasswordField passwordC;
    private JTextField txtCorreo;
    private JTextField txtTelefono;

    private JButton crearUsuarioButton;
    private JButton cargarFotoButton;
    private JLabel userL;
    private JLabel imageL;

    // CONSTRUCTOR FRAME

    public FormRegistro(ArrayList<User> users, FormMenu V) {

        this.users = users;
        frameTwo = new JFrame();
        mV = V;

        // ACTION LISTENERS

        crearUsuarioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // VARIABLES PARA RECIBIR

                String tIt = comboTi.getSelectedItem().toString();
                String nIt = txtIde.getText();
                pNt = txtPn.getText().trim();
                String sNt = txtSn.getText().trim();
                pAt = txtPa.getText().trim();
                sAt = txtSa.getText().trim();
                String contraseña = new String(passwordC.getPassword());
                String ct = txtCorreo.getText();
                String tt = txtTelefono.getText();
                int verificacion = 0;

                // REVISA NOMBRE Y APELLIDO

                if (pAt.matches("\\d+") || pNt.matches("\\d+") || sNt.matches("\\d+") || sAt.matches("\\d+")) {

                    JOptionPane.showMessageDialog(null, "Invalido numeros en nombres");

                } else if (pAt.isEmpty() && pNt.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Primer apellido y nombre son obligatorios");

                } else if (pAt.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "El primer apellido es obligatorio");

                } else if (pNt.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "El primer nombre es obligatorio");

                } else {

                    verificacion += 1;

                }

                // REVISA CORREO

                if (ct.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "El Correo es obligatorio");

                } else if (!ct.contains("@")) {

                    JOptionPane.showMessageDialog(null, "Correo sin arroba invalido");

                } else {

                    verificacion += 1;

                }

                // REVISA TELEFONO

                if (!tt.isEmpty() && !tt.matches("\\d+")) {

                    JOptionPane.showMessageDialog(null, "Telefono solo puede contener numeros");

                } else if (tt.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "El telefono es obligatorio");

                } else {

                    verificacion += 1;

                }

                // REVISA CONTRASEÑA

                if (contraseña.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "La contraseña es obligatoria");

                } else {

                    verificacion += 1;

                }

                // REVISA NUMERO DOCUMENTO

                if (tIt.contains("41 - Pasaporte") || (tIt.contains("42 - Documento de Identificacion Extranjero"))) {

                    if (nIt.isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Identificacion es obligatorio");

                    }

                } else if (!nIt.isEmpty() && !nIt.matches("\\d+")) {

                    JOptionPane.showMessageDialog(null, "Identificacion solo puede contener numeros");

                } else if (nIt.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Identificacion es obligatorio");

                } else {

                    verificacion += 1;

                }

                if (verificacion == 5) {

                    User usuarios = new User(mV.generateId(), tIt, nIt, pNt, sNt, pAt, sAt, username, contraseña, tt, ct, url);

                    JOptionPane.showMessageDialog(null, "Usuario creado");

                    users.add(usuarios);

                }

            }

        });

        // LISTENER REPETIDOR

        DocumentListener listener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                generarUsuario();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                generarUsuario();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                generarUsuario();

            }

        };

        txtPn.getDocument().addDocumentListener(listener);
        txtPa.getDocument().addDocumentListener(listener);
        txtSa.getDocument().addDocumentListener(listener);

        // CARGAR IMAGENES

        cargarFotoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser seleccionar = new JFileChooser();

                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes archivo", "png", "jpg");

                seleccionar.setFileFilter(filtro);

                int estado = seleccionar.showOpenDialog(null);

                if (estado == JFileChooser.APPROVE_OPTION) {

                    File archivo = seleccionar.getSelectedFile();
                    String verificacion = archivo.getName().toLowerCase();

                    if (verificacion.endsWith(".jpg") || (verificacion.endsWith(".png"))) {

                        url = archivo.getAbsolutePath();

                        ImageIcon imagen = new ImageIcon(url);

                        Image imagenO = imagen.getImage().getScaledInstance(imageL.getWidth(), imageL.getHeight(), Image.SCALE_SMOOTH);

                        imageL.setText("");
                        imageL.setIcon(new ImageIcon(imagenO));

                    } else {

                        JOptionPane.showMessageDialog(null, "Solo se permite JPG y PNG");

                    }

                } else if (estado == JFileChooser.CANCEL_OPTION) {

                    JOptionPane.showMessageDialog(null, "Seleccion cancelada");

                } else if (estado == JFileChooser.ERROR_OPTION) {

                    JOptionPane.showMessageDialog(null, "Error");

                }
            }

        });

    }

    // METODOS

     // SETUP DE LA VENTANA

    public void setUpFrameTwo () {

        frameTwo.setSize(700, 800);
        frameTwo.setTitle("Registro Usuarios");
        frameTwo.setContentPane(panelTwo);
        frameTwo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameTwo.setLocationRelativeTo(null);
        frameTwo.setVisible(true);

    }

     // GENERADOR USERNAME

    public void generarUsuario () {

        String apellidoN1 = "";
        String apellidoN2 = "";
        String nombreN1 = "";

        pAt = txtPa.getText().trim();
        sAt = txtSa.getText().trim();
        pNt = txtPn.getText().trim();

        if (!pNt.isEmpty()) {

            nombreN1 = pNt.toLowerCase(); ;

        }

        if (!pAt.isEmpty()) {

            apellidoN1 = pAt.toLowerCase().substring(0, 1);;

        }

        if (!sAt.isEmpty()) {

            apellidoN2 = sAt.toLowerCase().substring(0, 1);

        }

        username = apellidoN1 + apellidoN2 + nombreN1;

        username = username.replace("ñ","n");

        userL.setText(username);

    }

}