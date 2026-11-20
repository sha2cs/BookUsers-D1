import javax.swing.*;
import java.util.ArrayList;

public class Datos {

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static DefaultListModel<String> modelo = new DefaultListModel<>();

    private static int contadorId = 1;

    static {
    }
    public static void agregarUsuario(String tipoIdentificacion, String numeroIdentificacion,
                                      String primerNombre, String segundoNombre,
                                      String primerApellido, String segundoApellido,
                                      String username, String password,
                                      String telefono, String correo, String rutaFoto) {

        Usuario nuevo = new Usuario(tipoIdentificacion, numeroIdentificacion,
                primerNombre, segundoNombre,
                primerApellido, segundoApellido,
                password, telefono, correo, rutaFoto);

        nuevo.setId(contadorId);
        nuevo.setUsername(username);
        contadorId++;
        listaUsuarios.add(nuevo);
        modelo.addElement(nuevo.getId() + " - " + primerNombre + " " + primerApellido
                + " - " + username + " - " + correo);
    }

    public static Usuario buscarPorId(int id) {
        for (Usuario u : listaUsuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public static int cantidad() {
        return listaUsuarios.size();
    }
}


