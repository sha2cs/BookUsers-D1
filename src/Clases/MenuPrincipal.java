package Clases;

import javax.swing.*;

public class MenuPrincipal extends JFrame {
    private JPanel panelPrincipal;
    private JButton btnCrear;
    private JButton btnVer;
    private JButton btnCreditos;

    public MenuPrincipal() {
        setContentPane(panelPrincipal);
        setTitle("BookUsers-D1 - Menú Principal");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnCrear.addActionListener(e -> {
            new CrearUsuario().setVisible(true);
        });

        btnVer.addActionListener(e -> {
            new VerUsuario().setVisible(true);
        });

        btnCreditos.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Desarrollado por: Suarez Jostin y Juan Pacheco\nProyecto: BookUsers-D1",
                    "Créditos",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}