package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAdmin implements ActionListener {

    int count = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    public GUIAdmin(String titulo){
        frame = new JFrame();
        panel = new JPanel();
        button = new JButton("Click here");
        button.addActionListener(this::actionPerformed);

        label = new JLabel("Number of clicks: 0");

        
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(titulo);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks " + count);
    }
}
