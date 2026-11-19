import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormMenu {

    private JPanel PanelPrincipal;
    private JButton btnregistrar;
    private JButton btnconsultar;
    private JButton btncreditos;
    private JLabel version;
    private JPanel LugarParaBotones;


    private ArrayList<Usuario> listaUsuarios;
    private int contadorId = 1;


    public FormMenu() {
        listaUsuarios = new ArrayList<>();


        btnregistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Registro ventanaRegistro = new Registro(listaUsuarios, contadorId);
                ventanaRegistro.setVisible(true);
                contadorId++;
            }
        });
        btnconsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Listado ventanaListado = new Listado(listaUsuarios);
                ventanaListado.setVisible(true);
            }
        });
        btncreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Creditos ventanaCreditos = new Creditos();
                ventanaCreditos.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        JFrame ventana = new JFrame("BookUsers MezaDrake AndreaRestrepo");
        ventana.setContentPane(new FormMenu().PanelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

}