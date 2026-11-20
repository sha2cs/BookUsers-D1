package Bookusers;

import javax.swing.*;
import java.util.ArrayList;

public class FormMenu {
    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnCreditos;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private int siguienteId = 1;

    public FormMenu() {
        btnRegistrar.addActionListener(e -> {
            FormRegistro formulario = new FormRegistro(usuarios, this);

            JFrame ventanaRegistro = new JFrame("BookUsers · Maireth Yalena Casadiego Vega · Registro");
            ventanaRegistro.setContentPane(formulario.getPanelPrincipal());
            ventanaRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaRegistro.pack();
            ventanaRegistro.setLocationRelativeTo(null);
            ventanaRegistro.setVisible(true);
        });
        btnConsultar.addActionListener(e -> {
            FormListado formulario = new FormListado(usuarios);


            JFrame ventanaListado = new JFrame("BookUsers · Maireth Yalena Casadiego Vega · Listado");
            ventanaListado.setContentPane(formulario.getPanelPrincipal());
            ventanaListado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaListado.pack();
            ventanaListado.setLocationRelativeTo(null);
            ventanaListado.setVisible(true);
        });
        btnCreditos.addActionListener(e -> {
            FormCreditos formulario = new FormCreditos();

            JFrame ventanaCreditos = new JFrame("BookUsers · Maireth Yalena Casadiego Vega · Créditos");
            ventanaCreditos.setContentPane(formulario.getPanelPrincipal());
            ventanaCreditos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaCreditos.pack();
            ventanaCreditos.setLocationRelativeTo(null);
            ventanaCreditos.setVisible(true);
        });
    }

    public int obtenerSiguienteId() {
        return siguienteId++;
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("BookUsers · Maireth Yalena Casadiego Vega");
        ventana.setContentPane(new FormMenu().panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}

