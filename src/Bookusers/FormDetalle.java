package Bookusers;

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
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

}

