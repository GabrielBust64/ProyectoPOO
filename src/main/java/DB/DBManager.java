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
    private static PreparedStatement ps;
    private ResultSet res;
    public static void setPassword(char[] password) {
        System.out.printf(String.valueOf(password));
    }

    public static String getPCQuantity() {
        int cantidad = 69;
        return String.valueOf(cantidad);
    }

    public static void sendRequest(String text) {
    }





    public static boolean checkPassword(char[] password){
        //TODO Placeholder para el check en DB
        // Por ahora no tienen lógica y están hechos para retornar True siempre



        String hashFromDB = "35bc8cec895861697a0243c1304c7789"; // Hash para "patata"

        String hashFromGUI = hasher(String.valueOf(password));
        try{
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Contraseña from admin where id = ?");
            ps.setString(hashFromDB);

        }catch(Exception e){

        }
        return hashFromGUI.equals(hashFromDB);  //
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
        return new Object[][] {{"123456789","En espera"}};
    }

    public static void sendData(ArrayList<String[]> datosHistorial, ArrayList<String[]> datosEspera) {
        // TODO enviar datosHistorial y datosEspera a la BD
    }
}
