import javax.swing.*;

public class FormCreditos extends JFrame {
    private JPanel panelPrincipal;
    private JTextArea txtCreditos;

    public FormCreditos() {
        setTitle("BookUsers · Créditos");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);

        txtCreditos.setEditable(false);
        txtCreditos.setText(
                "Alejandro Arenas Bustamante\n"
                        + "Código: UNP-KC44-W6HD\n"
                        + "Correo: alejandro.arenabus@unipaz.edu.co\n\n"
                        + "Ariana Sharloth Albarran Robles\n"
                        + "Código: UNP-K5NH-3AZW\n"
                        + "Correo: ariana.albarranrob@unipaz.edu.co");
    }
}