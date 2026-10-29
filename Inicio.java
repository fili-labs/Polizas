package polizas;

import polizas.General.Controlador;
import polizas.General.Bienvenida;
import javax.swing.SwingUtilities;

public class Inicio {
    private Bienvenida vista;
    private Controlador controlador;

    public Inicio() {
        vista = new Bienvenida();
        controlador = new Controlador(vista);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Inicio::new);
    }
}
