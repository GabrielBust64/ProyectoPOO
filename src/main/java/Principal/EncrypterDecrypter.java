package Principal;

public class EncrypterDecrypter {
    public static boolean checkPassword(char[] password){
        //TODO Placeholder para el check en DB
        // Por ahora no tienen lógica y están hechos para retornar True siempre

        String hashFromDB = hasher("pato");

        String hashFromGUI = hasher(String.valueOf(password));
        return hashFromGUI.equals(hashFromDB);
    }

    public static String hasher(String s){
        //TODO Hacer un algoritmo Hash
        return "pato";
    }
}
