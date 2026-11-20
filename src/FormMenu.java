import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormMenu extends JFrame {
    private JPanel panelPrincipal;
    private JLabel titulo;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;
    private JLabel version;

    // Creacion de el ArrayList
    private ArrayList<Usuario> listaUsuarios;

    private int contadorId;

    // Constructor de la ventana FormMenu
    public FormMenu() {
        // Inicialización de las estructuras globales
        this.listaUsuarios = new ArrayList<>();
        this.contadorId = 1;

        setContentPane(panelPrincipal);

        // Nombres de el grupo en la ventana del menu
        setTitle("BookUsers · Keiner Miranda y Juan Aguilar");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 300);
        setLocationRelativeTo(null);

        // Acción botón crear usuarios
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormRegistro ventanaRegistro = new FormRegistro(listaUsuarios, FormMenu.this);
                ventanaRegistro.setVisible(true);

            }
        });

        // Acción botón ver listado
        btnListado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormListado ventanaListado= new FormListado(listaUsuarios);
                ventanaListado.setVisible(true);
            }
        });

        // Acción botón créditos
        btnCreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCreditos ventanaCreditos = new FormCreditos();
                ventanaCreditos.setVisible(true);
            }
        });
    }

    // Metodo para el aumento de el id global
    public int obtenerYProgresarId() {

        return this.contadorId++;
    }

    // Metodo main para iniciar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormMenu menu = new FormMenu();
                menu.setVisible(true);
            }
        });
    }
}







