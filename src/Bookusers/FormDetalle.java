package Bookusers;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.FileOutputStream;

import javax.swing.*;

public class FormDetalle {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblFoto;
    private JLabel lblTipoIdentificacion;
    private JLabel lblValorTipoIdentificacion;
    private JLabel lblNumeroIdentificacion;
    private JLabel lblValorNumeroIdentificacion;
    private JLabel lblPrimerNombre;
    private JLabel lblValorPrimerNombre;
    private JLabel lblSegundoNombre;
    private JLabel lblValorSegundoNombre;
    private JLabel lblPrimerApellido;
    private JLabel lblValorPrimerApellido;
    private JLabel lblSegundoApellido;
    private JLabel lblValorSegundoApellido;
    private JLabel lblUsername;
    private JLabel lblValorUsername;
    private JLabel lblTelefono;
    private JLabel lblValorTelefono;
    private JLabel lblCorreo;
    private JButton btnGenerarPDF;
    private JLabel lblValorCorreo;

    private Usuario usuario;
    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;

        lblValorTipoIdentificacion.setText(usuario.getTipoIdentificacion());
        lblValorNumeroIdentificacion.setText(usuario.getNumeroIdentificacion());
        lblValorPrimerNombre.setText(usuario.getPrimerNombre());
        lblValorSegundoNombre.setText(usuario.getSegundoNombre());
        lblValorPrimerApellido.setText(usuario.getPrimerApellido());
        lblValorSegundoApellido.setText(usuario.getSegundoApellido());
        lblValorUsername.setText(usuario.getUsername());
        lblValorTelefono.setText(usuario.getTelefono());
        lblValorCorreo.setText(usuario.getCorreo());

        if (usuario.getRutaFoto() != null && !usuario.getRutaFoto().isEmpty()) {
            ImageIcon imagen = new ImageIcon(usuario.getRutaFoto());

            ImageIcon imagenEscalada = new ImageIcon(
                    imagen.getImage().getScaledInstance(
                            120, 120, java.awt.Image.SCALE_SMOOTH
                    )
            );

            lblFoto.setIcon(imagenEscalada);
        }
        btnGenerarPDF.addActionListener(e -> generarPDF());

    }
    private void generarPDF() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar archivo PDF");
        selector.setSelectedFile(new java.io.File("Usuario_" + usuario.getId() + ".pdf"));

        int resultado = selector.showSaveDialog(panelPrincipal);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        java.io.File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new java.io.File(archivo.getAbsolutePath() + ".pdf");
        }

        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(archivo));

            documento.open();

            documento.add(new Paragraph("DETALLE DEL USUARIO"));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("ID: " + usuario.getId()));
            documento.add(new Paragraph("Tipo de identificación: " + usuario.getTipoIdentificacion()));
            documento.add(new Paragraph("Número de identificación: " + usuario.getNumeroIdentificacion()));
            documento.add(new Paragraph("Primer nombre: " + usuario.getPrimerNombre()));
            documento.add(new Paragraph("Segundo nombre: " + usuario.getSegundoNombre()));
            documento.add(new Paragraph("Primer apellido: " + usuario.getPrimerApellido()));
            documento.add(new Paragraph("Segundo apellido: " + usuario.getSegundoApellido()));
            documento.add(new Paragraph("Username: " + usuario.getUsername()));
            documento.add(new Paragraph("Teléfono: " + usuario.getTelefono()));
            documento.add(new Paragraph("Correo: " + usuario.getCorreo()));
            if (usuario.getRutaFoto() != null && !usuario.getRutaFoto().isEmpty()) {
                Image foto = Image.getInstance(usuario.getRutaFoto());
                foto.scaleToFit(150, 150);
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph("Fotografía:"));
                documento.add(foto);
            }

            documento.close();

            JOptionPane.showMessageDialog(panelPrincipal,
                    "PDF generado correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelPrincipal,
                    "Error al generar el PDF: " + ex.getMessage());
        }
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
