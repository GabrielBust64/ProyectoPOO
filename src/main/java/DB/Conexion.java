package DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("bruselas.ceisufro.cl:22", "prestador", "Prestador.2022");
            JOptionPane.showMessageDialog(null,"Conexion fallida");

        }catch(Exception e){
            System.out.println("error de conexion");
        }

        return con;
    }
}
