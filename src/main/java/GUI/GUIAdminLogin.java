package GUI;

import DB.DBManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAdminLogin implements ActionListener {
    private JPanel mainPanel;
    private JPasswordField password;
    private JFrame mainFrame;
    private JButton continuar;
    private JLabel etiqueta;
    private SpringLayout layout;

    public GUIAdminLogin(){
        layout = new SpringLayout();
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        password = new JPasswordField();
        continuar = new JButton("Continuar");
        etiqueta = new JLabel("Ingrese Contraseña de Administrador");
        etiqueta.setPreferredSize(new Dimension(300,25));
        mainPanel.setPreferredSize(new Dimension(300,120));
        mainPanel.setLayout(layout);
        continuar.addActionListener(this);
        mainPanel.add(password);
        password.setPreferredSize(new Dimension(280,25));
        mainPanel.add(continuar);
        mainPanel.add(etiqueta);

        // Frame settings
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setBackground(Color.gray);
        setPaddings();


        mainFrame.setTitle("Admin Login");
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void setPaddings() {
        layout.putConstraint(SpringLayout.NORTH, password, 5, SpringLayout.SOUTH, etiqueta);
        layout.putConstraint(SpringLayout.WEST, password, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, etiqueta);

        layout.putConstraint(SpringLayout.NORTH, continuar, 5, SpringLayout.SOUTH, password);
        layout.putConstraint(SpringLayout.EAST, continuar, 0, SpringLayout.EAST, password);

        layout.putConstraint(SpringLayout.WEST, etiqueta, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, etiqueta, 10, SpringLayout.NORTH, mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Buscar contraseña en DB
        if(DBManager.checkPassword(this.password.getPassword())){
            this.etiqueta.setText("Contraseña correcta");
            // new GUIAdmin(DBManager.getHistorial(),"Admin", DBManager.getRequests());
            new GUIAdmin(new Object[][]{{"199002430","patata","roto","brigido"}},"Admin", DBManager.getRequests());
            mainFrame.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Contraseña Incorrecta");
        }
    }
}
