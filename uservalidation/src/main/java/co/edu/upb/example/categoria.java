package co.edu.upb.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class categoria extends JDialog {
    // Variables de instancia
    private JComboBox<String> categoriaComboBox; // Para seleccionar la categoría
    private JButton obtenerTicketButton; // Para obtener un ticket
    private int numeroTurno = 1; // Contador del número de turno

    // Constructor de la clase 'categoria'
    public categoria(Frame parent) {
        super(parent, "Seleccionar Categoría", true); // Configuración del diálogo

        categoriaComboBox = new JComboBox<>(); // Crear un ComboBox para las categorías
        categoriaComboBox.addItem("Primer Semestre"); // Añadir opciones al ComboBox
        categoriaComboBox.addItem("Discapacitado");
        categoriaComboBox.addItem("Sin Prioridad");

        obtenerTicketButton = new JButton("Obtener Ticket"); // Crear un botón

        // ActionListener para manejar el clic del botón
        obtenerTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la categoría seleccionada y un número de cubículo aleatorio
                String categoriaSeleccionada = (String) categoriaComboBox.getSelectedItem();
                int numeroCubiculo = asignarCubiculoAleatorio();

                // Mostrar un ticket con la información relevante
                mostrarTicket(categoriaSeleccionada, numeroCubiculo);

                dispose(); // Cerrar el diálogo después de obtener el ticket
            }
        });

        // Crear un panel y agregar componentes
        JPanel panel = new JPanel();
        panel.add(new JLabel("Seleccione su categoría:"));
        panel.add(categoriaComboBox);
        panel.add(obtenerTicketButton);

        add(panel); // Agregar el panel al diálogo
        pack(); // Ajustar el tamaño del diálogo automáticamente
        setLocationRelativeTo(null); // Centrar el diálogo en la pantalla
    }

    // Método para asignar un número de cubículo aleatorio
    private int asignarCubiculoAleatorio() {
        Random random = new Random();
        int numeroCubiculo;

        if (categoriaComboBox.getSelectedItem().equals("Primer Semestre")) {
            numeroCubiculo = random.nextInt(2) + 5; // Números aleatorios 5 o 6 para "Primer Semestre"
        } else if (categoriaComboBox.getSelectedItem().equals("Discapacitado")) {
            numeroCubiculo = random.nextInt(2) + 7; // Números aleatorios 7 o 8 para "Discapacitado"
        } else {
            numeroCubiculo = random.nextInt(4) + 1; // Números aleatorios entre 1 y 4 para "Sin Prioridad"
        }

        return numeroCubiculo;
    }

    // Método para mostrar un ticket con la información relevante
    private void mostrarTicket(String categoria, int numeroCubiculo) {
        JOptionPane.showMessageDialog(this, "Categoría: " + categoria + "\nNúmero de Cubículo: " + numeroCubiculo
                + "\nNúmero de Turno: " + numeroTurno, "Ticket", JOptionPane.INFORMATION_MESSAGE);
        numeroTurno++; // Incrementar el número de turno
    }

    // Método principal de la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear y configurar la ventana principal
                JFrame frame = new JFrame("Ticket de Categoría");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);

                // Botón para abrir el diálogo de categoría
                JButton abrirDialogoButton = new JButton("Abrir Diálogo de Categoría");
                abrirDialogoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Crear y mostrar el diálogo de categoría
                        categoria dialog = new categoria(frame);
                        dialog.setVisible(true);
                    }
                });

                frame.add(abrirDialogoButton); // Agregar el botón a la ventana principal
                frame.setVisible(true); // Mostrar la ventana principal
            }
        });
    }
}

class CuadriculaCubiculos {
    private JFrame frame;
    private JPanel cubiculoPanel;
    private JPanel[] cubiculoPanels;
    private JLabel[] numeroLabels;
    private JLabel[] estadoLabels;
    private JLabel[] imagenLabels;
    private boolean[] ocupado;

    public CuadriculaCubiculos(int numeroCubiculos) {
        frame = new JFrame("Cubiculos de Atención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar el cierre de la ventana
        frame.setLayout(new BorderLayout());

        cubiculoPanel = new JPanel();
        cubiculoPanel.setLayout(new GridLayout(2, 4)); // Cuadrícula de 2 filas y 4 columnas (ajustable)

        cubiculoPanels = new JPanel[numeroCubiculos];
        numeroLabels = new JLabel[numeroCubiculos];
        estadoLabels = new JLabel[numeroCubiculos];
        imagenLabels = new JLabel[numeroCubiculos];
        ocupado = new boolean[numeroCubiculos];

        for (int i = 0; i < numeroCubiculos; i++) {
            cubiculoPanels[i] = new JPanel();
            cubiculoPanels[i].setLayout(new BoxLayout(cubiculoPanels[i], BoxLayout.Y_AXIS));
            cubiculoPanels[i].setBackground(Color.LIGHT_GRAY); // Fondo gris claro

            numeroLabels[i] = new JLabel("Cubículo " + (i + 1));
            numeroLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            numeroLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            estadoLabels[i] = new JLabel("Desocupado");
            estadoLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            imagenLabels[i] = new JLabel();
            imagenLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            cubiculoPanels[i].add(numeroLabels[i]);
            cubiculoPanels[i].add(estadoLabels[i]);
            cubiculoPanels[i].add(imagenLabels[i]);

            // Agrega un borde al panel del cubículo
            cubiculoPanels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            cubiculoPanel.add(cubiculoPanels[i]);
            ocupado[i] = false; // Inicialmente, todos los cubículos están desocupados

            final int cubiculoIndex = i;
            cubiculoPanels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Cambia el estado del cubículo (ocupado o desocupado)
                    ocupado[cubiculoIndex] = !ocupado[cubiculoIndex];

                    // Actualiza el estado y la imagen para reflejar el estado
                    if (ocupado[cubiculoIndex]) {
                        // Cubículo ocupado
                        estadoLabels[cubiculoIndex].setText("Ocupado");
                    } else {
                        // Cubículo desocupado
                        estadoLabels[cubiculoIndex].setText("Desocupado");
                    }
                }
            });

            // Asigna las imágenes a los cubículos 5, 6, 7 y 8
            if (i >= 4 && i <= 6) {
                ImageIcon imageIcon = new ImageIcon("src/main/java/org/example/1.png");
                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                imagenLabels[i].setIcon(new ImageIcon(image));
            }
            if (i >= 6 && i <= 8) {
                ImageIcon imageIcon = new ImageIcon("src/main/java/org/example/discapacidad.png");
                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                imagenLabels[i].setIcon(new ImageIcon(image));
            }
        }

        frame.add(cubiculoPanel, BorderLayout.CENTER);
        frame.setSize(800, 400); // Ajusta el tamaño de la ventana principal
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CuadriculaCubiculos(8); // Puedes especificar el número de cubículos que deseas mostrar
            }
        });
    }
}
