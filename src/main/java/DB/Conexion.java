package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection connection = null;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            System.err.println("Error");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://bruselas.ceisufro.cl/ProyectoPOO", "prestador", "Prestador.2022");
            System.out.println("conectado");
        } catch (SQLException e) {
            System.err.println("Error en la conexión");
            System.err.println("SQLException: " + e.getMessage());
        }

        return connection;
    }
}
