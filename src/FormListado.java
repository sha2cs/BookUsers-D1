import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormListado extends JFrame {
    private JLabel Titulo;
    private JList<Usuario> lstUsuarios;
    private JButton btnVerDetalle;
    private JPanel panelPrincipal;
    private JButton btnEliminar;
    private ArrayList<Usuario> listaUsuarios;
    private DefaultListModel<Usuario> modeloLista;

    public FormListado(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        setContentPane(panelPrincipal);

        // Se pone el titulo de la ventana
        setTitle("BookUsers · Listado de Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);

        // Creamos el modelo de datos
        DefaultListModel<Usuario> modeloLista = new DefaultListModel<>();

        // Recorremos la lista e insertamos un usuario por renglon
        for (Usuario u : listaUsuarios) {
            modeloLista.addElement(u);
        }

        // Le pasamos el modelo a la JList visual para visualizar en la ventana
        lstUsuarios.setModel(modeloLista);

        // Accion boton ver detallez
        btnVerDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = lstUsuarios.getSelectedIndex();

                // Si no hay selección devuelve -1
                if (indiceSeleccionado == -1) {
                    JOptionPane.showMessageDialog(FormListado.this,
                            "Por favor seleccione un usuario de la lista para ver su informacion.",
                            "Atencion",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Si selecciono a alguien lo mostramos completo
                Usuario usuarioSeleccionado = listaUsuarios.get(indiceSeleccionado);

                FormDetalle ventanaDetalle = new FormDetalle(usuarioSeleccionado);
                ventanaDetalle.setVisible(true);

            }
        });

        // Accion de el boton de eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = lstUsuarios.getSelectedIndex();

                // Validar si se selecciono a alguien
                if (indice == -1) {
                    JOptionPane.showMessageDialog(FormListado.this,
                            "Seleccione un usuario de la lista para eliminar",
                            "Atencion", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Usuario seleccionado = listaUsuarios.get(indice);

                // Confirmacion de la eliminacion del usuario
                int confirmacion = JOptionPane.showConfirmDialog(FormListado.this,
                        "¿Esta seguro de eliminar al usuario " + seleccionado.getUsername() + "?",
                        "Confirmar eliminacion de usuario",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {

                    // Borra a el usuario de el arrayList general
                    listaUsuarios.remove(indice);

                    // Borra a el usuario de la ventana de listado
                    modeloLista.remove(indice);
                    JOptionPane.showMessageDialog(FormListado.this, "Usuario eliminado");
                }
            }
        });

    }
}
