package Principal;

import DB.DBManager;
import GUI.GUIAdminLogin;

public class Principal {
    public static void main(String[] args) {
        new GUIAdminLogin();
        DBManager db = new DBManager();

    }
}
