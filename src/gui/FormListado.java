package gui;

import ui.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

public class FormListado {

    // ATRIBUTOS

    private ArrayList<User> users;
    private DefaultListModel<User> modelo;

    // FRAME

    private JFrame frameThree;
    private JPanel panelThree;
    private JList<User> usuarioL;

    private JButton detallesButton;

    // CONSTRUCTOR FRAME

    public FormListado (ArrayList<User> users) {

        this.users = users;
        frameThree = new JFrame();

        modelo = new DefaultListModel<>();
        usuarioL.setModel(modelo);

        detallesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (users.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Ingresar usuario primero");

                    return;

                }

                if (usuarioL.isSelectionEmpty()) {

                    JOptionPane.showMessageDialog(null, "Seleccionar usuario");

                } else if (!usuarioL.isSelectionEmpty()){

                    User seleccion = usuarioL.getSelectedValue();

                    FormDetalle fourV = new FormDetalle(seleccion);

                    fourV.setUpFrameFive();
                    fourV.usuarioInfo();

                }

            }

        });

    }

    // METODOS

    public void setUpFrameThree() {

        frameThree.setSize(500,600);
        frameThree.setTitle("FormListado");
        frameThree.setContentPane(panelThree);
        frameThree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameThree.setLocationRelativeTo(null);
        frameThree.setVisible(true);

    }

    // MODELO

    public void usuarioNuevo (){

        modelo.clear();

        for (User u : users) {

            modelo.addElement(u);

        }

    }

}
