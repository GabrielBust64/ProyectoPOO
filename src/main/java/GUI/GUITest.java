package GUI;

import Principal.DBManager;

public class GUITest {
    public static void main(String[] args) {
        new GUIAdmin(DBManager.getHistorial(), "Test", DBManager.getRequests());

    }
}
