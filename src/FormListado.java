import javax.swing.*;
import java.util.ArrayList;

public class FormListado extends JFrame {
    private JPanel panelPrincipal;
    private JList<Usuario> lstUsuarios;
    private JButton btnVerDetalle;

    private ArrayList<Usuario> usuarios;
    private DefaultListModel<Usuario> modelo;

    public FormListado(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        setTitle("BookUsers · Listado");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        modelo = new DefaultListModel<>();
        lstUsuarios.setModel(modelo);
        lstUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cargarLista();
        activarVerDetalle();
    }

    private void cargarLista() {
        modelo.clear();
        for (Usuario u : usuarios) {
            modelo.addElement(u);
        }
    }

    private void activarVerDetalle() {
        btnVerDetalle.addActionListener(e -> {
            int indice = lstUsuarios.getSelectedIndex();
            if (indice == -1) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona un usuario de la lista.",
                        "BookUsers · Listado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Usuario seleccionado = modelo.getElementAt(indice);
            FormDetalle detalle = new FormDetalle(seleccionado);
            detalle.setVisible(true);
        });
    }
}
