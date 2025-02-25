public class Usuario {
    String id, nombre, direccion, email, password;
    boolean estado;

    /**
     * constructor de la clase
     * @param id
     * @param nombre
     * @param direccion
     * @param email
     * @param password
     * @param estado
     */
    public Usuario(String id, String nombre, String direccion, String email, String password, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean Estado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
