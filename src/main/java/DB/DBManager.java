package DB;

import GUI.GUIAdmin;
import GUI.GUIAdminLogin;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class DBManager {
    private static PreparedStatement ps;
    private static ResultSet res;
    private static ResultSetMetaData rsmd;

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
        String bdpass ="";
        try{

            Conexion con = new Conexion();

            ps=con.getCon().prepareStatement("SELECT Contraseña from admin where id = ?");
            ps.setString(1, bdpass);
            res = ps.executeQuery();
            if(res.next()){
                bdpass =(res.getString("Contraseña"));
            }else{
                JOptionPane.showMessageDialog(null,"No existe una contraseña para admin");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se puede conectar a la base de datos= ");
        }
        return bdpass.equals(String.valueOf(password));
    }

    /*
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


     */


    public static boolean userHasPC(String text) {
        String rut;
        boolean boleano=false;
        try {
            Conexion con = new Conexion();
            ps = con.getCon().prepareStatement("select pc_id from usuario where rut = ? ");
            ps.setString(1,text);
            res= ps.executeQuery();
            if(res.next()){
                rut=res.getString("rut");
                if (text.equals(rut)) {
                    boleano=true;
                } else {
                    boleano=false;
                }
            }


        }catch(SQLException e){
            System.out.println("error = "+e);
        }
        return boleano;
    }

    public static Object[][] getHistorial() {
        String rut;
        String PCID;
        String Estado;
        String notas;

        try{
            Conexion con = new Conexion();
            ps=con.getCon().prepareStatement("select usuario_rut,pc_id,estado,comentario from historial");
            res = ps.executeQuery();
            rsmd = ps.getMetaData();
            int columnas = rsmd.getColumnCount();
            while(res.next()){
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = res.getObject(i+1);
                    //TODO fixear tabla.addRow();
                    //TODO hacer tabla con el otro metodo (manual)
                }

            }
        }catch (Exception e){
            System.out.println("error "+e);
        }
        return new Object[][]{{"199002430","124","Prestado","Buen estado"}};
    }

    public static Object[][] getRequests() {
        String usuario="";
        String estado="";
        try{
            Conexion con = new Conexion();
            ps = con.getCon().prepareStatement("SELECT usuario_rut, estado from Peticiones");
            res=ps.executeQuery();
            if(res.next()){
                usuario=res.getString("usuario_rut");
                estado=res.getString("estado");
            }else{
                JOptionPane.showMessageDialog(null,"error");
            }
        }catch(SQLException e){
            System.out.println(e);
        }

        return new Object[][] {{usuario,estado}};
    }

    public static void sendData(ArrayList<String[]> datosHistorial, ArrayList<String[]> datosEspera) {
        // TODO enviar datosHistorial y datosEspera a la BD
    }
}
