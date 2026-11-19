import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Listado extends JFrame {
    private JPanel panelPrincipal;
    private JList<Usuario> listaUsuariosView;
    private JButton btnVerDetalle;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private ArrayList<Usuario> listaUsuarios;
    private DefaultListModel<Usuario> modeloLista;

    public Listado(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

        setContentPane(panelPrincipal);
        setTitle("BookUsers - Listado de Usuarios");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloLista = new DefaultListModel<>();
        listaUsuariosView.setModel(modeloLista);
        actualizarLista();

        btnVerDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario seleccionado = listaUsuariosView.getSelectedValue();
                if (seleccionado != null) {
                    Detalles ventanaDetalle = new Detalles(seleccionado);
                    ventanaDetalle.setVisible(true);
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario seleccionado = listaUsuariosView.getSelectedValue();
                if (seleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario.");
                    return;
                }

                int resp = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de eliminar a " + seleccionado.getPrimerNombre() + "?",
                        "Confirmar", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    listaUsuarios.remove(seleccionado);
                    actualizarLista();
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario seleccionado = listaUsuariosView.getSelectedValue();
                if (seleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario para actualizar.");
                    return;
                }

                Registro ventanaEditar = new Registro(listaUsuarios, seleccionado);
                ventanaEditar.setVisible(true);

                ventanaEditar.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
                        actualizarLista();
                    }
                });
            }
        });
    }

    public void actualizarLista() {
        modeloLista.clear();
        for (Usuario u : listaUsuarios) {
            modeloLista.addElement(u);
        }
    }
}