import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BilleteraVirtualTest {

    @Test
    public void generarNumeroUnicoTest() {

        BilleteraVirtual billetera1 = new BilleteraVirtual("1233291233", 10000, new ArrayList<>() , new Usuario("123465566",  "juan", "calle23", "juan@email.com", "12345", true));
        BilleteraVirtual billetera2 = new BilleteraVirtual("1234568533", 23000, new ArrayList<>(),new Usuario("1234890", "camilo", "calle12", "camilo@email.com", "12334",false));

        assertNotNull(billetera1.getNumero());
        assertNotNull(billetera2.getNumero());
        assertNotEquals(billetera1.getNumero(), billetera2.getNumero()); // Verifica que sean distintos
    }

    @Test
    public void recargarSaldoTest() {
        BilleteraVirtual billetera = new BilleteraVirtual(new Usuario("Carlos"));
        boolean resultado = billetera.recargarSaldo(1000);

        assertTrue(resultado);
        assertEquals(1000, billetera.consultarSaldo());
    }

    @Test
    public void transferirTest() {
        BilleteraVirtual origen = new BilleteraVirtual(new Usuario("Ana"));
        BilleteraVirtual destino = new BilleteraVirtual(new Usuario("Luis"));

        origen.recargarSaldo(500);
        boolean resultado = origen.transferir(destino, 200, Categoria.VIAJES);

        assertTrue(resultado);
        assertEquals(300, origen.consultarSaldo());
        assertEquals(200, destino.consultarSaldo());
    }

    @Test
    public void consultarTransaccionesPorPeriodoTest() {
        BilleteraVirtual billetera = new BilleteraVirtual(new Usuario("Paula"));
        billetera.recargarSaldo(1000);
        billetera.transferir(new BilleteraVirtual(new Usuario("Diego")), 200, Categoria.FACTURAS);

        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusDays(1);

        ArrayList<Transaccion> transacciones = billetera.consultarTransaccionesPorPeriodo(inicio, fin);

        assertFalse(transacciones.isEmpty());
    }

    @Test
    public void obtenerPorcentajeGastosIngresosTest() {
        BilleteraVirtual billetera = new BilleteraVirtual(new Usuario("Sofia"));
        billetera.recargarSaldo(1000);
        billetera.transferir(new BilleteraVirtual(new Usuario("Diego")), 500, Categoria.VIAJES);

        billetera.obtenerPorcentajeGastosIngresos(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());

        // No hay assert porque el método imprime los resultados en la consola.
    }
}