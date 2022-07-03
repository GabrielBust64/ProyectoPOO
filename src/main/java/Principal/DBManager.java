package Principal;

import javax.management.ObjectName;
import java.util.ArrayList;

public class DBManager {
    public static boolean checkPassword(char[] password){
        //TODO Placeholder para el check en DB
        // Por ahora no tienen lógica y están hechos para retornar True siempre

        String hashFromDB = hasher("patata");

        String hashFromGUI = hasher(String.valueOf(password));
        return hashFromGUI.equals(hashFromDB);
    }

    public static String hasher(String s){
        // TODO Hacer un algoritmo Hash
        return s;
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
