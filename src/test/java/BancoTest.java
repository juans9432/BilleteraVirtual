import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BancoTest {

    @Test
    public void agregarUsuarioTest() throws Exception {
        Banco banco = new Banco("davivienda", new ArrayList<>(), new ArrayList<>());
        Usuario usuario1 = new Usuario("1115183021", "juan", "calle 3 #4-05",
                "juan@gmail.com", "12345", true);
        banco.agregar(usuario1);

        ArrayList<Usuario> usuarios = banco.getUsuarios();

        assertEquals(1, usuarios.size());
        assertEquals(usuario1, usuarios.get(0));
    }

    @Test
    public void obtenerUsuarioExistenteTest() {
        // Crear banco con una lista de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario("1115183021", "Juan", "Calle 3 #4-05", "juan@gmail.com", "12345", true);
        usuarios.add(usuario1);
        Banco banco = new Banco("Davivienda", usuarios, new ArrayList<>());

        // Intentar obtener el usuario
        Usuario usuarioObtenido = banco.obtener("1115183021");

        // Verificar que el usuario fue encontrado correctamente
        assertNotNull(usuarioObtenido);
        assertEquals(usuario1, usuarioObtenido);
    }
}
