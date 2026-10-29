package polizas.Marcas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class MarcasModelo extends Conexion implements DAO {

    MarcasModelo() {
        super();
    }

    @Override
    public List read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT * FROM marca");
            rs = ps.executeQuery();
            lista.clear();

            while (rs.next()) {
                lista.add(new Marca(rs.getInt("idMarca"), rs.getString("nombreMarca")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        int filas = 0;
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("INSERT INTO marca(nombreMarca) VALUES (?)");
            ps.setString(1, marca.getNombre());
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return filas;
    }

    @Override
    public int update(Object obj) {
        int filas = 0;
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("UPDATE marca SET nombreMarca = ? WHERE idMarca = ?");
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getId());
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return filas;
    }

    @Override
    public int delete(Object obj) {
        int filas = 0;
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("DELETE FROM marca WHERE idMarca = ?");
            ps.setInt(1, marca.getId());
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return filas;
    }
}
