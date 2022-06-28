package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIAdmin implements ActionListener {

    int count = 0;
    private JLabel label;
    private JTable tabla;
    private JFrame frame;
    private JButton button1;
    private JButton button2;
    private JPanel panel;
    public GUIAdmin(String titulo) {
        frame = new JFrame();
        panel = new JPanel();
        button2 = new JButton();
        button1 = new JButton();
        label = new JLabel();
        tabla = new JTable();

        panel.setBorder(BorderFactory.createEmptyBorder(300,600,300,600));
        panel.setLayout(new BorderLayout());
        panel.add(button2);
        panel.add(button1);
        panel.add(tabla);
        panel.add(label);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Admin");
        frame.pack();
        frame.setVisible(true);
    }

    public void populateTable(ArrayList<String> contents){
        for (int i = 0; i < contents.size(); i++) {
            for (int j = 0; j < 4; j++) {

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
