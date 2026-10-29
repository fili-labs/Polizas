package polizas.Marcas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    public Connection cnx;
    public PreparedStatement ps;
    public ResultSet rs;

    public void conectar() {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
        }
    }

    void close() {
        try {
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}