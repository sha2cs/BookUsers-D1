package modelo;

// Importamos herramientas para trabajar con textos.
import java.text.Normalizer;

// Permite guardar los objetos en archivos.
import java.io.IOException;
import java.io.Serializable;

// Permite leer fotografías desde una ubicación.
import java.nio.file.Files;
import java.nio.file.Paths;

public class Usuarios implements Serializable {

    // Identificador que permite mantener la compatibilidad
    // de esta clase cuando guardamos y recuperamos sus objetos.
    private static final long serialVersionUID = 1L;

    // Contador que asigna automáticamente un ID a cada usuario.

    // Comienza en 1 y aumenta cada vez que se crea un usuario.
    private static int contadorId = 1;


    // DATOS DEL USUARIO

    // Estas variables almacenan la información de cada persona.

    private int id;
    private String tipoDocumento;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String username;
    private String telefono;
    private String contrasena;
    private String correo;
    private byte[] fotografia;
    private String rutaFoto;


    // CONSTRUCTOR

    // Recibe los datos necesarios para crear un nuevo usuario.
    public Usuarios(
            String tipoDocumento,
            String documento,
            String primerNombre,
            String segundoNombre,
            String primerApellido,
            String segundoApellido,
            String contrasena,
            String telefono,
            String correo,
            String rutaFoto
    ) throws IOException {

        // Asignamos un ID único y aumentamos el contador.
        this.id = contadorId++;

        // Guardamos los datos recibidos en sus respectivas variables.
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;

        // Generamos automáticamente el nombre de usuario.
        this.username = generarUsername(
                primerApellido,
                segundoApellido,
                primerNombre
        );

        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rutaFoto = rutaFoto;

        // Leemos la fotografía y guardamos su contenido en un arreglo de bytes.
        this.fotografia = Files.readAllBytes(
                Paths.get(rutaFoto)
        );
    }

    // GENERAR NOMBRE DE USUARIO

    // Crea automáticamente un username utilizando los nombres y apellidos.
    private String generarUsername(
            String apellido1,
            String apellido2,
            String nombre
    ) {

        // Primero organizamos los textos para facilitar su utilización.
        String a1 = normalizar(apellido1);
        String a2 = normalizar(apellido2);
        String n = normalizar(nombre);

        String prefijo;

        // Si existen los dos apellidos, utilizamos la primera letra de cada uno.
        if (!a1.isEmpty() && !a2.isEmpty()) {

            prefijo = a1.substring(0, 1)
                    + a2.substring(0, 1);

            // Si solamente existe el primer apellido, utilizamos sus dos primeras letras.
        } else if (!a1.isEmpty()) {

            prefijo = a1.substring(
                    0, Math.min(2, a1.length())
            );

            // Si no hay apellidos, utilizamos la letra u.
        } else {

            prefijo = "u";
        }

        // Unimos el prefijo con el primer nombre.
        return prefijo + n;
    }

    // NORMALIZAR TEXTO

    // Elimina espacios, tildes y convierte el texto a minúsculas.
    private String normalizar(String texto) {

        // Si no recibimos texto, devolvemos una cadena vacía.
        if (texto == null) {
            return "";
        }

        return Normalizer.normalize(
                        texto.trim().toLowerCase(),
                        Normalizer.Form.NFD
                ).replaceAll("\\p{M}", "")
                .replaceAll("\\s+", "");
    }


    // ACTUALIZAR CONTADOR DE ID

    // Permite establecer el siguiente ID después de recuperar los usuarios.
    static void establecerContadorId(int siguienteId) {
        contadorId = siguienteId;
    }

    // ACTUALIZAR DATOS DEL USUARIO

    // Recibe la nueva información y modifica los datos del mismo usuario.
    public void actualizarDatos(
            String tipoDocumento,
            String documento,
            String primerNombre,
            String segundoNombre,
            String primerApellido,
            String segundoApellido,
            String contrasena,
            String telefono,
            String correo,
            String nuevaRutaFoto
    ) throws IOException {

        // Conservamos la fotografía anterior mientras no se seleccione otra.
        byte[] nuevaFotografia = this.fotografia;

        // Comprobamos si se seleccionó una nueva fotografía.
        if (nuevaRutaFoto != null
                && !nuevaRutaFoto.trim().isEmpty()) {

            // Leemos la nueva fotografía y guardamos su contenido.
            nuevaFotografia = Files.readAllBytes(
                    Paths.get(nuevaRutaFoto)
            );
        }

        // Actualizamos los datos personales del usuario.
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;

        this.contrasena = contrasena;
        this.telefono = telefono;
        this.correo = correo;

        // Generamos nuevamente el username con los nombres y apellidos actualizados.
        this.username = generarUsername(
                primerApellido,
                segundoApellido,
                primerNombre
        );

        // Si se seleccionó otra fotografía, reemplazamos la anterior.
        if (nuevaRutaFoto != null
                && !nuevaRutaFoto.trim().isEmpty()) {

            this.rutaFoto = nuevaRutaFoto;
            this.fotografia = nuevaFotografia;
        }
    }



    // GETTERS

    // Estos métodos permiten consultar los datos privados del usuario.
    // Cada uno devuelve el valor de una variable.

    public int getId() {
        return id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getUsername() {
        return username;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    // Devuelve una copia de la fotografía para evitar modificar
    // directamente el arreglo de bytes almacenado en el usuario.
    public byte[] getFotografia() {
        return fotografia == null ? null : fotografia.clone();
    }
}