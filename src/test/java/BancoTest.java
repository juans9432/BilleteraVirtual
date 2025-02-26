
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    private Banco banco;

    @BeforeEach
    public void crearDatosPrueba() {
        // Se crea una lista de usuarios con 3 estudiantes para las pruebas unitarias de la clase banco
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("1234", "José", "carrera 9 #3-33", "jose@gmail.com", "11221122", true));
        usuarios.add(new Usuario("5678", "Ana", "calle 14 #13-22", "ana@gmail.com", "33443344", true));
        usuarios.add(new Usuario("9012", "Mario", "calle 1 #1-23", "mario@gmail.com", "123456", true));

        // Se crea una instancia de GestionEstudiantes con la lista de estudiantes
        banco = new Banco("davivienda");
        banco.setUsuarios(usuarios);
    }


    @Test
    public void agregarUsuarioTest() throws Exception {
        Banco banco = new Banco("davivienda");
        Usuario usuario1 = new Usuario("1115183021", "juan", "calle 3 #4-05",
                "juan@gmail.com", "12345", true);
        banco.agregar(usuario1);

        ArrayList<Usuario> usuarios = banco.getUsuarios();

        assertEquals(1, usuarios.size());
        assertEquals(usuario1, usuarios.get(0));
    }

    @Test
    public void obtenerTest(){
        // Se obtiene un usuario con ID 1234
        Usuario usuario = banco.obtenerUsuario("1234");

        // Se espera que el usuario no sea nulo y que tenga los datos correctos
        assertNotNull(usuario);
        assertEquals("1234", usuario.getId());
        assertEquals("José", usuario.getNombre());
        assertEquals("carrera 9 #3-33", usuario.getDireccion());
        assertEquals("jose@gmail.com", usuario.getEmail());
        assertEquals("11221122", usuario.getPassword());

        boolean estadoEsperado = true;
        assertTrue(usuario.isEstado());
    }

    @Test
    public void eliminarTest(){
        // Se espera que no se lance ninguna excepción al eliminar el usuario con ID 1234
        assertDoesNotThrow( () -> {
            banco.eliminarUsuario("1234");
        } );

        // Se espera que el estudiante con ID 1234 no exista
        Usuario usuario = banco.obtenerUsuario("1234");
        assertNull(usuario);
    }

    @Test
    public void actualizarTest(){
        // Se crea un nuevo estudiante con los datos actualizados
        Usuario usuario = new Usuario(
                "1234",
                "Jose",
                "carrera 9 #3-33",
                "jose@gmail.com",
                "11221122",
                true
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            banco.actualizar(usuario);
        } );

        // Se obtiene el estudiante actualizado
        Usuario usuarioActualizado = banco.obtenerUsuario("1234");

        // Se espera que el estudiante no sea nulo y que tenga los datos actualizados
        assertNotNull(usuarioActualizado);
        assertEquals("Jose", usuarioActualizado.getNombre());
        assertEquals("carrera 9 #3-33", usuarioActualizado.getDireccion());
        assertEquals("jose@gmail.com", usuarioActualizado.getEmail());
        assertEquals("11221122", usuarioActualizado.getPassword());
        assertTrue(usuarioActualizado.isEstado());
    }




}
