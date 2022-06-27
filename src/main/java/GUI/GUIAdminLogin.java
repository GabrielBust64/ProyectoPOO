package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Principal.EncrypterDecrypter;

public class GUIAdminLogin implements ActionListener {
    private JPanel mainPanel;
    private JPasswordField password;
    private JFrame mainFrame;
    private JButton continuar;
    private JLabel etiqueta;

    public GUIAdminLogin(){
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        password = new JPasswordField();
        continuar = new JButton("Continuar");
        etiqueta = new JLabel("");
        mainPanel.setBorder(BorderFactory.createEmptyBorder(300,300,300,300));
        mainPanel.setLayout(new GridLayout(0,1));
        continuar.addActionListener(this::actionPerformed);
        mainPanel.add(password);
        mainPanel.add(continuar);
        mainPanel.add(etiqueta);

        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setTitle("Admin Login");
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(EncrypterDecrypter.checkPassword(password.getPassword())){
            this.etiqueta.setText("Contraseña correcta");
            new GUIAdmin("");
            mainFrame.dispose();
        }else{
            this.etiqueta.setText("Contraseña incorrecta");
        }
    }
}
