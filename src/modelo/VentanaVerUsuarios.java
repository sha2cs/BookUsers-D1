package modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

// Librerías utilizadas para crear documentos PDF.
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.Image;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

// Esta clase crea la ventana donde se pueden consultar
// y administrar los usuarios que ya están registrados.
public class VentanaVerUsuarios extends JFrame {

    // Elementos de la interfaz gráfica.
    private JComboBox ListaSeccionUsuario;
    private JPanel PanelVerUsuario;
    private JScrollPane ScrollVerUsuario;
    private JButton PDFButton;
    private JButton eliminarButton;
    private JButton editarButton;

    // Permite acceder a los usuarios guardados.
    private GestorUsuarios gestor;

    // Lista que muestra los usuarios en pantalla.
    private JList<Usuarios> listaUsuarios;

    // Modelo que almacena los elementos de la lista.
    private DefaultListModel<Usuarios> modeloLista;

    // CONSTRUCTOR DE LA VENTANA
    public VentanaVerUsuarios(GestorUsuarios gestor) {

        // Recibimos el gestor que contiene los usuarios registrados.
        this.gestor = gestor;

        // Configuramos el título de la ventana.
        setTitle("Usuarios registrados - UNIPAZ");

        // Creamos el fondo de la ventana utilizando una imagen.
        PanelConFondo fondo =
                new PanelConFondo("/imagenes/Imagen4.jpeg");

        fondo.setLayout(new BorderLayout());

        // Hacemos transparente el panel para permitir ver el fondo.
        PanelVerUsuario.setOpaque(false);
        fondo.add(PanelVerUsuario, BorderLayout.CENTER);

        setContentPane(fondo);

        // Configuramos el tamaño y el comportamiento de la ventana.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // CREAR LISTA DE USUARIOS

        // Creamos el modelo donde se guardarán los usuarios.
        modeloLista = new DefaultListModel<>();

        // Creamos la lista y le asignamos el modelo anterior.
        listaUsuarios = new JList<>(modeloLista);

        // PERSONALIZAR LA APARIENCIA DE CADA USUARIO

        // Modificamos la forma en que se muestra cada usuario
        // dentro de la lista.
        listaUsuarios.setCellRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus) {

                        // Obtenemos la apariencia normal del elemento.
                        super.getListCellRendererComponent(
                                list,
                                value,
                                index,
                                isSelected,
                                cellHasFocus
                        );

                        // Comprobamos que el elemento sea un usuario.
                        if (value instanceof Usuarios) {

                            // Convertimos el elemento en un objeto Usuarios.
                            Usuarios usuario = (Usuarios) value;

                            // Mostramos el primer nombre, el primer apellido
                            // y el nombre de usuario.
                            setText(
                                    usuario.getPrimerNombre() + " " +
                                            usuario.getPrimerApellido() + " - " +
                                            usuario.getUsername()
                            );
                        }

                        return this;
                    }
                }
        );

        // TRANSPARENCIA

        // Hacemos transparentes la lista y su área de desplazamiento.
        ScrollVerUsuario.setOpaque(false);
        ScrollVerUsuario.getViewport().setOpaque(false);
        listaUsuarios.setOpaque(false);

        // Colocamos la lista dentro del área de desplazamiento.
        ScrollVerUsuario.setViewportView(listaUsuarios);

        // CARGAR USUARIOS

        // Mostramos los usuarios que ya están registrados.
        cargarUsuarios();

        // SINCRONIZAR COMBOBOX CON LISTA

        // Cuando se selecciona un usuario en el menú desplegable,
        // también se selecciona en la lista.
        ListaSeccionUsuario.addActionListener(e -> {

            int indice = ListaSeccionUsuario.getSelectedIndex();

            // Comprobamos que el índice corresponda a un usuario existente.
            if (indice >= 0 && indice < modeloLista.size()) {
                listaUsuarios.setSelectedIndex(indice);
            }
        });

        // SINCRONIZAR LISTA CON COMBOBOX

        // Cuando se selecciona un usuario en la lista,
        // también se actualiza el menú desplegable.
        listaUsuarios.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int indice = listaUsuarios.getSelectedIndex();

                if (indice >= 0 &&
                        ListaSeccionUsuario.getSelectedIndex() != indice) {

                    ListaSeccionUsuario.setSelectedIndex(indice);
                }
            }
        });

        // BOTÓN PDF

        // Genera un documento PDF con la información
        // del usuario que se encuentre seleccionado.
        PDFButton.addActionListener(e -> {

            Usuarios usuario = obtenerUsuarioSeleccionado();

            // Si no se seleccionó ningún usuario, mostramos un aviso.
            if (usuario == null) {
                mostrarAvisoSeleccion();
                return;
            }

            // Si existe una selección, generamos su PDF.
            generarPDF(usuario);
        });

        // BOTÓN EDITAR

        // Abre el formulario para modificar los datos del usuario.
        editarButton.addActionListener(e -> editarUsuario());

        // BOTÓN ELIMINAR

        // Permite eliminar el usuario seleccionado.
        eliminarButton.addActionListener(e -> eliminarUsuario());
    }

    // OBTENER EL USUARIO SELECCIONADO
    private Usuarios obtenerUsuarioSeleccionado() {

        // Primero comprobamos si hay un usuario seleccionado en la lista.
        int indice = listaUsuarios.getSelectedIndex();

        if (indice >= 0 && indice < modeloLista.size()) {
            return modeloLista.getElementAt(indice);
        }

        // Si no hay selección en la lista, revisamos el menú desplegable.
        indice = ListaSeccionUsuario.getSelectedIndex();

        if (indice >= 0 && indice < modeloLista.size()) {
            return modeloLista.getElementAt(indice);
        }

        // Si no hay ningún usuario seleccionado, devolvemos null.
        return null;
    }

    // MENSAJE CUANDO NO HAY SELECCIÓN
    private void mostrarAvisoSeleccion() {

        // Mostramos una advertencia para indicar
        // que primero se debe seleccionar un usuario.
        JOptionPane.showMessageDialog(
                this,
                "Primero debes seleccionar un usuario.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // CARGAR USUARIOS REGISTRADOS
    private void cargarUsuarios() {

        // Limpiamos los datos anteriores de la lista y del menú.
        modeloLista.clear();
        ListaSeccionUsuario.removeAllItems();

        // Obtenemos todos los usuarios almacenados en el gestor.
        ArrayList<Usuarios> usuariosRegistrados =
                gestor.obtenerUsuarios();

        // Recorremos la lista de usuarios registrados.
        for (Usuarios usuario : usuariosRegistrados) {

            // Agregamos cada usuario a la lista.
            modeloLista.addElement(usuario);

            // También agregamos su información al menú desplegable.
            ListaSeccionUsuario.addItem(
                    usuario.getPrimerNombre() + " " +
                            usuario.getPrimerApellido() + " - " +
                            usuario.getUsername()
            );
        }

        // Quitamos cualquier selección anterior.
        listaUsuarios.clearSelection();
        ListaSeccionUsuario.setSelectedIndex(-1);
    }

    // EDITAR USUARIO
    private void editarUsuario() {

        // Obtenemos el usuario que se desea modificar.
        Usuarios usuarioSeleccionado =
                obtenerUsuarioSeleccionado();

        // Si no hay selección, mostramos una advertencia.
        if (usuarioSeleccionado == null) {
            mostrarAvisoSeleccion();
            return;
        }

        // Guardamos el ID para poder encontrar al usuario
        // nuevamente después de cerrar la ventana de edición.
        int idUsuario = usuarioSeleccionado.getId();

        // Abrimos el formulario con los datos del usuario seleccionado.
        VentanaCrearUsuario ventanaEdicion =
                new VentanaCrearUsuario(gestor, usuarioSeleccionado);

        // ACTUALIZAR LA LISTA CUANDO SE CIERRE LA VENTANA

        // Este evento se ejecuta cuando se cierra el formulario de edición.
        ventanaEdicion.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosed(WindowEvent e) {

                        // Actualizamos la lista para mostrar los cambios.
                        cargarUsuarios();

                        // Volvemos a seleccionar el usuario editado.
                        seleccionarUsuarioPorId(idUsuario);
                    }
                }
        );

        // Mostramos la ventana de edición.
        ventanaEdicion.setVisible(true);
    }

    // ELIMINAR USUARIO
    private void eliminarUsuario() {

        // Obtenemos el usuario seleccionado.
        Usuarios usuarioSeleccionado =
                obtenerUsuarioSeleccionado();

        // Comprobamos que exista una selección.
        if (usuarioSeleccionado == null) {
            mostrarAvisoSeleccion();
            return;
        }

        // Preguntamos si realmente desea eliminar al usuario.
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas eliminar al usuario "
                        + usuarioSeleccionado.getUsername() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // Si la respuesta no es afirmativa, cancelamos la operación.
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            // Intentamos eliminar al usuario utilizando su ID.
            boolean eliminado =
                    gestor.eliminarUsuario(usuarioSeleccionado.getId());

            if (eliminado) {

                // Actualizamos la lista después de eliminarlo.
                cargarUsuarios();

                // Informamos que la eliminación fue exitosa.
                JOptionPane.showMessageDialog(
                        this,
                        "El usuario fue eliminado correctamente.",
                        "Eliminación exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                // Avisamos si no se encontró el usuario.
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el usuario que deseas eliminar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (IOException ex) {

            // Mostramos el error si ocurre un problema
            // al guardar los cambios de la eliminación.
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el usuario:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            // Imprimimos los detalles del error en la consola.
            ex.printStackTrace();
        }
    }

    // VOLVER A SELECCIONAR UN USUARIO POR SU ID
    private void seleccionarUsuarioPorId(int id) {

        // Recorremos todos los usuarios de la lista.
        for (int i = 0; i < modeloLista.size(); i++) {

            Usuarios usuario = modeloLista.getElementAt(i);

            // Comparamos el ID de cada usuario con el ID recibido.
            if (usuario.getId() == id) {

                // Seleccionamos al usuario encontrado.
                listaUsuarios.setSelectedIndex(i);

                // Nos aseguramos de que sea visible en la lista.
                listaUsuarios.ensureIndexIsVisible(i);

                // Actualizamos también el menú desplegable.
                ListaSeccionUsuario.setSelectedIndex(i);

                return;
            }
        }
    }

    // GENERAR PDF INDIVIDUAL
    private void generarPDF(Usuarios usuario) {

        // Abrimos un selector para elegir dónde guardar el documento.
        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle("Guardar PDF del usuario");

        // Creamos un nombre inicial para el archivo.
        String nombreArchivo =
                "Usuario_" + usuario.getUsername() + ".pdf";

        selector.setSelectedFile(new File(nombreArchivo));

        // Mostramos la ventana para guardar el PDF.
        int resultado = selector.showSaveDialog(this);

        // Si el usuario cancela, detenemos el proceso.
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        // Obtenemos el archivo seleccionado.
        File archivo = selector.getSelectedFile();

        // ASEGURAR EXTENSIÓN PDF

        // Comprobamos que el nombre del archivo termine en .pdf.
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {

            // Si no tiene la extensión, se la agregamos.
            archivo = new File(
                    archivo.getAbsolutePath() + ".pdf"
            );
        }

        // COMPROBAR SI EL ARCHIVO YA EXISTE

        // Si existe un archivo con el mismo nombre,
        // preguntamos si desea reemplazarlo.
        if (archivo.exists()) {

            int confirmar = JOptionPane.showConfirmDialog(
                    this,
                    "El archivo ya existe. ¿Deseas reemplazarlo?",
                    "Confirmar reemplazo",
                    JOptionPane.YES_NO_OPTION
            );

            // Si no acepta, cancelamos la generación.
            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }
        }

        // Creamos el documento PDF.
        Document documento = new Document();

        try {

            // Indicamos dónde se guardará el documento.
            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(archivo)
            );

            // Abrimos el documento para comenzar a escribir.
            documento.open();

            // TÍTULO

            // Definimos el tipo, tamaño y estilo de letra del título.
            Font titulo = new Font(
                    Font.HELVETICA,
                    18,
                    Font.BOLD
            );

            // Creamos el texto que aparecerá en la parte superior.
            Paragraph encabezado = new Paragraph(
                    "FICHA INDIVIDUAL DE USUARIO",
                    titulo
            );

            // Centramos el título y dejamos espacio debajo.
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingAfter(20);

            // Agregamos el título al documento.
            documento.add(encabezado);

            // TABLA DE INFORMACIÓN

            // Creamos una tabla con dos columnas.
            PdfPTable tabla = new PdfPTable(2);

            // Hacemos que la tabla ocupe todo el ancho disponible.
            tabla.setWidthPercentage(100);

            // Establecemos la proporción del ancho de las columnas.
            tabla.setWidths(new float[]{1, 2});

            // Agregamos los datos del usuario a la tabla.
            agregarFila(tabla, "ID",
                    String.valueOf(usuario.getId()));

            agregarFila(tabla, "Tipo de documento",
                    usuario.getTipoDocumento());

            agregarFila(tabla, "Número de documento",
                    usuario.getDocumento());

            agregarFila(tabla, "Primer nombre",
                    usuario.getPrimerNombre());

            agregarFila(tabla, "Segundo nombre",
                    usuario.getSegundoNombre());

            agregarFila(tabla, "Primer apellido",
                    usuario.getPrimerApellido());

            agregarFila(tabla, "Segundo apellido",
                    usuario.getSegundoApellido());

            agregarFila(tabla, "Nombre de usuario",
                    usuario.getUsername());

            agregarFila(tabla, "Teléfono",
                    usuario.getTelefono());

            agregarFila(tabla, "Correo electrónico",
                    usuario.getCorreo());

            // Agregamos la tabla completa al documento.
            documento.add(tabla);

            // FOTOGRAFÍA

            // Obtenemos la fotografía guardada del usuario.
            byte[] fotografia = usuario.getFotografia();

            // Comprobamos que exista una fotografía.
            if (fotografia != null && fotografia.length > 0) {

                // Agregamos un pequeño título antes de la imagen.
                documento.add(
                        new Paragraph("\nFotografía del usuario:")
                );

                // Convertimos los datos de la fotografía en una imagen.
                Image imagen = Image.getInstance(fotografia);

                // Ajustamos el tamaño de la fotografía.
                imagen.scaleToFit(150, 150);

                // Centramos la fotografía.
                imagen.setAlignment(Element.ALIGN_CENTER);

                // Agregamos la fotografía al documento.
                documento.add(imagen);
            }

            // Cerramos el documento para guardar el PDF.
            documento.close();

            // Informamos que el PDF se creó correctamente.
            JOptionPane.showMessageDialog(
                    this,
                    "El PDF del usuario se generó correctamente.",
                    "PDF generado",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {

            // Si ocurre un error, comprobamos si el documento
            // todavía está abierto para poder cerrarlo.
            if (documento.isOpen()) {
                documento.close();
            }

            // Mostramos el mensaje del error.
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo generar el PDF:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            // Imprimimos los detalles del error en la consola.
            ex.printStackTrace();
        }
    }

    // AGREGAR FILAS A LA TABLA DEL PDF
    private void agregarFila(
            PdfPTable tabla,
            String campo,
            String valor) {

        // Creamos una fuente en negrita para los nombres de los campos.
        Font negrita = new Font(
                Font.HELVETICA,
                10,
                Font.BOLD
        );

        // Creamos la primera celda con el nombre del campo.
        PdfPCell celdaCampo = new PdfPCell(
                new Paragraph(campo, negrita)
        );

        // Creamos la segunda celda con el valor correspondiente.
        // Si el valor es null, dejamos el espacio vacío.
        PdfPCell celdaValor = new PdfPCell(
                new Paragraph(valor == null ? "" : valor)
        );

        // Dejamos espacio interno en ambas celdas.
        celdaCampo.setPadding(8);
        celdaValor.setPadding(8);

        // Agregamos las dos celdas a la tabla.
        tabla.addCell(celdaCampo);
        tabla.addCell(celdaValor);
    }
}