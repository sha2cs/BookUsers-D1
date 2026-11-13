package Bookusers;

import javax.swing.*;

public class FormMenu {
    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnCreditos;

    public FormMenu() {

    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("BookUsers");
        ventana.setContentPane(new FormMenu().panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}

