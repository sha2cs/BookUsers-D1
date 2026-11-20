package gui;

import ui.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormMenu {

    // ATRIBUTOS

     // ARRAYLIST

    private ArrayList<User> users = new ArrayList<>();

      // FRAME

    private final JFrame frameOne;

    // COMPONENTES

    private JButton buttonR;
    private JButton buttonL;
    private JButton buttonC;

     // PANEL

    private JPanel panelOne;

    // CONSTRUCTOR FRAME

    public FormMenu() {

        frameOne = new JFrame();

        // ACTION LISTENERS

        buttonR.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FormRegistro oneV  = new FormRegistro(users, FormMenu.this);

                oneV.setUpFrameTwo();

            }

        });

        buttonL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FormListado twoV = new FormListado(users);

                twoV.usuarioNuevo();
                twoV.setUpFrameThree();

            }

        });

        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FormCreditos threeV = new FormCreditos();

                threeV.setUpFrameFour();

            }

        });

    }

    // METODOS

    public void setUpFrameOne() {

        frameOne.setSize(850, 550);
        frameOne.setTitle("BookUsers · Yusof Lagos · Julio Cesar");
        frameOne.setContentPane(panelOne);
        frameOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOne.setLocationRelativeTo(null);
        frameOne.setVisible(true);

    }

    // ID

    private int IDC = 1;

    public int generateId (){

        return IDC++;

    }

    // EVENTOS

    public static void main(String[] args) {

        FormMenu V = new FormMenu();
        V.setUpFrameOne();

    }

}





