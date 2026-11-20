import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Image;

public class ListaS {
    private JPanel panelLista;
    private JList<String> listaUsuariosJList;
    private JLabel detalleLabel;
    private JLabel fotoDetalleLabel;

    public ListaS() {
        // Conecta el JList con el modelo de Datos
        listaUsuariosJList.setModel(Datos.modelo);

        listaUsuariosJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = listaUsuariosJList.getSelectedIndex();
                    if (index >= 0 && index < Datos.listaUsuarios.size()) {
                        Usuario u = Datos.listaUsuarios.get(index);
                        detalleLabel.setText("<html>ID: " + u.getId() + "<br>Nombre: " + u.getPrimerNombre() + " " + u.getSegundoNombre() + " " + u.getPrimerApellido() + " " + u.getSegundoApellido() +
                                "<br>Tipo: " + u.getTipoIdentificacion() + " - " + u.getNumeroIdentificacion() +
                                "<br>Usuario: " + u.getUsername() +
                                "<br>Tel: " + u.getTelefono() + "<br>Correo: " + u.getCorreo() + "</html>");

                        if (u.getRutaFoto() != null && !u.getRutaFoto().isEmpty()) {
                            ImageIcon icono = new ImageIcon(u.getRutaFoto());
                            Image img = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                            fotoDetalleLabel.setIcon(new ImageIcon(img));
                        } else {
                            fotoDetalleLabel.setIcon(null);
                            fotoDetalleLabel.setText("Sin foto");
                        }
                    }
                }
            }
        });
    }
    public ListaS(JDialog dialog) {}
    public JPanel getPanel() { return panelLista; }
}
