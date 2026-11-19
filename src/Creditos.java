import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Creditos extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblIntegrante1;
    private JLabel lblIntegrante2;
    private JButton btnCerrar;

    public Creditos() {
        setContentPane(panelPrincipal);
        setTitle("BookUsers - Créditos");
        setSize(570, 300);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblIntegrante1.setText("Integrante 2: Andrea Restrepo");
        lblIntegrante2.setText("Integrante 1: Drake Meza");

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

