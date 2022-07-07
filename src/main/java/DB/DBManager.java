package DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;



public class DBManager {

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt){

        try{
            Connection con = null;
            con = getConnection();

            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("SELECT * FROM admin");
            res = ps.executeQuery();

            if(res.next()) {
                JOptionPane.showMessageDialog(null,res.getString("id")+ " "+ res.getString("Usuario"));
            }else{
                JOptionPane.showMessageDialog(null, "No Existen Datos");
            }
            con.close();
        }catch(Exception e){
            System.out.println("error");

            }

        }

    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("bruselas.ceisufro.cl:22", "prestador", "prestador.2022");
            JOptionPane.showMessageDialog(null,"Conexion fallida");

        }catch(Exception e){
            System.out.println("error de conexion");
        }

        return con;
    }

    public static boolean checkPassword(char[] password){
        //TODO Placeholder para el check en DB
        // Por ahora no tienen lógica y están hechos para retornar True siempre

        String hashFromDB = "35bc8cec895861697a0243c1304c7789"; // Hash para "patata"

        String hashFromGUI = hasher(String.valueOf(password));
        return hashFromGUI.equals(hashFromDB);
    }


    public static String hasher(String input){
        try{
            MessageDigest msgDst = MessageDigest.getInstance("MD5");
            byte[] msgArr = msgDst.digest(input.getBytes());
            BigInteger bi = new BigInteger(1, msgArr);
            String hshtxt = bi.toString(16);
            while (hshtxt.length() < 32)
            {
                hshtxt = "0" + hshtxt;
            }
            return hshtxt;
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

    }

    public static Object[][] getHistorial() {
        return new Object[][]{{"199002430","124","Prestado","Buen estado"}};
    }

    public static Object[][] getRequests() {
        return new Object[][] {{"123456789","Esperando..."}};
    }

    public static void sendData(ArrayList<String[]> datosHistorial, ArrayList<String[]> datosEspera) {
        // TODO enviar datosHistorial y datosEspera a la BD
    }
}
