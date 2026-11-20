package modelo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VentanaCrearUsuario extends JFrame {



    // COMPONENTES DEL FORMULARIO

    // Estas variables representan los campos, botones y elementos
    // que aparecen en la ventana de registro de usuarios.

    private JPanel panelCrearUsuario;
    private JTextField textField2; // Campo para el número de documento.
    private JTextField textField3; // Campo para el primer nombre.
    private JTextField textField4; // Campo para el segundo nombre.
    private JTextField textField5; // Campo para la contraseña.
    private JTextField textField6; // Campo para el teléfono.
    private JTextField textField7; // Campo para el correo electrónico.
    private JTextField textField8; // Campo para el primer apellido.
    private JTextField textField9; // Campo para el segundo apellido.

    private JComboBox comboBox1;

    private JButton cancelarButton;
    private JButton guardarButton;
    private JButton seleccionarFotografiaButton;
    private JLabel labelFoto;

    // Objeto que permite administrar y guardar los usuarios.
    private GestorUsuarios gestor;

    // Guarda el usuario que se está editando.
    // Si su valor es null, significa que estamos registrando uno nuevo.
    private Usuarios usuarioEdicion;

    // Guarda la dirección de la fotografía seleccionada.
    private String rutaFoto = "";

    // CONSTRUCTOR PARA REGISTRAR USUARIOS
    // Recibe el gestor y prepara el formulario para crear un usuario.
    public VentanaCrearUsuario(GestorUsuarios gestor) {

        // Llamamos al otro constructor enviando null porque
        // todavía no existe un usuario que necesitemos editar.
        this(gestor, null);
    }

    // CONSTRUCTOR PRINCIPAL
    // Permite utilizar el mismo formulario para registrar o editar usuarios.
    public VentanaCrearUsuario(
            GestorUsuarios gestor,
            Usuarios usuario
    ) {

        // Guardamos el gestor y el usuario recibido.
        this.gestor = gestor;
        this.usuarioEdicion = usuario;

        // Cambiamos el título dependiendo de la operación.
        setTitle(
                usuario == null
                        ? "Crear Nuevo Usuario - UNIPAZ"
                        : "Editar Usuario - UNIPAZ"
        );

        // CONFIGURAR FONDO DE LA VENTANA

        // Comprobamos que el panel principal esté creado.
        if (panelCrearUsuario == null) {
            panelCrearUsuario = new JPanel();
        }

        // Creamos un panel que permite colocar una imagen de fondo.
        PanelConFondo fondo =
                new PanelConFondo("/imagenes/Imagen2.jpg");

        fondo.setLayout(new BorderLayout());

        // Hacemos transparente el panel para poder ver la imagen de fondo.
        panelCrearUsuario.setOpaque(false);

        // Colocamos el formulario en el centro del fondo.
        fondo.add(
                panelCrearUsuario,
                BorderLayout.CENTER
        );

        // Establecemos el fondo como contenido de la ventana.
        setContentPane(fondo);

        // CONFIGURACIÓN GENERAL DE LA VENTANA

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // TIPOS DE DOCUMENTO

        // Eliminamos los elementos anteriores del menú desplegable.
        comboBox1.removeAllItems();

        // Agregamos las opciones de documentos disponibles.
        comboBox1.addItem("Seleccione tipo de documento");
        comboBox1.addItem("Cédula de ciudadanía (CC)");
        comboBox1.addItem("Tarjeta de identidad (TI)");
        comboBox1.addItem("Cédula de extranjería (CE)");
        comboBox1.addItem("Pasaporte (PA)");
        comboBox1.addItem("Permiso por Protección Temporal (PPT)");
        comboBox1.addItem("Registro civil (RC)");


        // COMPROBAR SI ESTAMOS EDITANDO

        // Si recibimos un usuario, cargamos sus datos en el formulario.
        if (usuarioEdicion != null) {

            cargarDatosUsuario();

            // Cambiamos el texto del botón para indicar que se actualizarán datos.
            guardarButton.setText("Actualizar usuario");

        }


        // BOTÓN GUARDAR

        // Cuando se presiona, ejecutamos el método para guardar o actualizar.
        guardarButton.addActionListener(e -> guardarUsuario());

        // BOTÓN CANCELAR
        // Cierra la ventana sin realizar ninguna operación.
        cancelarButton.addActionListener(e -> dispose());

        // BOTÓN SELECCIONAR FOTOGRAFÍA
        // Abre el selector de archivos para elegir una imagen.
        seleccionarFotografiaButton.addActionListener(
                e -> seleccionarFotografia()
        );
    }

    // CARGAR DATOS DEL USUARIO PARA EDITAR
    // Coloca en los campos la información que ya tiene el usuario.
    private void cargarDatosUsuario() {

        // Cargamos el tipo de documento seleccionado.
        comboBox1.setSelectedItem(
                usuarioEdicion.getTipoDocumento()
        );

        // Colocamos los datos personales en sus respectivos campos.
        textField2.setText(
                usuarioEdicion.getDocumento()
        );

        textField3.setText(
                usuarioEdicion.getPrimerNombre()
        );

        textField4.setText(
                usuarioEdicion.getSegundoNombre()
        );

        textField5.setText(
                usuarioEdicion.getContrasena()
        );

        textField6.setText(
                usuarioEdicion.getTelefono()
        );

        textField7.setText(
                usuarioEdicion.getCorreo()
        );

        textField8.setText(
                usuarioEdicion.getPrimerApellido()
        );

        textField9.setText(
                usuarioEdicion.getSegundoApellido()
        );

        // MOSTRAR FOTOGRAFÍA ANTERIOR

        try {

            // Recuperamos la fotografía guardada del usuario.
            byte[] fotografia =
                    usuarioEdicion.getFotografia();

            // Comprobamos que exista una fotografía.
            if (fotografia != null && fotografia.length > 0) {

                // Convertimos los bytes en una imagen que Java pueda mostrar.
                ImageIcon imagenOriginal =
                        new ImageIcon(fotografia);

                // Mostramos la fotografía en el formulario.
                mostrarImagen(imagenOriginal);
            }

        } catch (Exception e) {

            // Mostramos una advertencia si no podemos cargar la fotografía.
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo cargar la fotografía anterior.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // GUARDAR O ACTUALIZAR USUARIO
    // Este método comprueba los datos y realiza la operación correspondiente.
    private void guardarUsuario() {

        // VALIDAR CAMPOS OBLIGATORIOS
        // Comprobamos que los campos necesarios no estén vacíos.
        if (comboBox1.getSelectedIndex() <= 0

                || textField2.getText().trim().isEmpty()
                || textField3.getText().trim().isEmpty()
                || textField5.getText().trim().isEmpty()
                || textField6.getText().trim().isEmpty()
                || textField7.getText().trim().isEmpty()
                || textField8.getText().trim().isEmpty()

                // Si estamos registrando un usuario nuevo,
                // también debemos comprobar que se haya seleccionado una fotografía.
                || (usuarioEdicion == null
                && rutaFoto.trim().isEmpty())) {

            // Mostramos una advertencia si faltan datos.
            mostrarAdvertenciaDatos();

            // Terminamos el método para evitar guardar información incompleta.
            return;
        }

        // VALIDAR CORREO ELECTRÓNICO
        // Comprobamos que el correo contenga el símbolo @.
        if (!textField7.getText().trim().contains("@")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El correo electrónico debe contener @.",
                    "Correo inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // CONFIRMAR OPERACIÓN
        // Preguntamos al usuario si realmente desea continuar.
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                usuarioEdicion == null
                        ? "¿Estás seguro de que deseas registrar este usuario?"
                        : "¿Estás seguro de que deseas actualizar este usuario?",
                "Confirmar operación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        // Si selecciona No, cancelamos la operación.
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            // COMPROBAR SI ES UN REGISTRO NUEVO
            if (usuarioEdicion == null) {

                // Creamos un nuevo objeto con los datos del formulario.
                Usuarios nuevoUsuario = getNuevoUsuario();

                // Enviamos el nuevo usuario al gestor para guardarlo.
                gestor.agregarUsuario(nuevoUsuario);

                // Mostramos el mensaje de registro exitoso.
                mostrarFelicidades();

            } else {

                // ACTUALIZAR USUARIO EXISTENTE
                // Enviamos los nuevos datos al usuario que estamos editando.
                usuarioEdicion.actualizarDatos(

                        comboBox1.getSelectedItem().toString(),

                        textField2.getText().trim(),

                        textField3.getText().trim(),

                        textField4.getText().trim(),

                        textField8.getText().trim(),

                        textField9.getText().trim(),

                        textField5.getText(),

                        textField6.getText().trim(),

                        textField7.getText().trim(),

                        rutaFoto
                );

                // Guardamos los cambios realizados en el archivo.
                gestor.actualizarUsuario(usuarioEdicion);

                // Informamos que la actualización fue exitosa.
                JOptionPane.showMessageDialog(
                        this,
                        "Los datos del usuario se actualizaron correctamente.",
                        "Actualización exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            // Cerramos el formulario después de completar la operación.
            dispose();

        } catch (Exception ex) {

            // Mostramos un mensaje si ocurre algún problema al guardar.
            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar el usuario: "
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            // Mostramos información del error en la consola.
            ex.printStackTrace();
        }
    }

    // SELECCIONAR FOTOGRAFÍA


    // Permite buscar una imagen en los archivos del computador.
    private void seleccionarFotografia() {

        // Creamos una ventana para seleccionar archivos.
        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle(
                "Seleccionar fotografía del usuario"
        );

        // Creamos un filtro para permitir solamente imágenes JPG, JPEG y PNG.
        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter(
                        "Imágenes (JPG, JPEG, PNG)",
                        "jpg", "jpeg", "png"
                );

        // Aplicamos el filtro al selector de archivos.
        selector.setFileFilter(filtro);

        // Abrimos la ventana para seleccionar una fotografía.
        int resultado = selector.showOpenDialog(this);

        // Comprobamos si el usuario seleccionó un archivo.
        if (resultado == JFileChooser.APPROVE_OPTION) {

            // Obtenemos el archivo seleccionado.
            File archivo = selector.getSelectedFile();

            // Guardamos la dirección de la fotografía.
            rutaFoto = archivo.getAbsolutePath();

            // Convertimos el archivo seleccionado en una imagen.
            ImageIcon imagenOriginal =
                    new ImageIcon(rutaFoto);

            // Mostramos la fotografía en el formulario.
            mostrarImagen(imagenOriginal);

            // Informamos que la selección fue exitosa.
            JOptionPane.showMessageDialog(
                    this,
                    "Fotografía seleccionada correctamente.",
                    "Fotografía seleccionada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // MOSTRAR IMAGEN REDIMENSIONADA

    // Ajusta el tamaño de la fotografía para que encaje en el espacio disponible.
    private void mostrarImagen(ImageIcon imagenOriginal) {

        // Obtenemos el ancho y el alto del espacio donde se muestra la fotografía.
        int ancho = labelFoto.getWidth();
        int alto = labelFoto.getHeight();

        // Si todavía no tenemos un tamaño válido, utilizamos 150 por 150.
        if (ancho <= 0 || alto <= 0) {
            ancho = 150;
            alto = 150;
        }

        // Comprobamos que la imagen se haya cargado correctamente.
        if (imagenOriginal.getIconWidth() <= 0
                || imagenOriginal.getIconHeight() <= 0) {
            return;
        }

        // Calculamos la proporción necesaria para ajustar la imagen
        // sin deformarla.
        double escala = Math.min(
                (double) ancho / imagenOriginal.getIconWidth(),
                (double) alto / imagenOriginal.getIconHeight()
        );

        // Calculamos el nuevo ancho de la imagen.
        Math.max(
                1,
                (int) (imagenOriginal.getIconWidth() * escala)
        );

        // Calculamos el nuevo ancho y alto para la imagen.
        int nuevoAncho = Math.max(
                1,
                (int) (imagenOriginal.getIconWidth() * escala)
        );

        int nuevoAlto = Math.max(
                1,
                (int) (imagenOriginal.getIconHeight() * escala)
        );

        // Creamos una versión de la imagen con el tamaño calculado.
        Image imagenEscalada =
                imagenOriginal.getImage().getScaledInstance(
                        nuevoAncho,
                        nuevoAlto,
                        Image.SCALE_SMOOTH
                );

        // Quitamos el texto anterior y colocamos la nueva imagen.
        labelFoto.setText("");
        labelFoto.setIcon(new ImageIcon(imagenEscalada));

        // Centramos la fotografía dentro de su espacio.
        labelFoto.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        labelFoto.setVerticalAlignment(
                SwingConstants.CENTER
        );
    }

    // MOSTRAR ADVERTENCIA DE CAMPOS INCOMPLETOS
    // Crea una ventana personalizada cuando faltan datos obligatorios.
    private void mostrarAdvertenciaDatos() {

        // Creamos una nueva ventana para mostrar la advertencia.
        JFrame ventanaAdvertencia =
                new JFrame("Campos incompletos");

        ventanaAdvertencia.setSize(600, 600);
        ventanaAdvertencia.setLocationRelativeTo(this);
        ventanaAdvertencia.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // Evitamos que el usuario pueda cambiar el tamaño de la ventana.
        ventanaAdvertencia.setResizable(false);

        // Buscamos la imagen de advertencia dentro de los recursos del proyecto.
        java.net.URL recurso =
                getClass().getResource(
                        "/imagenes/Advertencia.jpeg"
                );

        // Comprobamos que la imagen exista.
        if (recurso == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró la imagen Advertencia.jpeg",
                    "Error de imagen",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Cargamos la imagen original.
        ImageIcon imagenOriginal =
                new ImageIcon(recurso);

        // Ajustamos la imagen al tamaño de la ventana.
        Image imagenEscalada =
                imagenOriginal.getImage().getScaledInstance(
                        600,
                        600,
                        Image.SCALE_SMOOTH
                );

        // Creamos una etiqueta que contiene la imagen de fondo.
        JLabel fondo = new JLabel(
                new ImageIcon(imagenEscalada)
        );

        fondo.setLayout(new BorderLayout());

        // Creamos un panel para organizar el mensaje y el botón.
        JPanel panelInferior = new JPanel(
                new BorderLayout()
        );

        panelInferior.setOpaque(false);

        // Creamos el mensaje que verá el usuario.
        JLabel mensaje = new JLabel(
                "Por favor, llena tus datos",
                SwingConstants.CENTER
        );

        // Configuramos el tamaño y el estilo del mensaje.
        mensaje.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        mensaje.setForeground(Color.WHITE);

        // Agregamos un espacio alrededor del mensaje.
        mensaje.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 5, 10, 5
                )
        );

        // Creamos el botón para cerrar la advertencia.
        JButton botonEntendido =
                new JButton("Entendido");

        botonEntendido.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        // Al presionar el botón, cerramos la ventana.
        botonEntendido.addActionListener(
                e -> ventanaAdvertencia.dispose()
        );

        // Creamos un panel para organizar el botón.
        JPanel panelBoton = new JPanel();

        panelBoton.setOpaque(false);
        panelBoton.add(botonEntendido);

        // Colocamos los elementos dentro del panel inferior.
        panelInferior.add(
                mensaje,
                BorderLayout.CENTER
        );

        panelInferior.add(
                panelBoton,
                BorderLayout.SOUTH
        );

        // Colocamos el panel inferior sobre la imagen de fondo.
        fondo.add(
                panelInferior,
                BorderLayout.SOUTH
        );

        // Establecemos el contenido y mostramos la ventana.
        ventanaAdvertencia.setContentPane(fondo);
        ventanaAdvertencia.setVisible(true);
    }

    // MOSTRAR MENSAJE DE REGISTRO EXITOSO
    // Crea una ventana personalizada para felicitar al usuario.
    private void mostrarFelicidades() {

        // Creamos la ventana de felicitaciones.
        JFrame ventanaFelicidades =
                new JFrame("Registro exitoso");

        ventanaFelicidades.setSize(600, 600);
        ventanaFelicidades.setLocationRelativeTo(this);

        ventanaFelicidades.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // Evitamos que se pueda cambiar el tamaño de la ventana.
        ventanaFelicidades.setResizable(false);

        // Buscamos la imagen de felicitaciones en los recursos del proyecto.
        java.net.URL recurso =
                getClass().getResource(
                        "/imagenes/Felicidades.jpeg"
                );

        // Comprobamos que la imagen exista.
        if (recurso == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró la imagen Felicidades.jpeg",
                    "Error de imagen",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Cargamos la imagen original.
        ImageIcon imagenOriginal =
                new ImageIcon(recurso);

        // Ajustamos la imagen al tamaño de la ventana.
        Image imagenEscalada =
                imagenOriginal.getImage().getScaledInstance(
                        600,
                        600,
                        Image.SCALE_SMOOTH
                );

        // Creamos una etiqueta que contiene la imagen de fondo.
        JLabel fondo = new JLabel(
                new ImageIcon(imagenEscalada)
        );

        fondo.setLayout(new BorderLayout());

        // Creamos un panel para organizar el mensaje y el botón.
        JPanel panelInferior = new JPanel(
                new BorderLayout()
        );

        panelInferior.setOpaque(false);

        // Creamos el mensaje de registro exitoso.
        JLabel mensaje = new JLabel(
                "¡Usuario registrado con éxito!",
                SwingConstants.CENTER
        );

        mensaje.setFont(
                new Font("Arial", Font.BOLD, 23)
        );

        mensaje.setForeground(Color.BLACK);

        // Agregamos espacio alrededor del mensaje.
        mensaje.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 5, 10, 5
                )
        );

        // Creamos el botón para cerrar la ventana.
        JButton botonEntendido =
                new JButton("Entendido");

        botonEntendido.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        botonEntendido.addActionListener(
                e -> ventanaFelicidades.dispose()
        );

        // Creamos un panel para colocar el botón.
        JPanel panelBoton = new JPanel();

        panelBoton.setOpaque(false);
        panelBoton.add(botonEntendido);

        // Organizamos el mensaje y el botón.
        panelInferior.add(
                mensaje,
                BorderLayout.CENTER
        );

        panelInferior.add(
                panelBoton,
                BorderLayout.SOUTH
        );

        // Colocamos el panel sobre la imagen de fondo.
        fondo.add(
                panelInferior,
                BorderLayout.SOUTH
        );

        // Establecemos el contenido y mostramos la ventana.
        ventanaFelicidades.setContentPane(fondo);
        ventanaFelicidades.setVisible(true);
    }

    // CREAR OBJETO PARA REGISTRAR UN USUARIO
    // Toma la información escrita en el formulario y crea un nuevo usuario.
    private Usuarios getNuevoUsuario()
            throws IOException {

        // Obtenemos el tipo de documento seleccionado.
        String tipoDocumento =
                comboBox1.getSelectedItem().toString();

        // Obtenemos los datos escritos en los campos.
        String documento =
                textField2.getText().trim();

        String primerNombre =
                textField3.getText().trim();

        String segundoNombre =
                textField4.getText().trim();

        String primerApellido =
                textField8.getText().trim();

        String segundoApellido =
                textField9.getText().trim();

        String contrasena =
                textField5.getText();

        String telefono =
                textField6.getText().trim();

        String correo =
                textField7.getText().trim();

        // Creamos y devolvemos el nuevo objeto con toda la información.
        return new Usuarios(
                tipoDocumento,
                documento,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                contrasena,
                telefono,
                correo,
                rutaFoto
        );
    }
}