package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAdmin implements ActionListener {

    int count = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button1;
    private JButton button2;
    private JPanel panel;
    public GUIAdmin(String titulo){
        frame = new JFrame();
        panel = new JPanel();
        button1 = new JButton("Click here");
        button1.addActionListener(this::actionPerformed);
        button2 = new JButton("Don't click here");
        button2.addActionListener(this::actionPerformed2);

        label = new JLabel("Number of clicks: 0");

        
        panel.setBorder(BorderFactory.createEmptyBorder(300, 600, 300, 600));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(button1);
        panel.add(button2);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(titulo);
        frame.pack();
        frame.setVisible(true);
    }

    private void actionPerformed2(ActionEvent actionEvent) {
        count--;
        label.setText("Number of click " + count);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks " + count);
    }
}
