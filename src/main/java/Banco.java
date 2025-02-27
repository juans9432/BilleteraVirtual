import java.util.ArrayList;

public class Banco {
    String nombre;
    ArrayList<Usuario> usuarios;
    ArrayList<BilleteraVirtual> billeteras;

    /**
     * constructor de la clase
     */
    public Banco(String nombre) {
        this.nombre=nombre;
        this.usuarios = new ArrayList<>();
        this.billeteras = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<BilleteraVirtual> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<BilleteraVirtual> billeteras) {
        this.billeteras = billeteras;
    }

    /**
     * metodo para agregar un usuario
     * @param usuario
     * @throws Exception
     */
    public void agregar(Usuario usuario) throws Exception{

        Usuario usuarioBuscado = obtenerUsuario(usuario.getId());

        // Si el estudiante ya existe, lanzar una excepción
        if(usuarioBuscado!=null){
            throw new Exception("Ya existe un usuario con el mismo ID");
        }else{
            usuarios.add(usuario);
        }
    }

    /**
     * metodo para obtener el usuario
     * @param id
     * @return
     */
    public Usuario obtenerUsuario(String id){

        // Buscar el usuario con el ID dado
        return usuarios
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    /**
     * metodo para eliminar un usuario
     * @param id
     * @throws Exception
     */
    public void eliminarUsuario(String id) throws Exception{
        Usuario usuarioBuscado = obtenerUsuario(id);

        // Si el usuario no existe, lanzar una excepción
        if(usuarioBuscado==null){
            throw new Exception("No existe un usuario con el ID dado");
        }else{
            usuarios.remove(usuarioBuscado);
        }
    }

    /**
     *metodo que actualiza un usuario
     * @param nuevoUsuario
     * @throws Exception
     */
    public void actualizar(Usuario nuevoUsuario) throws Exception{
        Usuario usuarioBuscado = obtenerUsuario(nuevoUsuario.getId());

        // Si el usuario no existe, lanzar una excepción
        if(usuarioBuscado!=null){
            usuarioBuscado.setId(nuevoUsuario.getId());
            usuarioBuscado.setNombre(nuevoUsuario.getNombre());
            usuarioBuscado.setDireccion(nuevoUsuario.getDireccion());
            usuarioBuscado.setEmail(nuevoUsuario.getEmail());
            usuarioBuscado.setPassword(nuevoUsuario.getPassword());
            usuarioBuscado.setEstado(nuevoUsuario.isEstado());

        }else{
            throw new Exception("No existe un usuario con el ID dado");
        }
    }

    /**
     * metodo que agrega una billetera
     * @param billetera
     * @throws Exception
     */
    public void agregarBilletera(BilleteraVirtual billetera) throws Exception{

        BilleteraVirtual billeteraBuscada = obtenerBilletera(billetera.getNumero());

        // Si la billetera ya existe, lanzar una excepción
        if(billeteraBuscada!=null){
            throw new Exception("Ya existe una billetera con el mismo numero");
        }else{
            billeteras.add(billetera);
        }
    }

    /**
     * metodo que obtiene la billetera
     * @param numero
     * @return
     */
    public BilleteraVirtual obtenerBilletera(String numero){

        // Buscar la billetera con el ID dado
        return billeteras
                .stream()
                .filter(e -> e.getNumero().equals(numero))
                .findFirst()
                .orElse(null);

    }



}
