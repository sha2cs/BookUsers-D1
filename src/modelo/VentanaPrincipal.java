package modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Esta clase crea la ventana principal del programa BookUsers.
public class VentanaPrincipal extends JFrame {

    // Elementos visuales que forman parte de la ventana principal.
    private JPanel PanelPrincipal;
    private JButton creditosDeCreadorButton;
    private JButton verUsuariosButton;
    private JButton crearUsuariosButton;

    // Creamos un gestor compartido para manejar los usuarios.
    // Esto permite que las diferentes ventanas trabajen
    // con los mismos usuarios registrados.
    private GestorUsuarios gestorPrincipal = new GestorUsuarios();

    // Constructor de la ventana principal.
    public VentanaPrincipal() {

        // Configuramos el título que aparecerá en la ventana.
        setTitle("Sistema de Gestión de Usuarios - UNIPAZ");

        // Creamos el fondo utilizando una imagen.
        PanelConFondo fondo =
                new PanelConFondo("/imagenes/Imagen1.jpg");

        // Organizamos los elementos del fondo.
        fondo.setLayout(new BorderLayout());

        // Hacemos transparente el panel principal
        // para que se pueda visualizar la imagen de fondo.
        PanelPrincipal.setOpaque(false);

        // Agregamos el panel principal al centro de la ventana.
        fondo.add(PanelPrincipal, BorderLayout.CENTER);

        // Establecemos el fondo como contenido de la ventana.
        setContentPane(fondo);

        // Configuramos el comportamiento y tamaño de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Configuramos el botón para consultar los usuarios.
        verUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creamos la ventana de consulta
                // y le enviamos el gestor de usuarios.
                VentanaVerUsuarios ventanaVer =
                        new VentanaVerUsuarios(gestorPrincipal);

                // Mostramos la ventana.
                ventanaVer.setVisible(true);
            }
        });

        // Configuramos el botón para registrar nuevos usuarios.
        crearUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Creamos la ventana de registro
                // utilizando el mismo gestor de usuarios.
                VentanaCrearUsuario ventanaCrear =
                        new VentanaCrearUsuario(gestorPrincipal);

                // Mostramos el formulario.
                ventanaCrear.setVisible(true);
            }
        });

        // Configuramos el botón para mostrar los créditos del proyecto.
        creditosDeCreadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Llamamos al método que muestra los créditos.
                mostrarCreditos();
            }
        });
    }

    // Este método crea una ventana con la información
    // de los integrantes y los datos del proyecto.
    private void mostrarCreditos() {

        // Creamos una ventana de diálogo.
        // El valor true indica que debe cerrarse
        // antes de volver a utilizar la ventana principal.
        JDialog dialogo = new JDialog(
                this,
                "BookUsers - Créditos",
                true
        );

        // Configuramos el tamaño y la posición de la ventana.
        dialogo.setSize(520, 590);
        dialogo.setLocationRelativeTo(this);
        dialogo.setResizable(false);
        dialogo.setDefaultCloseOperation(
                JDialog.DISPOSE_ON_CLOSE
        );

        // Creamos el panel que contendrá todos los elementos
        // de la sección de créditos.
        JPanel principal = new JPanel();

        // Organizamos los componentes uno debajo del otro.
        principal.setLayout(new BoxLayout(
                principal,
                BoxLayout.Y_AXIS
        ));

        // Establecemos espacios internos para que los textos
        // no queden pegados a los bordes.
        principal.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 25, 25, 25
                )
        );

        // Creamos el texto que muestra el nombre y la versión del programa.
        JLabel titulo = new JLabel("BookUsers v1.0.1.1");

        titulo.setFont(new Font(
                "Arial",
                Font.BOLD,
                20
        ));

        // Centramos el título.
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creamos el encabezado de la sección de integrantes.
        JLabel integrantes = new JLabel("Integrantes:");

        integrantes.setFont(new Font(
                "Arial",
                Font.BOLD,
                17
        ));

        // Centramos el encabezado.
        integrantes.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dejamos reservado un espacio para agregar posteriormente
        // una imagen decorativa.

        // Creamos una línea de separación visual.
        JLabel separador1 = new JLabel("-");

        separador1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creamos las etiquetas con la información
        // del primer integrante del proyecto.
        JLabel nombreMateo = new JLabel(
                "Nombre: Mateo Mahecha Gutiérrez"
        );

        JLabel documentoMateo = new JLabel(
                "Documento: 1097187362"
        );

        JLabel codigoMateo = new JLabel(
                "Código Estudiantil: UNP-3SUP-4RYA"
        );

        JLabel correoMateo = new JLabel(
                "Correo: Mateomahechagutierrez@gmail.com"
        );

        // Creamos otra línea para dividir la información
        // de los dos integrantes.
        JLabel separador2 = new JLabel("-");

        separador2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creamos las etiquetas con la información
        // del segundo integrante del proyecto.
        JLabel nombreManuel = new JLabel(
                "Nombre: Manuel José Londoño Vergara"
        );

        JLabel documentoManuel = new JLabel(
                "Documento: 1101203526"
        );

        JLabel codigoManuel = new JLabel(
                "Código Estudiantil: UNP-ZKYP-9NCW"
        );

        JLabel correoManuel = new JLabel(
                "Correo: manueljoselondonovergara@gamil.com"
        );

        // Mostramos la materia relacionada con el proyecto
        // y el año en que fue desarrollado.
        JLabel materia = new JLabel(
                "Materia: POO - Año: 2026"
        );

        // Creamos una última línea de separación.
        JLabel separador3 = new JLabel("-");

        separador3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creamos el botón que permite cerrar la ventana de créditos.
        JButton cerrar = new JButton("Cerrar");

        // Cuando se presiona, se cierra la ventana de diálogo.
        cerrar.addActionListener(e -> dialogo.dispose());

        cerrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Definimos una fuente que se utilizará
        // para mostrar los datos de los integrantes.
        Font fuenteDatos = new Font(
                "Arial",
                Font.BOLD,
                14
        );

        // Guardamos las etiquetas de los integrantes
        // dentro de un arreglo para configurarlas juntas.
        JLabel[] textos = {
                nombreMateo,
                documentoMateo,
                codigoMateo,
                correoMateo,
                nombreManuel,
                documentoManuel,
                codigoManuel,
                correoManuel
        };

        // Recorremos todas las etiquetas del arreglo.
        for (JLabel texto : textos) {

            // Aplicamos el mismo tipo de letra
            // y centramos cada etiqueta.
            texto.setFont(fuenteDatos);
            texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // Configuramos también el texto de la materia.
        materia.setFont(fuenteDatos);
        materia.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Aplicamos el mismo tamaño de letra a los separadores.
        separador1.setFont(fuenteDatos);
        separador2.setFont(fuenteDatos);
        separador3.setFont(fuenteDatos);

        // Agregamos el título al panel principal.
        principal.add(titulo);

        // Agregamos un espacio debajo del título.
        principal.add(Box.createVerticalStrut(20));

        // Agregamos el encabezado de integrantes.
        principal.add(integrantes);

        principal.add(Box.createVerticalStrut(15));

        // Aquí irá posteriormente la imagen.

        // Agregamos el primer separador.
        principal.add(separador1);

        principal.add(Box.createVerticalStrut(12));

        // Agregamos los datos del primer integrante.
        principal.add(nombreMateo);
        principal.add(documentoMateo);
        principal.add(codigoMateo);
        principal.add(correoMateo);

        principal.add(Box.createVerticalStrut(20));

        // Agregamos el segundo separador.
        principal.add(separador2);

        principal.add(Box.createVerticalStrut(12));

        // Agregamos los datos del segundo integrante.
        principal.add(nombreManuel);
        principal.add(documentoManuel);
        principal.add(codigoManuel);
        principal.add(correoManuel);

        principal.add(Box.createVerticalStrut(22));

        // Agregamos la información de la materia y el año.
        principal.add(materia);

        principal.add(Box.createVerticalStrut(15));

        // Agregamos el último separador.
        principal.add(separador3);

        principal.add(Box.createVerticalStrut(20));

        // Agregamos el botón para cerrar la ventana.
        principal.add(cerrar);

        // Establecemos el panel principal como contenido
        // de la ventana de créditos.
        dialogo.setContentPane(principal);

        // Mostramos la ventana.
        dialogo.setVisible(true);
    }

    // Este es el punto de inicio del programa.
    // Java ejecuta este método cuando se inicia la aplicación.
    public static void main(String[] args) {

        // SwingUtilities.invokeLater permite iniciar
        // la interfaz gráfica en el hilo correspondiente de Swing.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                // Creamos la ventana principal
                // y hacemos que sea visible.
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}