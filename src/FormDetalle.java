import javax.swing.*;
import java.awt.Image;
import java.io.File;

public class FormDetalle extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JTextArea txtDatos;
    private JButton btnGenerarPDF;

    private Usuario usuario;

    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;
        setTitle("BookUsers · Detalle");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);

        txtDatos.setEditable(false);
        mostrarFoto();
        txtDatos.setText(armarDatos());
    }

    private void mostrarFoto() {
        String ruta = usuario.getRutaFoto();
        if (ruta == null || ruta.isEmpty() || !new File(ruta).exists()) {
            lblFoto.setIcon(null);
            lblFoto.setText("Sin foto");
            return;
        }
        ImageIcon icono = new ImageIcon(ruta);
        Image escalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblFoto.setText("");
        lblFoto.setIcon(new ImageIcon(escalada));
    }

    public String armarDatos() {
        return "ID: " + usuario.getId() + "\n"
                + "Tipo de identificación: " + usuario.getTipoIdentificacion() + "\n"
                + "Número de identificación: " + usuario.getNumeroIdentificacion() + "\n"
                + "Primer nombre: " + usuario.getPrimerNombre() + "\n"
                + "Segundo nombre: " + valor(usuario.getSegundoNombre()) + "\n"
                + "Primer apellido: " + usuario.getPrimerApellido() + "\n"
                + "Segundo apellido: " + valor(usuario.getSegundoApellido()) + "\n"
                + "Username: " + usuario.getUsername() + "\n"
                + "Teléfono: " + usuario.getTelefono() + "\n"
                + "Correo: " + usuario.getCorreo();
    }

    private String valor(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "(no registrado)";
        }
        return texto;
    }
}
