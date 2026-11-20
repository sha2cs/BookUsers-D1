import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;

public class FormDetalle extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JTextArea txtInfo;
    private JButton btnGenerarPdf;

    private Usuario usuario;

    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;

        setTitle("BookUsers · Detalle");
        setContentPane(panelPrincipal);
        Tema.aplicar(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 550);
        setLocationRelativeTo(null);

        mostrarFoto();
        mostrarInfo();

        btnGenerarPdf.addActionListener(e -> generarPdf());
    }

    private void mostrarFoto() {
        String ruta = usuario.getRutaFoto();
        if (ruta == null || ruta.isEmpty() || !new File(ruta).exists()) {
            lblFoto.setText("Sin foto");
            return;
        }
        ImageIcon icono = new ImageIcon(ruta);
        Image escalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblFoto.setText("");
        lblFoto.setIcon(new ImageIcon(escalada));
    }

    private void mostrarInfo() {
        txtInfo.setEditable(false);
        txtInfo.setText(
                "ID: " + usuario.getId() + "\n"
                        + "Tipo de identificación: " + usuario.getTipoIdentificacion() + "\n"
                        + "Número de identificación: " + usuario.getNumeroIdentificacion() + "\n"
                        + "Primer nombre: " + usuario.getPrimerNombre() + "\n"
                        + "Segundo nombre: " + usuario.getSegundoNombre() + "\n"
                        + "Primer apellido: " + usuario.getPrimerApellido() + "\n"
                        + "Segundo apellido: " + usuario.getSegundoApellido() + "\n"
                        + "Username: " + usuario.getUsername() + "\n"
                        + "Teléfono: " + usuario.getTelefono() + "\n"
                        + "Correo: " + usuario.getCorreo()
        );
    }

    private void generarPdf() {
        JFileChooser selector = new JFileChooser();
        selector.setSelectedFile(new File("usuario_" + usuario.getUsername() + ".pdf"));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File destino = selector.getSelectedFile();

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(destino));
            documento.open();

            String ruta = usuario.getRutaFoto();
            if (ruta != null && !ruta.isEmpty() && new File(ruta).exists()) {
                com.lowagie.text.Image foto = com.lowagie.text.Image.getInstance(ruta);
                foto.scaleToFit(120, 120);
                documento.add(foto);
            }

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(txtInfo.getText()));

            JOptionPane.showMessageDialog(this, "PDF generado en:\n" + destino.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo generar el PDF: " + ex.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }
}