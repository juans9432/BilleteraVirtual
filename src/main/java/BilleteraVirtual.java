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
    private static ArrayList<Integer> usuariosRegistrados = new ArrayList<>();

    // Método para generar un número de billetera único
    public static int generarNumeroBilletera() {
        int numeroBilletera;

        for (int i = 0; i < 1000; i++) {
            numeroBilletera = (int) (Math.random() * 1000000000); // Genera un número de 9 dígitos
            boolean existe = false;

            for (int usuario : usuariosRegistrados) {
                if (usuario == numeroBilletera) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                usuariosRegistrados.add(numeroBilletera);
                saldo.add(0.0);
                return numeroBilletera;
            }
        }
        return -1;

    // Método para recargar la billetera virtual
    public static boolean recargarBilletera(int numeroBilletera, double monto) {
        if (monto > 0) {
            for (int i = 0; i < usuariosRegistrados.size(); i++) {
                if (usuariosRegistrados.get(i) == numeroBilletera) {
                    saldo.set(i, saldo.get(i) + monto);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Agregamos usuarios registrados de ejemplo
        usuariosRegistrados.add(123456789);
        usuariosRegistrados.add(987654321);
        usuariosRegistrados.add(456123789);
        saldos.add(50.0);
        saldos.add(75.0);
        saldos.add(100.0);

        // Generamos una nueva billetera
        int nuevaBilletera = generarNumeroBilletera();

        if (nuevaBilletera != -1) {
            System.out.println("Número de billetera generado: " + nuevaBilletera);
        } else {
            System.out.println("No se pudo generar un número de billetera único.");
        }

        // Probamos recargar la billetera
        boolean recargaExitosa = recargarBilletera(nuevaBilletera, 200.0);

        if (recargaExitosa) {
            int index = usuariosRegistrados.indexOf(nuevaBilletera);
            System.out.println("Recarga exitosa. Nuevo saldo: " + saldo.get(index));
        } else {
            System.out.println("Error en la recarga.");
        }
    }

}



