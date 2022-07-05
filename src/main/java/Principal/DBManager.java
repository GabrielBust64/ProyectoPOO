package Principal;

import java.util.ArrayList;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class DBManager {
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
