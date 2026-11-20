package gui;

import ui.User;

import javax.swing.*;
import java.awt.*;
import java.net.UnknownServiceException;

public class FormDetalle {

    // ATRIBUTOS

    User userT;

    // COMPONENTES

    private JButton Descargar;

    // FRAME

    private JFrame frameFive;
    private JPanel panelFive;
    private JLabel TDI;
    private JLabel NDI;
    private JLabel PN;
    private JLabel SN;
    private JPanel jpanel;
    private JLabel SA;
    private JLabel ID;
    private JLabel TE;
    private JLabel CO;
    private JLabel FT;
    private JLabel PA;
    private JLabel US;

    // CONSTRUCTOR FRAME

    public FormDetalle (User userT) {

        this.userT = userT;

        frameFive = new JFrame();

    }

    // METODOS

    public void setUpFrameFive() {

        frameFive.setSize(800,600);
        frameFive.setTitle("Creditos");
        frameFive.setContentPane(panelFive);
        frameFive.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFive.setLocationRelativeTo(null);
        frameFive.setVisible(true);

    }

    public void usuarioInfo() {

        TDI.setText(" Tipo: " + userT.getTI());
        NDI.setText(" Numero identificacion: " + userT.getNI());
        PN.setText(" Primer nombre: " + userT.getPN());
        SN.setText(" Segundo nombres: " + userT.getSN());
        PA.setText(" Primer apellido: " + userT.getPA());
        SA.setText(" Segundo apellido: " + userT.getSA());

        ID.setText("ID: " + userT.getId());
        US.setText("Usuario: " +userT.getUsername());
        TE.setText("Telefono: " + userT.getTelefono());
        CO.setText("Correo: " + userT.getCorreo());

        ImageIcon imagenT = new ImageIcon(userT.getFotoUrl());

        Image imagenO = imagenT.getImage().getScaledInstance(FT.getWidth(), FT.getHeight(), Image.SCALE_SMOOTH);

        FT.setText("");
        FT.setIcon(new ImageIcon(imagenO));

    }

}

