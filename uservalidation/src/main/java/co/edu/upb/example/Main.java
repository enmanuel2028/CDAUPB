package co.edu.upb.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Invocar el inicio de la aplicación en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear una ventana principal
                JFrame frame = new JFrame("Ticket de Categoría");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Definir la operación de cierre
                frame.setSize(400, 200); // Ajustar el tamaño de la ventana principal
                frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

                // Crear un botón para abrir el diálogo de categoría
                JButton abrirDialogoButton = new JButton("Abrir Diálogo de Categoría");
                abrirDialogoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Cuando se hace clic en el botón, se crea y muestra el diálogo de categoría
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
