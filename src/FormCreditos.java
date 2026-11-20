import javax.swing.*;

public class FormCreditos extends JFrame {
    private JPanel panelPrincipal;
    private JLabel Titulo;
    private JLabel lblIntegrante1;
    private JLabel lblCodigoEstudiantil1;
    private JLabel lblCorreo1;
    private JLabel lblIntegrante2;
    private JLabel lblCodigoEstudiantil2;
    private JLabel lblCorreo2;
    private JLabel TItulo2;
    private JLabel Titulo3;

    public FormCreditos() {
        setContentPane(panelPrincipal);

        // Titulo de la ventana formCreditos
        setTitle("BookUsers · Creditos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
    }
}

