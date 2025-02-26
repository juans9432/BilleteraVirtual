import java.time.LocalDateTime;
import java.util.ArrayList;

public class BilleteraVirtual {
    String numero;
    float saldo;
    ArrayList<Transaccion> transacciones;
    Usuario usuario;
    static ArrayList<String> numerosExistentes = new ArrayList<>(); // verificar numeros unicos

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
    private String generarNumeroUnico() {
        String nuevoNumero = "nuevoNumero";
        boolean unico = false;

        for (int i = 0; !unico; i++) {
            nuevoNumero = String.valueOf(1000000000L + (long) (Math.random() * 9000000000L)); // Número de 10 dígitos
            unico = true;

            for (String numeroExistente : numerosExistentes) {
                if (numeroExistente.equals(nuevoNumero)) {
                    unico = false;
                    break;
                }
            }
        }
        numerosExistentes.add(nuevoNumero);
        return nuevoNumero;
    }
    public boolean recargarSaldo(float monto) {
        boolean esPositivo = false;

        for (int i = 0; i < 1; i++) {
            if (monto > 0) {
                esPositivo = true;
            }
        }

        if (esPositivo) {
            saldo += monto;
            transacciones.add(new Transaccion("RECARGA", monto, LocalDateTime.now(), Categoria.VIAJES, null, this));
            return true;
        }
        return false;
    }
    public boolean transferir(BilleteraVirtual destino, float monto, Categoria categoria) {
        boolean tieneSaldoSuficiente = false;

        for (int i = 0; i < 1; i++) {
            if (monto > 0 && saldo >= monto) {
                tieneSaldoSuficiente = true;
            }
        }

        if (tieneSaldoSuficiente) {
            saldo -= monto;
            destino.saldo += monto;

            Transaccion envio = new Transaccion("ENVIO", monto, LocalDateTime.now(), categoria, this, destino);
            Transaccion recepcion = new Transaccion("RECEPCION", monto, LocalDateTime.now(), categoria, destino, this);

            transacciones.add(envio);
            destino.transacciones.add(recepcion);
            return true;
        }
        return false;
    }

    public float consultarSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> consultarTransacciones() {
        return transacciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
