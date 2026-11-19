import java.awt.event.ActionListener;

public class Usuario {

        private int id;
        private String tipoIdentificacion;
        private String numeroIdentificacion;
        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;
        private String username;
        private String Password;
        private String telefono;
        private String correo;
        private String RutaImagen;

        public Usuario(int id, String tipoIdentificacion,
                       String numeroIdentificacion, String primerNombre,
                       String segundoNombre, String primerApellido,
                       String segundoApellido, String username,
                       String password, String telefono,
                       String correo, String rutaFoto) {

            this.id = id;
            this.tipoIdentificacion = tipoIdentificacion;
            this.numeroIdentificacion = numeroIdentificacion;
            this.primerNombre = primerNombre;
            this.segundoNombre = segundoNombre;
            this.primerApellido = primerApellido;
            this.segundoApellido = segundoApellido;
            this.username = username;
            this.Password= password;
            this.telefono = telefono;
            this.correo = correo;
            this.RutaImagen = rutaFoto;
        }

        public int getId() { return id; }

        public String getTipoIdentificacion() {
            return tipoIdentificacion;
        }

        public String getNumeroIdentificacion() {
            return numeroIdentificacion;
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

        public String getUsername() { return username; }

        public String getPassword() { return Password; }

        public String getTelefono() { return telefono; }

        public String getCorreo() { return correo; }

        public String getRutaFoto() { return RutaImagen; }

        public void setTipoIdentificacion(String tipo) {
            this.tipoIdentificacion = tipo;
        }

        public void setNumeroIdentificacion(String numero) {
            this.numeroIdentificacion = numero;
        }

        public void setPrimerNombre(String nombre) {
            this.primerNombre = nombre;
        }

        public void setSegundoNombre(String nombre) {
            this.segundoNombre = nombre;
        }

        public void setPrimerApellido(String apellido) {
            this.primerApellido = apellido;
        }

        public void setSegundoApellido(String apellido) {
            this.segundoApellido = apellido;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.Password = password;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public void setRutaFoto(String rutaFoto) {
            this.RutaImagen = rutaFoto;
        }

    @Override
    public String toString() {

        String nombreCompleto = primerNombre;

        if (segundoNombre != null && !segundoNombre.trim().isEmpty()) {
            nombreCompleto += " " + segundoNombre;
        }

        nombreCompleto += " " + primerApellido;

        if (segundoApellido != null && !segundoApellido.trim().isEmpty()) {
            nombreCompleto += " " + segundoApellido;
        }
        return id + " - " + nombreCompleto + " (" + tipoIdentificacion + ": " + numeroIdentificacion + ")";
    }
}

