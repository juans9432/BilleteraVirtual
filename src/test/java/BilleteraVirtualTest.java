import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BilleteraVirtualTest {

    /**
     * test para el metodo que genera un numero aleatorio para la billetera
     */
    @Test
    public void generarNumeroUnicoTest() {

        BilleteraVirtual billetera1 = new BilleteraVirtual("1233291233", 10000, new Usuario("123465566",  "juan", "calle23", "juan@email.com", "12345", true));
        BilleteraVirtual billetera2 = new BilleteraVirtual("1234568533", 23000, new Usuario("1234890", "camilo", "calle12", "camilo@email.com", "12334",false));

        assertNotNull(billetera1.getNumero());
        assertNotNull(billetera2.getNumero());
        assertNotEquals(billetera1.getNumero(), billetera2.getNumero()); // Verifica que sean distintos
    }

    /**
     * test para el metodo que recarga saldo
     */
    @Test
    public void recargarSaldoTest() {
        BilleteraVirtual billetera = new BilleteraVirtual("1233291233", 10000, new Usuario("123465566",  "juan", "calle23", "juan@email.com", "12345", true));
        boolean resultado = billetera.recargarSaldo(1000);

        assertTrue(resultado);
        //assertEquals(1000, billetera.consultarSaldo());
    }

    /**
     * test para el metodo de transferir
     * @throws Exception
     */
    @Test
    public void transferirTest() throws Exception {

        Usuario usuario1 = new Usuario("1234", "Diego", "calle 15 #3-22",  "diego@email.com", "12345", true);
        Usuario usuario2 = new Usuario("12345", "Ana", "calle 17 #3-22",  "ana@email.com", "121212", true);


        BilleteraVirtual origen = new BilleteraVirtual("12345", 500, usuario1);
        BilleteraVirtual destino = new BilleteraVirtual("4567", 200, usuario2);

        boolean resultado = origen.transferir(destino, 200, Categoria.VIAJES);

        assertTrue(resultado);
        assertEquals(100, origen.consultarSaldo(usuario1));
        assertEquals(400, destino.consultarSaldo(usuario2));
    }

    /**
     * test para el metodo que consulta transacciones por periodo
     */
    @Test
    public void consultarTransaccionesPorPeriodoTest() {

        Usuario usuario1 = new Usuario("5678", "camilo", "calle 5 #4-32",  "camilo@email.com", "12345", true);
        Usuario usuario2 = new Usuario("4678", "sofia", "calle 2 #4-32",  "sofia@email.com", "123456", true);

        BilleteraVirtual billetera = new BilleteraVirtual("223344", 34523, usuario1);
        billetera.recargarSaldo(1000);
        BilleteraVirtual billeteraDesstino = new BilleteraVirtual("1111", 23234, usuario2);
        billetera.transferir(billeteraDesstino, 200, Categoria.VIAJES);

        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusDays(1);

        ArrayList<Transaccion> transacciones = billetera.consultarTransaccionesPorPeriodo(inicio, fin);

        assertFalse(transacciones.isEmpty());
    }


    /**
     * test para el metodo que obtiene porcentaje de gastos e ingresos
     */
    @Test
    public void obtenerPorcentajeGastosIngresosTest() {

        Usuario usuario1 = new Usuario("5678", "felipe", "calle 5 #4-32",  "felipe@email.com", "12345", true);
        Usuario usuario2 = new Usuario("4678", "miguel", "carrera 8 #4-32",  "miguel@email.com", "12345f", true);

        BilleteraVirtual billeteraOrigen = new BilleteraVirtual("12341234", 600, usuario1);
        BilleteraVirtual billeteraDestino = new BilleteraVirtual("123412", 700, usuario2);

        billeteraOrigen.recargarSaldo(1000);
        billeteraOrigen.transferir(billeteraDestino, 500, Categoria.VIAJES);



        billeteraOrigen.obtenerPorcentajeGastosIngresos(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());

        // No hay assert porque el método imprime los resultados en la consola.
    }

    /**
     * test para el metodo de consultar saldo
     * @throws Exception
     */
    @Test
    public void consultarSaldoTest() throws Exception {

        Usuario usuario1 = new Usuario("5678", "pedro", "calle 5 #4-23", "pedro@gmail.com", "234323", true);

        BilleteraVirtual billetera = new BilleteraVirtual("2222", 5000, usuario1 );

        assertEquals(5000, billetera.consultarSaldo(usuario1));
    }

    /**
     * test para el metodo que consulta transacciones
     * @throws Exception
     */
    @Test
    public void consultarTransaccionesTest() throws Exception {

        Usuario usuario1 = new Usuario("5678", "pedro", "calle 5 #4-23", "pedro@gmail.com", "234323", true);
        Usuario usuario2 = new Usuario("4321", "Andres", "calle 7 #4-23", "andres@gmail.com", "09876", true);

        BilleteraVirtual billetera1 = new BilleteraVirtual("2222", 5000, usuario1 );
        BilleteraVirtual billetera2 = new BilleteraVirtual("3333", 6000, usuario2 );

        billetera1.transferir(billetera2, 300, Categoria.VIAJES);
        billetera1.transferir(billetera2, 700, Categoria.GASOLINA);


        List<Transaccion> transacciones = billetera1.consultaTransaccion(usuario1);

        assertEquals(2, transacciones.size());
    }



}