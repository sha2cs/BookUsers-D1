package modelo;

// Importamos ArrayList para guardar varios usuarios en una lista.
import java.util.ArrayList;

// Importamos las herramientas necesarias para trabajar con archivos.
import java.io.*;
import java.nio.file.*;

public class GestorUsuarios {

    // Dirección de la carpeta donde se guardarán los usuarios.
    // La carpeta UNIPAZ se crea dentro de los archivos del usuario del computador.
    private static final String CARPETA =
            System.getProperty("user.home")
                    + File.separator + "UNIPAZ";

    // Nombre y ubicación del archivo donde se guardan los usuarios.
    private static final String ARCHIVO =
            CARPETA + File.separator + "usuarios.dat";

    // Lista que contiene todos los usuarios registrados.
    private ArrayList<Usuarios> listaUsuarios;

    // CONSTRUCTOR

    // Se ejecuta al crear un objeto de esta clase.
    public GestorUsuarios() {

        // Creamos una lista vacía para almacenar los usuarios.
        listaUsuarios = new ArrayList<>();

        // Intentamos recuperar los usuarios guardados anteriormente.
        cargarUsuarios();
    }

    // AGREGAR USUARIO

    // Recibe un usuario y lo agrega a la lista.
    public void agregarUsuario(Usuarios usuario)
            throws IOException {

        // Comprobamos que el usuario no sea nulo.
        if (usuario != null) {

            // Agregamos el usuario a la lista.
            listaUsuarios.add(usuario);

            try {

                // Guardamos la lista actualizada en el archivo.
                guardarUsuarios();

            } catch (IOException e) {

                // Si ocurre un error al guardar, quitamos el usuario
                // de la lista para evitar que quede registrado solo en memoria.
                listaUsuarios.remove(usuario);

                // Enviamos el error al método que llamo esta operacion.
                throw e;
            }
        }
    }

    // ELIMINAR USUARIO

    // Recibe el ID y busca al usuario que tenga ese número.
    public boolean eliminarUsuario(int id)
            throws IOException {

        // Recorremos la lista para revisar cada usuario.
        for (int i = 0; i < listaUsuarios.size(); i++) {

            Usuarios usuario = listaUsuarios.get(i);

            // Comprobamos si el ID del usuario coincide con el recibido.
            if (usuario.getId() == id) {

                // Eliminamos al usuario de la lista.
                listaUsuarios.remove(i);

                try {

                    // Guardamos la lista sin el usuario eliminado.
                    guardarUsuarios();

                    // Devolvemos true para indicar que se eliminó correctamente.
                    return true;

                } catch (IOException e) {

                    // Si ocurre un error al guardar, volvemos a colocar
                    // al usuario en la posición donde estaba.
                    listaUsuarios.add(i, usuario);

                    throw e;
                }
            }
        }

        // Devolvemos false si no encontramos el ID.
        return false;
    }

    // ACTUALIZAR USUARIO

    // Comprueba si el usuario existe y guarda sus nuevos datos.
    public boolean actualizarUsuario(Usuarios usuario)
            throws IOException {

        // Si no recibimos un usuario válido, terminamos el método.

        if (usuario == null) {
            return false;
        }

        // Recorremos la lista para buscar al usuario.
        for (Usuarios existente : listaUsuarios) {

            // Comparamos los ID para comprobar que sea el mismo usuario.
            if (existente.getId() == usuario.getId()) {

                // Guardamos en el archivo los cambios realizados.
                guardarUsuarios();

                // Indicamos que el usuario fue encontrado y actualizado.
                return true;
            }
        }

        // Si no encontramos el usuario, devolvemos false.
        return false;
    }

    // GUARDAR USUARIOS

    // Este método almacena la lista completa en el archivo.
    private void guardarUsuarios() throws IOException {

        // Creamos la carpeta UNIPAZ si todavía no existe.
        Files.createDirectories(Paths.get(CARPETA));

        // Abrimos el archivo para escribir los datos.
        try (
                ObjectOutputStream salida =
                        new ObjectOutputStream(
                                new FileOutputStream(ARCHIVO)
                        )
        ) {

            // Escribimos la lista completa dentro del archivo.
            salida.writeObject(listaUsuarios);
        }

        // Mostramos un mensaje en la consola.
        System.out.println("Usuarios guardados correctamente.");
    }

