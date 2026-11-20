public class Usuario {

    private static int siguienteId = 1;

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


    // estos atributos son private entonces solo se pueden usar dentro de la clase, estos son los datos de los usuarios.
    public Usuario(int id, String tipoIdentificacion, String numeroIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String username, String password, String telefono, String correo, String rutaFoto) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.correo = correo;
        this.rutaFoto = rutaFoto;

        // Mi nota: recibe los datos y los guarda en los atributos;
        // this.id es el atributo y id es el dato que llegó.



    }

    public int getId() {
        return id;
    }

    public String getTipoIdentificacion(){
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion(){
        return numeroIdentificacion;
    }

    public String getPrimerNombre(){
        return primerNombre;
    }

    public String getSegundoNombre(){
        return segundoNombre;
    }

    public String getPrimerApellido(){
        return primerApellido;
    }

    public String getSegundoApellido(){
        return segundoApellido;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getCorreo(){
        return correo;
    }

    public String getRutaFoto(){
        return rutaFoto;
    }

    // Mi nota: como los atributos son private,
    // los get permiten leerlos desde otras clases; return devuelve el valor.

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public void setPrimerNombre(String primerNombre){
        this.primerNombre = primerNombre;
    }
    public void setSegundoNombre(String segundoNombre){
        this.segundoNombre = segundoNombre;
    }
    public void setPrimerApellido(String primerApellido){
        this.primerApellido = primerApellido;
    }
    public void setSegundoApellido(String segundoApellido){
        this.segundoApellido = segundoApellido;
    }
    public void setUsername (String username){
        this.username = username;
    }
    public void setPassword (String password){
        this.password = password;
    }
    public void setTelefono (String telefono){
        this.telefono = telefono;
    }
    public void setCorreo (String correo){
        this.correo = correo;
    }
    public void setRutaFoto (String rutaFoto){
        this.rutaFoto = rutaFoto;
    }

    public static int generarId(){
        return siguienteId++;
    }

    //El set cambia el valor de un atributo privado desde otra clase, el get lee el set escribe
    //No hay SetId porque ese lo asgina el programa

    @Override
    public String toString(){
        String nombreCompleto = primerNombre;
        if (!segundoNombre.isEmpty()){
            nombreCompleto += " " + segundoNombre;
        }
        nombreCompleto += " " + primerApellido;
        if (!segundoApellido.isEmpty()){
            nombreCompleto += " " + segundoApellido;
        }
        return id + " - " + username + " - " + nombreCompleto;
    }
}



