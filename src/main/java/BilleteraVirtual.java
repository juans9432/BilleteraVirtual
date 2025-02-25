import java.util.ArrayList;

public class BilleteraVirtual {
    String numero;
    float saldo;
    ArrayList<Transaccion> transacciones;
    Usuario usuario;

    public BilleteraVirtual(String numero, float saldo, ArrayList<Transaccion> transacciones, Usuario usuario) {
        this.numero = numero;
        this.saldo = saldo;
        this.transacciones = new ArrayList<>();
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
     * @return
     */
    public float consultarSaldo(){
        return saldo;
    }

    /**
     * metodo para consultar las transacciones
     * @return
     */
    public ArrayList<Transaccion> consultarTransacciones(){
        return transacciones;
    }
}
