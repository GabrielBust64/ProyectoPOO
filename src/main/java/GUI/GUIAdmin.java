package GUI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAdmin implements ActionListener {
    private JComboBox dropHistorial;
    private JComboBox dropEspera;
    private JScrollPane scrollHistorial;
    private JScrollPane scrollEspera;
    private JLabel labelTabla;
    private JTable tablaHistorial;
    private JTable tablaEspera;
    private JFrame frame;
    private JButton button1;
    private JButton button2;
    private JPanel panel;
    private SpringLayout layout;
    private JLabel labelRequests;
    public GUIAdmin(Object[][] historial, String titulo, Object[][] requests) {
        layout = new SpringLayout();
        dropEspera = new JComboBox();
        dropHistorial = new JComboBox();
        frame = new JFrame();
        panel = new JPanel();
        button2 = new JButton();
        button1 = new JButton();
        labelTabla = new JLabel("Historial");
        labelRequests = new JLabel("Espera");
        tablaHistorial = new JTable(historial, new Object[]{"RUT","PCID","Estado","Notas"});
        tablaEspera = new JTable(requests, new Object[]{"RUT","Estado"});
        scrollEspera = new JScrollPane(tablaEspera);
        scrollHistorial = new JScrollPane(tablaHistorial);
        // ComboBox settings
        dropEspera.addItem("Prestado");
        dropEspera.addItem("Denegado");
        TableColumn columnaComboEspera = tablaEspera.getColumnModel().getColumn(1);
        columnaComboEspera.setCellEditor(new DefaultCellEditor(dropEspera));
        dropHistorial.addItem("Recibido");
        TableColumn columnaComboHistorial = tablaHistorial.getColumnModel().getColumn(2);
        columnaComboHistorial.setCellEditor(new DefaultCellEditor(dropHistorial));

        // Frame settings
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        // Panel settings
        panel.setLayout(layout);
        button1.setText("Enviar estado");
        button2.setText("Recibir estado");
        panel.add(labelTabla);
        panel.add(labelRequests);
        panel.add(scrollHistorial);
        panel.add(scrollEspera);
        panel.add(button1);
        panel.add(button2);
        panel.setPreferredSize(frame.getPreferredSize());
        panel.setBackground(Color.gray);
        setupLayout();
        scrollHistorial.setPreferredSize(new Dimension(1300,930));
        scrollEspera.setPreferredSize(new Dimension(150,930));



        frame.setTitle(titulo);
        frame.pack();
        frame.setVisible(true);
    }



    private void setupLayout() {
        // Label de Historial
        layout.putConstraint(SpringLayout.NORTH, labelTabla,10,SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, labelTabla,10,SpringLayout.NORTH, panel);
        // Label de Espera
        layout.putConstraint(SpringLayout.NORTH, labelRequests, 10, SpringLayout.NORTH,panel);
        layout.putConstraint(SpringLayout.WEST, labelRequests, 10, SpringLayout.EAST,scrollHistorial);
        layout.putConstraint(SpringLayout.NORTH, labelRequests, 0, SpringLayout.NORTH,labelTabla);
        // Tabla de Espera
        layout.putConstraint(SpringLayout.NORTH, scrollEspera, 10, SpringLayout.NORTH,labelTabla);
        layout.putConstraint(SpringLayout.WEST, scrollEspera, 10, SpringLayout.EAST, scrollHistorial);
        layout.putConstraint(SpringLayout.NORTH, scrollEspera, 5, SpringLayout.SOUTH, labelRequests);
        layout.putConstraint(SpringLayout.EAST, scrollEspera, -10,SpringLayout.EAST, panel);
        // Tabla de Historial
        layout.putConstraint(SpringLayout.NORTH, scrollHistorial, 5, SpringLayout.SOUTH,labelTabla);
        layout.putConstraint(SpringLayout.WEST, scrollHistorial,10,SpringLayout.WEST,panel);
        // Botón Enviar Estado
        layout.putConstraint(SpringLayout.SOUTH, button1,-10,SpringLayout.SOUTH,panel);
        layout.putConstraint(SpringLayout.NORTH, button1,10,SpringLayout.SOUTH, scrollEspera);
        layout.putConstraint(SpringLayout.EAST, button1, 0, SpringLayout.EAST, scrollEspera);
        // Botón Recibir Estado
        layout.putConstraint(SpringLayout.NORTH, button2, 0, SpringLayout.NORTH, button1);
        layout.putConstraint(SpringLayout.EAST, button2, -10, SpringLayout.WEST, button1);
        layout.putConstraint(SpringLayout.SOUTH, button2,-10,SpringLayout.SOUTH,panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
