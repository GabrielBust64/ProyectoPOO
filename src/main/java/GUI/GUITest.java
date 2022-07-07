package GUI;

import DB.DBManager;

public class GUITest {
    public static void main(String[] args) {
        new GUIAdmin(DBManager.getHistorial(), "Test", DBManager.getRequests());

    }
}
