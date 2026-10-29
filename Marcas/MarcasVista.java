package polizas.Marcas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MarcasVista extends JPanel {

    protected JButton btn1, btn_a, btn_m, btn_e;
    protected JTextField txt1;
    protected JLabel lbl1, lbl2;

    protected JTable tabla;
    protected MarcasTM marcasModelo;
    protected String[] columnas = {"Id", "Nombre"};

    protected JComboBox cmbMarcas;

    public MarcasVista() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
        norte.setOpaque(false);

        lbl1 = new JLabel("Welcome to Marcas Admin.");
        lbl1.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel filaTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        filaTitulo.setOpaque(false);
        filaTitulo.add(lbl1);

        lbl2 = new JLabel("Nombre de la marca:");
        txt1 = new JTextField("Marca 1", 20);
        cmbMarcas = new JComboBox();
        cmbMarcas.setPreferredSize(new Dimension(200, 28));
        JPanel filaCampos = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        filaCampos.setOpaque(false);
        filaCampos.add(lbl2);
        filaCampos.add(txt1);
        filaCampos.add(cmbMarcas);

        btn1 = new JButton("Consultar");
        btn_a = new JButton("Agregar");
        btn_m = new JButton("Modificar");
        btn_e = new JButton("Eliminar");
        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        filaBotones.setOpaque(false);
        filaBotones.add(btn1);
        filaBotones.add(btn_a);
        filaBotones.add(btn_m);
        filaBotones.add(btn_e);

        norte.add(filaTitulo);
        norte.add(filaCampos);
        norte.add(filaBotones);

        marcasModelo = new MarcasTM();
        tabla = new JTable(marcasModelo);
        JScrollPane sc = new JScrollPane(tabla);

        add(norte, BorderLayout.NORTH);
        add(sc, BorderLayout.CENTER);
    }

    public void actualizarVista(List<Marca> lista) {
        marcasModelo.vaciarLista();
        marcasModelo.fireTableDataChanged();
        marcasModelo.llenar(lista);
        marcasModelo.fireTableDataChanged();

        cmbMarcas.removeAllItems();
        for (Marca m : lista) {
            cmbMarcas.addItem(m);
        }
    }
}