package gui;

import javax.swing.*;

public class FormCreditos {

    // ATRIBUTOS

    // FRAME

    private JFrame frameFour;
    private JPanel panelFour;

    // CONSTRUCTOR FRAME

    public FormCreditos () {

        frameFour = new JFrame();

    }

    // METODOS

    public void setUpFrameFour() {

        frameFour.setSize(450,500);
        frameFour.setTitle("Creditos");
        frameFour.setContentPane(panelFour);
        frameFour.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFour.setLocationRelativeTo(null);
        frameFour.setVisible(true);

    }
}
