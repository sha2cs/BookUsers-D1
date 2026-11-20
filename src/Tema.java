import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class Tema {

    public static final Color FONDO = new Color(245, 235, 221);        // crema
    public static final Color CAFE_OSCURO = new Color(75, 46, 30);     // botones
    public static final Color CAFE_MEDIO = new Color(111, 78, 55);     // selección
    public static final Color LATTE = new Color(200, 162, 122);        // bordes
    public static final Color CAMPO = new Color(255, 253, 249);        // campos
    public static final Color TEXTO = new Color(59, 36, 23);

    public static final Font FUENTE = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_NEGRITA = new Font("Segoe UI", Font.BOLD, 14);

    public static void aplicar(JPanel raiz) {
        raiz.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        estilizarPanel(raiz);
    }

    private static void estilizarPanel(JPanel panel) {
        panel.setBackground(FONDO);
        panel.setOpaque(true);
        for (Component c : panel.getComponents()) {
            if (c instanceof JPanel p) {
                estilizarPanel(p);
            } else if (c instanceof JButton b) {
                estilizarBoton(b);
            } else if (c instanceof JLabel l) {
                l.setForeground(TEXTO);
                l.setFont(FUENTE_NEGRITA);
            } else if (c instanceof JTextComponent t) {
                estilizarCampo(t);
            } else if (c instanceof JComboBox<?> cb) {
                cb.setBackground(CAMPO);
                cb.setForeground(TEXTO);
                cb.setFont(FUENTE);
            } else if (c instanceof JList<?> lista) {
                lista.setBackground(CAMPO);
                lista.setForeground(TEXTO);
                lista.setFont(FUENTE);
                lista.setSelectionBackground(CAFE_MEDIO);
                lista.setSelectionForeground(Color.WHITE);
            }
        }
    }

    private static void estilizarBoton(JButton b) {
        b.setBackground(CAFE_OSCURO);
        b.setForeground(Color.WHITE);
        b.setFont(FUENTE_NEGRITA);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private static void estilizarCampo(JTextComponent t) {
        t.setBackground(CAMPO);
        t.setForeground(TEXTO);
        t.setCaretColor(CAFE_OSCURO);
        t.setFont(FUENTE);
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LATTE, 1),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)));
    }
}