package co.edu.upb.Operador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class VistaOperador extends JFrame {

    private JPanel panelCards;
    private CardLayout cardLayout;
    private Cita cita;
    private JTextArea areaCitas;
    private JTextField campoCancelarID;

    public VistaOperador(String usuario) {
        setTitle("OPERADOR");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        cita = new Cita();

        // Crear el panel que contendrá los distintos paneles
        panelCards = new JPanel();
        cardLayout = new CardLayout();
        panelCards.setLayout(cardLayout);

        // Agregar los paneles de agendamiento, visualización y cancelación
        JPanel panelAgendamiento = crearPanelAgendamiento();
        JPanel panelCitas = crearPanelCitas();
        JPanel panelCancelar = crearPanelCancelar();

        // Agregar los paneles al contenedor de cards
        panelCards.add(panelAgendamiento, "Agendamiento");
        panelCards.add(panelCitas, "Citas");
        panelCards.add(panelCancelar, "Cancelar");

        // Agregar el contenedor de cards al marco principal
        add(panelCards);

        // Botones para alternar entre los paneles
        JButton botonAgendamiento = new JButton("Agendar Cita");
        JButton botonCitas = new JButton("Ver Citas");
        JButton botonCancelar = new JButton("Cancelar Cita");

        // Agregar los botones y definir sus acciones
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAgendamiento);
        panelBotones.add(botonCitas);
        panelBotones.add(botonCancelar);
        add(panelBotones, BorderLayout.NORTH);

        // Acción para el botón Agendar Cita
        botonAgendamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCards, "Agendamiento");
            }
        });

        // Acción para el botón Ver Citas
        botonCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCards, "Citas");
                // Cargar y mostrar las citas existentes
                cargarCitasDesdeJSON();
            }
        });

        // Acción para el botón Cancelar Cita
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCards, "Cancelar");
                // Cargar y mostrar las citas existentes
                cargarCitasDesdeJSON();
            }
        });
    }

    private JPanel crearPanelAgendamiento() {
        // Implementa el panel de agendamiento aquí
        // Debe contener componentes para ingresar información de citas y un botón para
        // agendar
        JPanel panelAgendamiento = new JPanel();

        // Componentes de agendamiento
        JTextField campoSemestre = new JTextField(5); // Campo para el número de semestre
        JTextField campoNombre = new JTextField(20);
        JTextField campoApellidos = new JTextField(20);
        JTextField campoId = new JTextField(10);
        JTextField campoFecha = new JTextField(10);
        JTextField campoHora = new JTextField(10);
        JButton botonAgendar = new JButton("Agendar");

        // Agregar componentes al panel de agendamiento
        panelAgendamiento.add(new JLabel("Número de Semestre:"));
        panelAgendamiento.add(campoSemestre);
        panelAgendamiento.add(new JLabel("Nombres del Estudiante:"));
        panelAgendamiento.add(campoNombre);
        panelAgendamiento.add(new JLabel("Apellidos del Estudiante:"));
        panelAgendamiento.add(campoApellidos);
        panelAgendamiento.add(new JLabel("ID:"));
        panelAgendamiento.add(campoId);
        panelAgendamiento.add(new JLabel("Fecha:"));
        panelAgendamiento.add(campoFecha);
        panelAgendamiento.add(new JLabel("Hora:"));
        panelAgendamiento.add(campoHora);
        panelAgendamiento.add(botonAgendar);

        // Acción para el botón Agendar
        botonAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener datos del formulario de agendamiento
                int semestre = Integer.parseInt(campoSemestre.getText());
                String nombres = campoNombre.getText();
                String apellidos = campoApellidos.getText();
                int id = Integer.parseInt(campoId.getText());
                String fecha = campoFecha.getText();
                String hora = campoHora.getText();
                Estudiante nuevaCita = new Estudiante(semestre, nombres, apellidos, id, fecha, hora);
                cita.agendarCita(nuevaCita);

                // Guardar la cita en el archivo JSON
                guardarCitaEnJSON(nuevaCita);

                // Limpiar los campos después de agendar
                campoSemestre.setText("");
                campoNombre.setText("");
                campoApellidos.setText("");
                campoId.setText("");
                campoFecha.setText("");
                campoHora.setText("");
            }
        });

        return panelAgendamiento;
    }

    private JPanel crearPanelCitas() {
        // Implementa el panel de visualización de citas aquí
        // Debe contener componentes para mostrar la lista de citas
        JPanel panelCitas = new JPanel();
        areaCitas = new JTextArea(10, 40);
        areaCitas.setEditable(false);
        panelCitas.add(new JScrollPane(areaCitas));

        return panelCitas;
    }

    private JPanel crearPanelCancelar() {
        // Implementa el panel de cancelación de citas aquí
        // Debe contener componentes para mostrar la lista de citas y un botón para
        // cancelar
        JPanel panelCancelar = new JPanel();
        JTextArea areaCitasCancelar = new JTextArea(10, 40);
        areaCitasCancelar.setEditable(false);
        JButton botonCancelar = new JButton("Cancelar Cita");

        // Mostrar las citas existentes en el área de texto
        areaCitasCancelar.setText("");
        for (Estudiante estudiante : cita.listaCitas) {
            areaCitasCancelar.append(estudiante.toString() + "\n");
        }

        // Agregar los componentes al panel de cancelación
        panelCancelar.add(new JScrollPane(areaCitasCancelar));
        panelCancelar.add(botonCancelar);
        
        // Acción para el botón Cancelar Cita
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del estudiante cuya cita se desea cancelar desde el campo de
                // texto
                String idCancelar = campoCancelarID.getText();

                if (!idCancelar.isEmpty()) {
                    int idEstudiante = Integer.parseInt(idCancelar);

                    // Llama al método para cancelar la cita por el ID del estudiante
                    cancelarCitaPorID(idEstudiante);

                    // Actualizar el área de citas después de la cancelación
                    areaCitasCancelar.setText("");
                    for (Estudiante estudiante : cita.listaCitas) {
                        areaCitasCancelar.append(estudiante.toString() + "\n");
                    }

                    // Guarda la lista actualizada en el archivo JSON
                    guardarCitasEnJSON(cita.getListaCitas());
                }
            }
        });

        return panelCancelar;
    }

    // Método para guardar una cita en un archivo JSON
    private void guardarCitaEnJSON(Estudiante nuevaCita) {
        try {
            // Cargar citas existentes desde el archivo JSON
            List<Estudiante> citasExistentes = cargarCitasDesdeJSON();

            // Agregar la nueva cita a la lista
            citasExistentes.add(nuevaCita);

            // Crear un objeto Gson con formato legible (pretty printing)
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(citasExistentes);

            // Guardar la lista actualizada en el archivo JSON
            try (FileWriter writer = new FileWriter("citas.json")) {
                writer.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar citas desde el archivo JSON
    private List<Estudiante> cargarCitasDesdeJSON() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("citas.json");
            java.lang.reflect.Type type = new TypeToken<List<Estudiante>>() {
            }.getType();
            List<Estudiante> citas = gson.fromJson(reader, type);

            // Limpiar y cargar las citas en el área de citas
            areaCitas.setText("");
            for (Estudiante estudiante : citas) {
                areaCitas.append(estudiante.toString() + "\n");
            }
            return citas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Método para cancelar una cita por ID
    // Método para cancelar una cita por ID de estudiante
    private void cancelarCitaPorID(int idEstudiante) {
        cita.cancelarCita(idEstudiante);
    }

    // Método para guardar las citas en el archivo JSON
    private void guardarCitasEnJSON(List<Estudiante> citas) {
        try {
            // Crear un objeto Gson con formato legible (pretty printing)
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(citas);

            // Guardar la lista actualizada en el archivo JSON
            try (FileWriter writer = new FileWriter("citas.json")) {
                writer.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VistaOperador("Operador").setVisible(true);
            }
        });
    }
}
