package Clases;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class VerUsuario extends JFrame {
    private JPanel panelPrincipal;
    private JComboBox<String> cmbSeleccionarUsuario;
    private JList<String> lstUsuarios;
    private JLabel lblId;
    private JLabel lblTipoDoc;
    private JLabel lblNumDoc;
    private JLabel lblNombre1;
    private JLabel lblNombre2;
    private JLabel lblApellido1;
    private JLabel lblApellido2;
    private JLabel lblFoto;
    private JButton PDFButton;

    public VerUsuario() {
        setContentPane(panelPrincipal);
        setTitle("Ver Usuarios - BookUsers-D1");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cargarDatos();

        cmbSeleccionarUsuario.addActionListener(e -> {
            int idx = cmbSeleccionarUsuario.getSelectedIndex();
            if (idx >= 0) {
                lstUsuarios.setSelectedIndex(idx);
                mostrarDetalleUsuario(idx);
            }
        });

        lstUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int idx = lstUsuarios.getSelectedIndex();
                if (idx >= 0) {
                    cmbSeleccionarUsuario.setSelectedIndex(idx);
                    mostrarDetalleUsuario(idx);
                }
            }
        });

        PDFButton.addActionListener(e -> exportarFichaOpenPDF());
    }

    private void cargarDatos() {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        cmbSeleccionarUsuario.removeAllItems();

        for (Usuario u : Usuario.listaUsuarios) {
            String item = u.getIdFormateado() + " | " + u.getUsername() + " - " +
                    u.getPrimerNombre() + " " + u.getPrimerApellido();
            modeloLista.addElement(item);
            cmbSeleccionarUsuario.addItem(item);
        }

        lstUsuarios.setModel(modeloLista);

        if (!Usuario.listaUsuarios.isEmpty()) {
            cmbSeleccionarUsuario.setSelectedIndex(0);
            lstUsuarios.setSelectedIndex(0);
            mostrarDetalleUsuario(0);
        }
    }

    private void mostrarDetalleUsuario(int indice) {
        if (indice >= 0 && indice < Usuario.listaUsuarios.size()) {
            Usuario u = Usuario.listaUsuarios.get(indice);

            lblId.setText(u.getIdFormateado());
            lblTipoDoc.setText(u.getTipoIdentificacion());
            lblNumDoc.setText(u.getNumeroIdentificacion());
            lblNombre1.setText(u.getPrimerNombre());
            lblNombre2.setText(u.getSegundoNombre());
            lblApellido1.setText(u.getPrimerApellido());
            lblApellido2.setText(u.getSegundoApellido());

            if (u.getRutaFoto() != null && !u.getRutaFoto().isEmpty()) {
                ImageIcon iconoOriginal = new ImageIcon(u.getRutaFoto());
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(imagenEscalada));
                lblFoto.setText("");
            } else {
                lblFoto.setIcon(null);
                lblFoto.setText("Sin foto");
            }
        }
    }

    private void exportarFichaOpenPDF() {
        int indice = cmbSeleccionarUsuario.getSelectedIndex();
        if (indice < 0 || Usuario.listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ningún usuario seleccionado.");
            return;
        }

        Usuario u = Usuario.listaUsuarios.get(indice);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Ficha de Usuario en PDF");
        fileChooser.setSelectedFile(new File("Usuario_" + u.getIdFormateado() + "_" + u.getUsername() + ".pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
            if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                rutaArchivo += ".pdf";
            }

            Document documento = new Document();
            try {
                PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
                documento.open();

                com.lowagie.text.Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                documento.add(new Paragraph("FICHA DE REGISTRO - BOOKUSERS-D1", fuenteTitulo));
                documento.add(new Paragraph(" "));

                com.lowagie.text.Font fuenteTexto = FontFactory.getFont(FontFactory.HELVETICA, 12);
                documento.add(new Paragraph("ID Consecutivo: " + u.getIdFormateado(), fuenteTexto));
                documento.add(new Paragraph("Nombre de Usuario: " + u.getUsername(), fuenteTexto));
                documento.add(new Paragraph("Tipo de Documento: " + u.getTipoIdentificacion(), fuenteTexto));
                documento.add(new Paragraph("Número de Documento: " + u.getNumeroIdentificacion(), fuenteTexto));
                documento.add(new Paragraph("Primer Nombre: " + u.getPrimerNombre(), fuenteTexto));
                documento.add(new Paragraph("Segundo Nombre: " + u.getSegundoNombre(), fuenteTexto));
                documento.add(new Paragraph("Primer Apellido: " + u.getPrimerApellido(), fuenteTexto));
                documento.add(new Paragraph("Segundo Apellido: " + u.getSegundoApellido(), fuenteTexto));
                documento.add(new Paragraph("Teléfono: " + u.getTelefono(), fuenteTexto));
                documento.add(new Paragraph("Correo Electrónico: " + u.getCorreo(), fuenteTexto));
                documento.add(new Paragraph(" "));

                if (u.getRutaFoto() != null && !u.getRutaFoto().isEmpty()) {
                    documento.add(new Paragraph("Fotografía adjunta:", fuenteTexto));
                    documento.add(new Paragraph(" "));
                    com.lowagie.text.Image fotoPdf = com.lowagie.text.Image.getInstance(u.getRutaFoto());
                    fotoPdf.scaleToFit(150, 150);
                    documento.add(fotoPdf);
                }

                documento.close();
                JOptionPane.showMessageDialog(this, "PDF generado con éxito en:\n" + rutaArchivo);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear el PDF: " + ex.getMessage());
            }
        }
    }
}