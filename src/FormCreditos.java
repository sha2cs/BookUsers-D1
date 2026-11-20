import javax.swing.*;

public class FormCreditos extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblIntegrante1;
    private JLabel lblIntegrante2;

    public FormCreditos() {
        setTitle("BookUsers · Créditos");
        setContentPane(panelPrincipal);
        Tema.aplicar(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
    }
}