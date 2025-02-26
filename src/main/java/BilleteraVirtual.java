import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BilleteraVirtual {
    String numero;
    float saldo;
    ArrayList<Transaccion> transacciones;
    Usuario usuario;

    public BilleteraVirtual(String numero, float saldo, ArrayList<Transaccion> transacciones, Usuario usuario) {
        this.numero = numero;
        this.saldo = saldo;
        this.transacciones = transacciones;
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * metodo para consultar el saldo
     *
     * @return
     */
    public float consultarSaldo(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("el usuario no puede ser nulo");
        }
        if (usuario.getId().equals(this.usuario.getId()) && usuario.getPassword().equals(this.usuario.getPassword())) {
            return saldo;
        } else {
            throw new Exception("credenciales invalidas");
        }
    }

    /**
     * metodo para consultar las transacciones
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    public ArrayList<Transaccion> consultaTransacción(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("el usuario no puede ser nulo");
        }
        if (usuario.getId().equals(this.usuario.getId()) && usuario.getPassword().equals(this.usuario.getPassword())) {
            return transacciones;
        } else {
            throw new Exception("credenciales invalidas");
        }
    }

    /**
     * metodo para realizar una transacción
     *
     * @param billeteraDestino
     * @throws Exception
     */
    public void realizarTransaccion(BilleteraVirtual billeteraDestino, Transaccion transaccion) throws Exception {

        if (saldo < transaccion.getMonto()) {
            throw new Exception("fondos insuficientes");
        }

        if (billeteraDestino == null || transaccion.getMonto() == 0) {
            throw new Exception("datos invalidos");
        }

        this.saldo -= (transaccion.getMonto() + 200); // el monto a enviar más 200 que es el costo de la transferencia
        billeteraDestino.saldo += transaccion.getMonto();
    }
}
