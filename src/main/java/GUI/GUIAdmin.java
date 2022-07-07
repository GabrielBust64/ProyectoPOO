package GUI;

import DB.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GUIAdmin implements ActionListener {
    private JComboBox dropHistorial;
    private JComboBox dropEspera;
    private JScrollPane scrollHistorial;
    private JScrollPane scrollEspera;
    private JLabel labelTabla;
    private JLabel labelCantidad;
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
    private JButton cambiarPass;
    public GUIAdmin() {
        cambiarPass = new JButton("Cambiar Contraseña");
        cambiarPass.addActionListener(this::actionPerformedCambiarPass);
        layout = new SpringLayout();
        dropEspera = new JComboBox();
        dropHistorial = new JComboBox();
        frame = new JFrame();
        panel = new JPanel();
        button2 = new JButton();
        button1 = new JButton();
        labelTabla = new JLabel("Historial");
        labelTabla.setForeground(Color.white);
        labelCantidad = new JLabel("PCs disponibles: " + DBManager.getPCQuantity());
        labelCantidad.setPreferredSize(new Dimension(200,25));
        labelCantidad.setForeground(Color.white);
        labelRequests = new JLabel("Espera");
        labelRequests.setForeground(Color.white);
        modeloHistorial = new DefaultTableModel(new Object[]{"RUT","PCID","Estado","Notas"},0);
        tablaHistorial = new JTable(modeloHistorial);
        modeloEspera = new DefaultTableModel(new Object[]{"RUT","Estado"},0);
        tablaEspera = new JTable(modeloEspera);

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
        panel.add(cambiarPass);
        panel.add(labelCantidad);
        panel.setPreferredSize(frame.getPreferredSize());
        panel.setBackground(Color.darkGray);
        setupLayout();
        button1.addActionListener(this::actionPerformed);
        button2.addActionListener(this::actionPerformed2);
        scrollHistorial.setPreferredSize(new Dimension(1300,930));
        scrollEspera.setPreferredSize(new Dimension(150,930));

        frame.setTitle("Admin");
        frame.pack();
        frame.setVisible(true);
        recibirDatos(modeloHistorial,modeloEspera);
    }




    private void setupLayout() {
        // Label de Historial
        layout.putConstraint(SpringLayout.NORTH, labelTabla,10,SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, labelTabla,10,SpringLayout.WEST, panel);
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
        // Botón cambiar contraseña
        layout.putConstraint(SpringLayout.NORTH, cambiarPass, 0,SpringLayout.NORTH, button1);
        layout.putConstraint(SpringLayout.SOUTH, cambiarPass,-10, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.WEST,cambiarPass,10,SpringLayout.WEST,panel);
        // LabelCantidad
        layout.putConstraint(SpringLayout.EAST,labelCantidad, 5,SpringLayout.WEST,button2);
        layout.putConstraint(SpringLayout.NORTH,labelCantidad,0,SpringLayout.NORTH,button2);
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
    private void actionPerformedCambiarPass(ActionEvent actionEvent) {
        new changePass();
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

class changePass implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JPasswordField passwordField;
    private JPasswordField passwordField2;
    private JButton continuar;
    private JButton cancelar;
    private JLabel lastPass;
    private JLabel nuevaPass;
    private SpringLayout layout;

    changePass(){
        frame = new JFrame();
        panel= new JPanel();
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(280,25));
        passwordField2 = new JPasswordField();
        passwordField2.setPreferredSize(new Dimension(280,25));
        continuar = new JButton("Continuar");
        continuar.addActionListener(this::actionPerformed);
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this::actionPerformedCancelar);
        lastPass = new JLabel("Ingrese contraseña actual");
        lastPass.setForeground(Color.white);
        nuevaPass = new JLabel("Ingrese nueva contraseña");
        nuevaPass.setForeground(Color.white);
        layout = new SpringLayout();

        // Frame Settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300,200));
        frame.add(panel, BorderLayout.CENTER);


        // Panel settings
        panel.add(passwordField);
        panel.add(passwordField2);
        panel.add(cancelar);
        panel.add(continuar);
        panel.add(lastPass);
        panel.add(nuevaPass);
        panel.setLayout(layout);
        panel.setPreferredSize(frame.getPreferredSize());


        setupLayout();
        frame.setTitle("Cambiar Contraseña");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void setupLayout() {
        // LastPass label
        layout.putConstraint(SpringLayout.NORTH,lastPass,10,SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST,lastPass,10,SpringLayout.WEST,panel);
        layout.putConstraint(SpringLayout.EAST,lastPass,-10,SpringLayout.EAST,panel);

        // PasswordField
        layout.putConstraint(SpringLayout.NORTH,passwordField,5,SpringLayout.SOUTH,lastPass);
        layout.putConstraint(SpringLayout.WEST,passwordField,0,SpringLayout.WEST,lastPass);
        layout.putConstraint(SpringLayout.EAST,passwordField,-10,SpringLayout.EAST,panel);

        // NewPass label
        layout.putConstraint(SpringLayout.NORTH, nuevaPass,5,SpringLayout.SOUTH, passwordField);
        layout.putConstraint(SpringLayout.WEST, nuevaPass,0,SpringLayout.WEST,passwordField);
        layout.putConstraint(SpringLayout.EAST,nuevaPass,-10,SpringLayout.EAST,panel);

        // PasswordField2
        layout.putConstraint(SpringLayout.NORTH,passwordField2,5,SpringLayout.SOUTH,nuevaPass);
        layout.putConstraint(SpringLayout.WEST,passwordField2,0,SpringLayout.WEST,nuevaPass);
        layout.putConstraint(SpringLayout.EAST,passwordField2,-10,SpringLayout.EAST,panel);

        // Continuar botón
        layout.putConstraint(SpringLayout.EAST,continuar,0,SpringLayout.EAST,passwordField2);
        layout.putConstraint(SpringLayout.NORTH,continuar,5,SpringLayout.SOUTH,passwordField2);
        layout.putConstraint(SpringLayout.SOUTH,continuar,-10,SpringLayout.SOUTH,panel);

        // Cancelar botón
        layout.putConstraint(SpringLayout.NORTH,cancelar,0,SpringLayout.NORTH,continuar);
        layout.putConstraint(SpringLayout.EAST,cancelar,-5,SpringLayout.WEST,continuar);
        layout.putConstraint(SpringLayout.SOUTH,cancelar,-10,SpringLayout.SOUTH,panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (DBManager.checkPassword(passwordField.getPassword())){
            DBManager.setPassword(passwordField2.getPassword());
            frame.dispose();
        }else{
            lastPass.setText("Contraseña Incorrecta");
        }


    }
    public void actionPerformedCancelar(ActionEvent e){
        frame.dispose();
    }
}
