import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

public class FormMenu extends JFrame {
    private JButton btnRegistro;
    private JButton btnListado;
    private JButton btnCreditos;
    private JPanel panelPrincipal;
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private static final String VERSION = "v1.0.0";
    public static final int ANCHO = 500;
    public static final int ALTO = 780;

    // el form es lo grafico, el java es el codigo

    public FormMenu(){
        setTitle("BookUsers · Alejandro Arenas y Ariana Albarrán");
        armarPantalla();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        btnCreditos.addActionListener(e -> {
            FormCreditos creditos = new FormCreditos();
            creditos.setVisible(true);
        });

        btnRegistro.addActionListener(e -> {
            FormRegistro registro = new FormRegistro(usuarios);
            registro.setVisible(true);
        });

        btnListado.addActionListener(e -> {
            FormListado listado = new FormListado(usuarios);
            listado.setVisible(true);
        });
    }

    private void armarPantalla() {
        JLabel lblTitulo = new JLabel("BOOKUSERS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel lblPie = new JLabel("© 2026 Alejandro Arenas y Ariana Albarrán · " + VERSION,
                SwingConstants.CENTER);
        lblPie.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblPie.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(lblTitulo, BorderLayout.NORTH);
        contenedor.add(panelPrincipal, BorderLayout.CENTER);
        contenedor.add(lblPie, BorderLayout.SOUTH);
        setContentPane(contenedor);

        btnRegistro.setText("Registrar usuario");
        btnListado.setText("Ver listado");
        btnCreditos.setText("Créditos");
    }

    public static void main(String[] args){
        FormMenu menu = new FormMenu();
        menu.setVisible(true);
    }
}


