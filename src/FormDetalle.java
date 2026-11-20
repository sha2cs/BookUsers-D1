import javax.swing.*;
import com.sha2cs.generatepdf.DocumentoPDF;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FormDetalle extends JFrame{
    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JLabel lbtTipoId;
    private JLabel lblNumeroId;
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblUsername;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JButton btnGenerarPdf;
    private JLabel lblID;
    private Usuario usuario;

    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;
        setContentPane(panelPrincipal);

        // Titulo de la ventana ver detalle
        setTitle("BookUsers · Detalle de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null);

        cargarDatosEnPantalla();
        btnGenerarPdf.addActionListener(e -> generarPdf());
    }

    // Metodo para mostrar la informacion de el usuario en la ventana ver detalles
    private void cargarDatosEnPantalla() {
        lblID.setText("ID: " + usuario.getId());
        lbtTipoId.setText("Tipo ID: " + usuario.getTipoIdentificacion());
        lblNumeroId.setText("Numero identificacion: " + usuario.getNumeroIdentificacion());
        lblNombres.setText("Nombres: " + usuario.getPrimerNombre() + " " + usuario.getSegundoNombre());
        lblApellidos.setText("Apellidos: " + usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
        lblUsername.setText("Nombre de usuario: " + usuario.getUsername());
        lblTelefono.setText("Telefono: " + usuario.getTelefono());
        lblCorreo.setText("Correo: " + usuario.getCorreo());

        // Cargar y escalar la foto
        if (usuario.getRutaFoto() != null && new File(usuario.getRutaFoto()).isFile()) {
            ImageIcon iconoOriginal = new ImageIcon(usuario.getRutaFoto());
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(imagenEscalada));
            lblFoto.setText("");
        } else {
            lblFoto.setText("(Sin foto)");
        }
    }
    // Metodo para crear y abrir el pdf
    private void generarPdf() {
        try {
            DocumentoPDF documento = new DocumentoPDF("Ficha de Usuario");
            if (usuario.getRutaFoto() != null && new File(usuario.getRutaFoto()).isFile()) {
                documento.imagen(usuario.getRutaFoto());
            }
            // Toda la informacion de el usuario en el pdf excepto la contraseña
            documento.campo("ID", usuario.getId());
            documento.campo("Tipo de identificacion", usuario.getTipoIdentificacion());
            documento.campo("Numero identificacion", usuario.getNumeroIdentificacion());
            documento.campo("Primer nombre", usuario.getPrimerNombre());
            documento.campo("Segundo nombre", usuario.getSegundoNombre());
            documento.campo("Primer apellido", usuario.getPrimerApellido());
            documento.campo("Segundo apellido", usuario.getSegundoApellido());
            documento.campo("Nombre de usuario", usuario.getUsername());
            documento.campo("Telefono", usuario.getTelefono());
            documento.campo("Correo", usuario.getCorreo());

            File pdf = documento.guardar("usuario-" + usuario.getId() + ".pdf");
            JOptionPane.showMessageDialog(this, "PDF generado correctamente en:\n"
                    + pdf.getAbsolutePath());Desktop.getDesktop().open(pdf);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: "
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}

