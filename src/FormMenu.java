import javax.swing.*;
import java.util.ArrayList;

public class FormMenu extends JFrame {

    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public FormMenu() {
        setTitle("BookUsers · Daniel Torres y Fredy Quintero");
        setContentPane(panelPrincipal);
        Tema.aplicar(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        btnCreditos.addActionListener(e -> {
            FormCreditos creditos = new FormCreditos();
            creditos.setVisible(true);
        });

        btnRegistrar.addActionListener(e -> {
            FormRegistro registro = new FormRegistro(usuarios);
            registro.setVisible(true);
        });

        btnListado.addActionListener(e -> {
            FormListado listado = new FormListado(usuarios);
            listado.setVisible(true);
        });
    }

    public static void main(String[] args) {
        FormMenu menu = new FormMenu();
        menu.setVisible(true);
    }
}