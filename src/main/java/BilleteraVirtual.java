import java.time.LocalDateTime;
import java.util.ArrayList;

public class BilleteraVirtual {
    String numero;
    float saldo;
    ArrayList<Transaccion> transacciones;
    Usuario usuario;
    static ArrayList<String> numerosExistentes = new ArrayList<>(); // verificar numeros unicos

    public BilleteraVirtual(String numero, float saldo, Usuario usuario) {
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
     * @return
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
     * metodo para generar un numero de una billetera
     * @return
     */
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

    /**
     * metodo para recargar el saldo de la billetera
     * @param monto
     * @return
     */
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

    /**
     * metodo para transferir de una billetera a otra
     * @param destino
     * @param monto
     * @param categoria
     * @return
     */
    public boolean transferir(BilleteraVirtual destino, float monto, Categoria categoria) {
        boolean tieneSaldoSuficiente = false;

        for (int i = 0; i < 1; i++) {
            if (monto > 0 && saldo >= monto) {
                tieneSaldoSuficiente = true;
            }
        }

        if (tieneSaldoSuficiente) {
            saldo -= (monto + 200); //se descuenta el monto transferido más 200 del costo de la transaccion
            destino.saldo += monto;

            Transaccion envio = new Transaccion("ENVIO", monto, LocalDateTime.now(), categoria, this, destino);
            Transaccion recepcion = new Transaccion("RECEPCION", monto, LocalDateTime.now(), categoria, destino, this);

            transacciones.add(envio);
            destino.transacciones.add(recepcion);
            return true;
        }
        return false;
    }

    /**
     * metodo para consultar las transacciones por periodo
     * @param inicio
     * @param fin
     * @return
     */
    public ArrayList<Transaccion> consultarTransaccionesPorPeriodo(LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getFecha().isAfter(inicio) && transaccion.getFecha().isBefore(fin)) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    /**
     * metodo para obtener el porcentaje de gastos e ingresos
     * @param anio
     * @param mes
     */
    public void obtenerPorcentajeGastosIngresos(int anio, int mes) {
        float ingresos = 0;
        float gastos = 0;

        for (Transaccion transaccion : transacciones) {
            if (transaccion.getFecha().getYear() == anio && transaccion.getFecha().getMonthValue() == mes) {
                if (transaccion.getOrigen() == this) {
                    gastos += transaccion.getMonto();
                } else if (transaccion.getDestino() == this) {
                    ingresos += transaccion.getMonto();
                }
            }
        }

        float total = ingresos + gastos;
        if (total > 0) {
            System.out.println("Ingresos: " + (ingresos / total) * 100 + "%");
            System.out.println("Gastos: " + (gastos / total) * 100 + "%");
        } else {
            System.out.println("No hay transacciones registradas en este período.");
        }
    }
}
