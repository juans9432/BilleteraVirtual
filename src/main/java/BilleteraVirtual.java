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

    // metodo para generar un numero de billetera unico
    public static int generarNumeroBilletera() {
        int numeroBilletera;

        for (int i = 0; i < 1000; i++) {
            numeroBilletera = (int) (Math.random() * 1000000000); // genera un numero de 9 digitos
            if (!usuariosRegistrados.contains(numeroBilletera)) {
                usuariosRegistrados.add(numeroBilletera); //
                return numeroBilletera; //
            }
        }

        return -1; // Si no se encuentra un número único después de 1000 intentos
    }

    public static void main(String[] args) {
        usuariosRegistrados.add(123456789);
        usuariosRegistrados.add(987654321);
        usuariosRegistrados.add(456123789);


        int nuevaBilletera = generarNumeroBilletera();

        if (nuevaBilletera != -1) {
            System.out.println("Número de billetera generado: " + nuevaBilletera);
        } else {
            System.out.println("No se pudo generar un número de billetera único.");
        }
    }
}



