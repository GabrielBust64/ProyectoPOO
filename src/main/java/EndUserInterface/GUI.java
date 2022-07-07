package EndUserInterface;

import DB.DBManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private JLabel label;
    private SpringLayout layout;
    private JButton boton;
    private JTextField texto;
    private JPanel panel;
    private JFrame frame;
    private JLabel labelIncorrecto;
    public GUI() {
        layout = new SpringLayout();
        labelIncorrecto = new JLabel("");
        boton = new JButton("Enviar");
        texto = new JTextField();
        frame = new JFrame();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,110));
        panel.setLayout(layout);
        boton.addActionListener(this);
        label = new JLabel("Ingrese su RUT");
        label.setPreferredSize(new Dimension(300,25));
        labelIncorrecto.setPreferredSize(new Dimension(200,25));

        // Add items to panel
        panel.add(boton);
        panel.add(texto);
        panel.add(label);
        panel.add(labelIncorrecto);

        // Frame Settings
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.gray);
        setPaddings();

        // Finish GUI
        frame.setTitle("User Request");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setPaddings() {
        // TextBox paddings
        layout.putConstraint(SpringLayout.WEST, texto, 10,SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.EAST, texto, -10,SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, label);

        // Label paddings
        layout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, label, 10,SpringLayout.WEST, panel);

        // Button Paddings
        layout.putConstraint(SpringLayout.SOUTH, boton, -10, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.EAST, boton, 0, SpringLayout.EAST, texto);
        layout.putConstraint(SpringLayout.NORTH, boton, 5, SpringLayout.SOUTH, texto);

        // Second Label paddings
        layout.putConstraint(SpringLayout.NORTH, labelIncorrecto, 5, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.WEST, labelIncorrecto, 0, SpringLayout.WEST, texto);


    }

    private boolean checkRut(String rut) {
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return !validacion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!checkRut(texto.getText())){
            // TODO send to DB
            if (DBManager.userHasPC(texto.getText())){
                JOptionPane.showMessageDialog(null, "Usted aún no devuelve un PC");
            }else {
                JOptionPane.showMessageDialog(null,"Petición ingresada correctamente");
                texto.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null,"RUT Incorrecto");
        }

    }
}
