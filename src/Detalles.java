import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import com.sha2cs.generatepdf.DocumentoPDF;

public class Detalles extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JLabel lblNombreCompleto;
    private JLabel lblTipoId;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblUsername;
    private JButton btnGenerarPDF;
    private JButton btnCerrar;

    private Usuario usuario;

    public Detalles(Usuario usuario) {
        this.usuario = usuario;

        setContentPane(panelPrincipal);
        setTitle("BookUsers - Detalle de Usuario");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarDatos();
        configurarEventos();
    }

    private void cargarDatos() {
        String nombreCompleto = usuario.getPrimerNombre();
        if (usuario.getSegundoNombre() != null && !usuario.getSegundoNombre().isEmpty()) {
            nombreCompleto += " " + usuario.getSegundoNombre();
        }
        nombreCompleto += " " + usuario.getPrimerApellido();
        if (usuario.getSegundoApellido() != null && !usuario.getSegundoApellido().isEmpty()) {
            nombreCompleto += " " + usuario.getSegundoApellido();
        }

        lblNombreCompleto.setText(nombreCompleto);
        lblTipoId.setText(usuario.getTipoIdentificacion() + ": " + usuario.getNumeroIdentificacion());
        lblTelefono.setText("Teléfono: " + usuario.getTelefono());
        lblCorreo.setText("Correo: " + usuario.getCorreo());
        lblUsername.setText("Username: " + usuario.getUsername());

        String ruta = usuario.getRutaFoto();
        if (ruta != null && !ruta.isEmpty()) {
            File archivoImagen = new File(ruta);
            if (archivoImagen.exists()) {
                ImageIcon iconoOriginal = new ImageIcon(ruta);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(imagenEscalada));
                lblFoto.setText("");
            } else {
                lblFoto.setText("Imagen no encontrada");
            }
        } else {
            lblFoto.setText("Sin foto");
        }
    }

    private void configurarEventos() {
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnGenerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarPDF();
            }
        });
    }

    private void generarPDF() {
        try {
            DocumentoPDF documento = new DocumentoPDF("Ficha de usuario - BookUsers");

            if (usuario.getRutaFoto() != null && new File(usuario.getRutaFoto()).isFile()) {
                documento.imagen(usuario.getRutaFoto());
            }

            documento.campo("Id", usuario.getId());
            documento.campo("Tipo de identificación", usuario.getTipoIdentificacion());
            documento.campo("Número de identificación", usuario.getNumeroIdentificacion());
            documento.campo("Nombre completo", lblNombreCompleto.getText());
            documento.campo("Username", usuario.getUsername());
            documento.campo("Teléfono", usuario.getTelefono());
            documento.campo("Correo", usuario.getCorreo());

            File pdf = documento.guardar("usuario-" + usuario.getId() + ".pdf");

            JOptionPane.showMessageDialog(this, "PDF generado con éxito en:\n" + pdf.getAbsolutePath());
            Desktop.getDesktop().open(pdf);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el PDF: " + e.getMessage());
        }
    }
}