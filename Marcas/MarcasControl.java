package polizas.Marcas;

import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {

    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();

        marcasEventos();
    }

    public void marcasEventos() {

        // CONSULTA
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        // AGREGA
        mv.btn_a.addActionListener(e -> {
            Marca marca = new Marca(0, mv.txt1.getText());
            int r = mm.create(marca);
            JOptionPane.showMessageDialog(null, r > 0 ? "Marca agregada" : "No se pudo agregar");
            mv.actualizarVista(mm.read());
        });

        // MODIFICA
        mv.btn_m.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una marca de la tabla");
                return;
            }
            int id = mv.marcasModelo.getMarca(fila).getId();
            Marca marca = new Marca(id, mv.txt1.getText());
            int r = mm.update(marca);
            JOptionPane.showMessageDialog(null, r > 0 ? "Marca actualizada" : "No se pudo actualizar");
            mv.actualizarVista(mm.read());
        });

        // ELIMINA
        mv.btn_e.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una marca de la tabla");
                return;
            }
            Marca marca = mv.marcasModelo.getMarca(fila);
            int confirmar = JOptionPane.showConfirmDialog(null,
                    "¿Eliminar la marca " + marca.getNombre() + "?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }
            int r = mm.delete(marca);
            JOptionPane.showMessageDialog(null, r > 0 ? "Marca eliminada" : "No se pudo eliminar");
            mv.actualizarVista(mm.read());
        });

        mv.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txt1.setText((String) mv.tabla.getValueAt(fila, 1));
                }
            }
        });

        mv.cmbMarcas.addActionListener(e -> {
            Marca marca = (Marca) mv.cmbMarcas.getSelectedItem();
        });
    }
}