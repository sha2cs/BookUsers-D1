import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class FormListado extends JFrame {

    private JPanel panelPrincipal;
    private JList<Usuario> lstUsuarios;
    private JButton btnVerDetalle;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private ArrayList<Usuario> usuarios;
    private DefaultListModel<Usuario> modelo = new DefaultListModel<>();

    public FormListado(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;

        setTitle("BookUsers · Listado");
        setContentPane(panelPrincipal);
        Tema.aplicar(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        for (Usuario u : usuarios) {
            modelo.addElement(u);
        }
        lstUsuarios.setModel(modelo);

        btnVerDetalle.addActionListener(e -> verDetalle());
        btnEliminar.addActionListener(e -> eliminar());
        btnActualizar.addActionListener(e -> actualizar());
    }

    private void verDetalle() {
        int indice = lstUsuarios.getSelectedIndex();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la lista.");
            return;
        }
        Usuario seleccionado = modelo.getElementAt(indice);
        FormDetalle detalle = new FormDetalle(seleccionado);
        detalle.setVisible(true);
    }

    private void eliminar() {
        int indice = lstUsuarios.getSelectedIndex();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la lista.");
            return;
        }
        Usuario seleccionado = modelo.getElementAt(indice);
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Eliminar a " + seleccionado.getUsername() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            usuarios.remove(seleccionado);
            modelo.remove(indice);
        }
    }

    private void actualizar() {
        int indice = lstUsuarios.getSelectedIndex();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la lista.");
            return;
        }
        Usuario seleccionado = modelo.getElementAt(indice);
        FormRegistro edicion = new FormRegistro(usuarios, seleccionado);
        edicion.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                lstUsuarios.repaint();
            }
        });
        edicion.setVisible(true);
    }
}