    // RECUPERAR USUARIOS

    // Lee el archivo y recupera los usuarios guardados anteriormente.
    @SuppressWarnings("unchecked")
    private void cargarUsuarios() {

        // Creamos un objeto que representa el archivo.
        File archivo = new File(ARCHIVO);

        // Comprobamos si el archivo existe.
        if (!archivo.exists()) {

            // Si no existe, significa que todavía no hay datos guardados.
            System.out.println("No existe un archivo anterior.");

            return;
        }

        // Abrimos el archivo para leer la información almacenada.
        try (
                ObjectInputStream entrada =
                        new ObjectInputStream(
                                new FileInputStream(archivo)
                        )
        ) {

            // Leemos el objeto que está guardado en el archivo.
            Object objeto = entrada.readObject();

            // Comprobamos que el objeto leído sea una lista.
            if (objeto instanceof ArrayList<?>) {

                // Convertimos el objeto en una lista para poder recorrerla.
                ArrayList<?> usuariosGuardados =
                        (ArrayList<?>) objeto;

                // Variable que permite encontrar el ID más alto.
                int mayorId = 0;

                // Recorremos todos los elementos de la lista recuperada.
                for (Object elemento : usuariosGuardados) {

                    // Comprobamos que cada elemento sea un usuario.
                    if (elemento instanceof Usuarios) {

                        // Convertimos el elemento en un objeto Usuarios.
                        Usuarios usuario = (Usuarios) elemento;

                        // Agregamos el usuario a nuestra lista actual.
                        listaUsuarios.add(usuario);

                        // Comprobamos si su ID es mayor que el anterior.
                        if (usuario.getId() > mayorId) {
                            mayorId = usuario.getId();
                        }
                    }
                }

                // Establecemos el siguiente ID para evitar repetir números.
                Usuarios.establecerContadorId(mayorId + 1);

                // Mostramos cuántos usuarios fueron recuperados.
                System.out.println(
                        "Usuarios recuperados: "
                                + listaUsuarios.size()
                );
            }

        } catch (IOException | ClassNotFoundException e) {

            // Mostramos un mensaje si ocurre un error al leer el archivo.
            JOptionPaneHelper.mostrarError(
                    "No se pudieron recuperar los usuarios: "
                            + e.getMessage()
            );
        }
    }

    // OBTENER USUARIOS

    // Devuelve una copia de la lista de usuarios registrados.
    public ArrayList<Usuarios> obtenerUsuarios() {

        return new ArrayList<>(listaUsuarios);
    }

    // BUSCAR POR NOMBRE DE USUARIO

    // Recibe el username y busca si existe en la lista.
    public Usuarios buscarPorUsername(String username) {

        // Si el texto recibido es nulo, no podemos realizar la búsqueda.
        if (username == null) {
            return null;
        }

        // Recorremos todos los usuarios.
        for (Usuarios u : listaUsuarios) {

            // Comparamos los nombres sin importar mayúsculas o minúsculas.
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }

        // Devolvemos null si no encontramos coincidencias.
        return null;
    }

    // BUSCAR POR ID

    // Busca un usuario utilizando su número de identificación interno.
    public Usuarios buscarPorId(int id) {

        // Revisamos cada usuario de la lista.
        for (Usuarios u : listaUsuarios) {

            // Comparamos el ID recibido con el ID del usuario.
            if (u.getId() == id) {
                return u;
            }
        }

        // Si no existe un usuario con ese ID, devolvemos null.
        return null;
    }

    // CANTIDAD DE USUARIOS

    // Devuelve el número total de usuarios registrados.
    public int cantidadUsuarios() {

        return listaUsuarios.size();
    }

    // UBICACIÓN DEL ARCHIVO

    // Devuelve la dirección donde se guardan los usuarios.
    public String obtenerRutaArchivo() {

        return ARCHIVO;
    }
}


// CLASE AUXILIAR
// Esta clase permite mostrar mensajes de error en una ventana.
class JOptionPaneHelper {

    // Recibe el mensaje que queremos mostrar al usuario.
    public static void mostrarError(String mensaje) {

        javax.swing.JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Error de almacenamiento",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}