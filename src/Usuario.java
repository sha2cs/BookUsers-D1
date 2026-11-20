public class Usuario {

        private int id;
        private String tipoIdentificacion;
        private String numeroIdentificacion;
        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;
        private String username;
        private String password;
        private String telefono;
        private String correo;
        private String rutaFoto;

        public Usuario() {}

        public Usuario(String tipoIdentificacion, String numeroIdentificacion,
                       String primerNombre, String segundoNombre,
                       String primerApellido, String segundoApellido,
                       String password, String telefono, String correo, String rutaFoto) {
            this.tipoIdentificacion = tipoIdentificacion;
            this.numeroIdentificacion = numeroIdentificacion;
            this.primerNombre = primerNombre;
            this.segundoNombre = segundoNombre;
            this.primerApellido = primerApellido;
            this.segundoApellido = segundoApellido;
            this.password = password;
            this.telefono = telefono;
            this.correo = correo;
            this.rutaFoto = rutaFoto;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getTipoIdentificacion() { return tipoIdentificacion; }
        public String getNumeroIdentificacion() { return numeroIdentificacion; }
        public String getPrimerNombre() { return primerNombre; }
        public String getSegundoNombre() { return segundoNombre; }
        public String getPrimerApellido() { return primerApellido; }
        public String getSegundoApellido() { return segundoApellido; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public String getTelefono() { return telefono; }
        public String getCorreo() { return correo; }
        public String getRutaFoto() { return rutaFoto; }
    }

