package GUI;

import Principal.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private DefaultTableModel modeloHistorial;
    private DefaultTableModel modeloEspera;
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
        modeloHistorial = new DefaultTableModel(new Object[]{"RUT","PCID","Estado","Notas"},0);
        tablaHistorial = new JTable(modeloHistorial);
        modeloEspera = new DefaultTableModel(new Object[]{"RUT","Estado"},0);
        tablaEspera = new JTable(modeloEspera);
        recibirDatos(modeloHistorial,modeloEspera);
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
        button1.addActionListener(this::actionPerformed);
        button2.addActionListener(this::actionPerformed2);
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
    public void actionPerformed(ActionEvent enviar) {
        ArrayList<String[]> datosHistorial = new ArrayList<>();
        ArrayList<String[]> datosEspera = new ArrayList<>();
        for (int i = 0; i < tablaHistorial.getRowCount(); i++) {
            datosHistorial.add(getRow(tablaHistorial, i));
        }
        for (int i = 0; i < tablaEspera.getRowCount(); i++) {
            datosEspera.add(getRow(tablaEspera, i));
        }
        DBManager.sendData(datosHistorial, datosEspera);
    }



    public void actionPerformed2(ActionEvent recibir) {
        recibirDatos(modeloHistorial, modeloEspera);
    }

    private void recibirDatos(DefaultTableModel modeloHistorial, DefaultTableModel modeloEspera) {
        for (int i = 0; i < modeloEspera.getRowCount(); i++) {
            modeloEspera.removeRow(i);
        }
        for (int i = 0; i < modeloHistorial.getRowCount(); i++) {
            modeloHistorial.removeRow(i);
        }
        for (int i = 0; i < DBManager.getHistorial().length; i++) {
            modeloHistorial.addRow(DBManager.getHistorial()[i]);
        }
        for (int i = 0; i < DBManager.getRequests().length; i++) {
            modeloEspera.addRow(DBManager.getRequests()[i]);
        }
        modeloEspera.fireTableDataChanged();
        modeloHistorial.fireTableDataChanged();
    }

    private String[] getRow(JTable tabla, int i) {
        String[] resultado = new String[tabla.getColumnCount()];
        for (int j = 0; j < resultado.length; j++) {
            resultado[j] = (String) tabla.getModel().getValueAt(i,j);
        }
        return resultado;
    }

}
