import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
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
        BilleteraVirtual billetera = new BilleteraVirtual("1233291233", 10000, new ArrayList<>() , new Usuario("123465566",  "juan", "calle23", "juan@email.com", "12345", true));
        boolean resultado = billetera.recargarSaldo(1000);

        assertTrue(resultado);
        assertEquals(1000, billetera.consultarSaldo());
    }

    @Test
    public void transferirTest() {

        Usuario usuario1 = new Usuario("1234", "Diego", "calle 15 #3-22",  "diego@email.com", "12345", true);
        Usuario usuario2 = new Usuario("12345", "Ana", "calle 17 #3-22",  "ana@email.com", "121212", true);


        BilleteraVirtual origen = new BilleteraVirtual("12345", 300, new ArrayList<Transaccion>(), usuario1);
        BilleteraVirtual destino = new BilleteraVirtual("4567", 200, new ArrayList<Transaccion>(), usuario2);

        origen.recargarSaldo(500);
        boolean resultado = origen.transferir(destino, 200, Categoria.VIAJES);

        assertTrue(resultado);
        assertEquals(600, origen.consultarSaldo());
        assertEquals(400, destino.consultarSaldo());
    }

    @Test
    public void consultarTransaccionesPorPeriodoTest() {

        Usuario usuario1 = new Usuario("5678", "camilo", "calle 5 #4-32",  "camilo@email.com", "12345", true);
        Usuario usuario2 = new Usuario("4678", "sofia", "calle 2 #4-32",  "sofia@email.com", "123456", true);

        BilleteraVirtual billetera = new BilleteraVirtual("223344", 34523, new ArrayList<Transaccion>(), usuario1);
        billetera.recargarSaldo(1000);
        BilleteraVirtual billeteraDesstino = new BilleteraVirtual("1111", 23234, new ArrayList<Transaccion>(), usuario2);
        billetera.transferir(billeteraDesstino, 200, Categoria.VIAJES);

        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = LocalDateTime.now().plusDays(1);

        ArrayList<Transaccion> transacciones = billetera.consultarTransaccionesPorPeriodo(inicio, fin);

        assertFalse(transacciones.isEmpty());
    }


    @Test
    public void obtenerPorcentajeGastosIngresosTest() {

        Usuario usuario1 = new Usuario("5678", "felipe", "calle 5 #4-32",  "felipe@email.com", "12345", true);
        Usuario usuario2 = new Usuario("4678", "miguel", "carrera 8 #4-32",  "miguel@email.com", "12345f", true);

        BilleteraVirtual billeteraOrigen = new BilleteraVirtual("12341234", 600, new ArrayList<Transaccion>(), usuario1);
        BilleteraVirtual billeteraDestino = new BilleteraVirtual("123412", 700, new ArrayList<Transaccion>(), usuario2);

        billeteraOrigen.recargarSaldo(1000);
        billeteraOrigen.transferir(billeteraDestino, 500, Categoria.VIAJES);



        billeteraOrigen.obtenerPorcentajeGastosIngresos(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());

        // No hay assert porque el método imprime los resultados en la consola.
    }

    @Test
    public void sumar(){

    }
}