package co.edu.upb.Entrega;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import co.edu.upb.Operador.Cita;

public class VistaEntrega extends JFrame {
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    List<Cita> citasActuales;

    public VistaEntrega(String usuario) {
        setTitle("ENTREGA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Nombre del Estudiante");
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Semestre");

        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        add(scrollPane, BorderLayout.CENTER);

        JButton botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarInformacion();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonRefrescar);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public List<Cita> cargarCitasDesdeJSON(String rutaArchivoJSON) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Jean\\Desktop\\ESTRUCTURA DE DATOS PROYECTO\\uservalidation\\citas.json");

            Gson gson = new Gson();

            java.lang.reflect.Type citaListType = new TypeToken<List<Cita>>() {
            }.getType();

            List<Cita> citas = gson.fromJson(fileReader, citaListType);

            if (citas != null) {
                for (Cita cita : citas) {
                    System.out.println("ID: " + cita.getId());
                }
            }

            return citas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void refrescarInformacion() {
        String rutaArchivoJSON = "C:\\Users\\Jean\\Desktop\\ESTRUCTURA DE DATOS PROYECTO\\uservalidation\\citas.json"; 
        List<Cita> citas = cargarCitasDesdeJSON(rutaArchivoJSON);
    
        if (citas != null) {
            citasActuales = citas;
            initTable(citasActuales);
        }
    }

    private void initTable(List<Cita> citasActuales) {
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        model.setRowCount(0);
    
        for (Cita cita : citasActuales) {
            Object[] row = {
                cita.getFecha(),
                cita.getApellidosEstudiantes(),
                cita.getId(),
                cita.getNumeroSemestre()
            };
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaEntrega app = new VistaEntrega("usuario");
            app.setVisible(true);
        });
    }
}

