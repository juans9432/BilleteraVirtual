import java.util.ArrayList;

public class Banco {
    ArrayList<Usuario> usuarios;
    ArrayList<BilleteraVirtual> billeteras;

    /**
     * constructor de la clase
     * @param usuarios
     * @param billeteras
     */
    public Banco(ArrayList<Usuario> usuarios, ArrayList<BilleteraVirtual> billeteras) {
        this.usuarios = usuarios;
        this.billeteras = billeteras;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<BilleteraVirtual> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<BilleteraVirtual> billeteras) {
        this.billeteras = billeteras;
    }
}
