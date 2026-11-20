package ui;

public class User {

    // ATRIBUTOS USUARIO

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
    private String fotoUrl;

    // CONSTRUCTOR USUARIO

    public User (int id, String tI, String nI, String pN, String sN, String pA, String sA, String username, String password,
                 String telefono, String correo, String fotoUrl) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.correo = correo;
        this.fotoUrl = fotoUrl;
        tipoIdentificacion = tI;
        numeroIdentificacion = nI;
        primerNombre = pN;
        segundoNombre = sN;
        primerApellido = pA;
        segundoApellido = sA;

    }

    // METODOS USUARIO

    @Override
    public String toString() {

        return " U" + id + " - " + username;

    }
     // GET

    public int getId() {
        return id;
    }

    public String getTI() {
        return tipoIdentificacion;
    }

    public String getNI() {
        return numeroIdentificacion;
    }

    public String getPN() {
        return primerNombre;
    }

    public String getSN() {
        return segundoNombre;
    }

    public String getPA() { return primerApellido; }

    public String getSA() { return segundoApellido; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

     // SET

    public void setId(int id) {
        this.id = id;
    }

    public void setTI(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setNI(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void setPN(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public void setSN(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public void setPA(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSA(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
