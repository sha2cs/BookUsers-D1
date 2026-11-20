
package Bookusers;

import javax.swing.*;
import java.util.ArrayList;

public class FormListado {

    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JList lstUsuarios;
    private JButton btnVerDetalle;

    public FormListado(ArrayList<Usuario> usuarios) {

        DefaultListModel<Usuario> modelo = new DefaultListModel<>();

        for (Usuario usuario : usuarios) {
            modelo.addElement(usuario);
        }

        lstUsuarios.setModel(modelo);

        btnVerDetalle.addActionListener(e -> {

            if (lstUsuarios.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(panelPrincipal,
                        "Por favor, selecciona un usuario.");
                return;
            }

            Usuario usuarioSeleccionado =
                    (Usuario) lstUsuarios.getSelectedValue();

            FormDetalle formulario =
                    new FormDetalle(usuarioSeleccionado);


            JFrame ventana = new JFrame("BookUsers · Maireth Yalena Casadiego Vega · Detalle");
            ventana.setContentPane(formulario.getPanelPrincipal());
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}