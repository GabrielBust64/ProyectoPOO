package DB;

import java.sql.*;

public class Conexion {
    Connection con;
    PreparedStatement ps;
    Statement statement;
    ResultSet res;
    public void conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://bruselas.ceisufro.cl:22/ProyectoPOO", "prestador", "Prestador.2022");
            statement = con.createStatement();
            System.out.println("conectado");
            con.close();

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error en la conexión");
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }
}