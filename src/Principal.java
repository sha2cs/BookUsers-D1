import javax.swing.*;


public class Principal {
    private JButton regristrarteButton;
    private JButton verInformacionButton;
    private JButton creditosButton;
    private JPanel panelPrincipal;
    private JFrame framePrincipal;

    public Principal() {
        creditosButton.addActionListener(e -> {
            Creditos v = new Creditos();
            JFrame f = new JFrame("Créditos");
            f.setContentPane(v.getPanel()); f.pack();
            f.setLocationRelativeTo(framePrincipal); f.setAlwaysOnTop(true);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); f.setVisible(true); f.toFront();
        });
        verInformacionButton.addActionListener(e -> {
            ListaS v = new ListaS();
            JFrame f = new JFrame("Lista");
            f.setContentPane(v.getPanel()); f.pack();
            f.setLocationRelativeTo(framePrincipal); f.setAlwaysOnTop(true);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); f.setVisible(true); f.toFront();
        });
        regristrarteButton.addActionListener(e -> {
            BookUsers v = new BookUsers();
            JFrame f = new JFrame("Registrarte");
            f.setContentPane(v.getPanel()); f.pack();
            f.setLocationRelativeTo(framePrincipal); f.setAlwaysOnTop(true);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); f.setVisible(true); f.toFront();
        });
    }
    public JPanel getPanel() { return panelPrincipal; }
    public void setFramePrincipal(JFrame frame) { this.framePrincipal = frame; }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        Principal p = new Principal();
        p.setFramePrincipal(frame);
        frame.setContentPane(p.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); frame.setLocationRelativeTo(null); frame.setVisible(true);
    }
}


