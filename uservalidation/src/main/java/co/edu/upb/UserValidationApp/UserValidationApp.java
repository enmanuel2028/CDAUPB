package co.edu.upb.UserValidationApp;

import javax.swing.*;

import co.edu.upb.Banco.VistaBanco;
import co.edu.upb.Operador.VistaOperador;
import co.edu.upb.Ticket.VistaTicket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

public class UserValidationApp extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;

    private Map<String, String> mapaUsuarios; // Mapa para almacenar nombres de usuario y contraseñas

    public UserValidationApp() {
        setTitle("VALIDACION DE USUARIO"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        mapaUsuarios = new HashMap<>(); // Inicialización del mapa de usuarios
        mapaUsuarios.put("operador", "operador"); // Agrega usuarios y contraseñas al mapa
        mapaUsuarios.put("banco", "banco");
        mapaUsuarios.put("ticket", "ticket");

        JPanel panelPrincipal = new JPanel(); // Crea un panel principal
        panelPrincipal.setLayout(null); // Configura el diseño de cuadrícula (filas x columnas)

        JPanel recuadro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int arc = 30; // Radio de redondeo
                int width = getWidth() - 1;
                int height = getHeight() - 1;
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.GRAY);
                g2d.fill(roundedRectangle);
            }
        };
        recuadro.setLayout(null);
        recuadro.setBounds(350, 70, 300, 300);

        Font fontTitulo = new Font("Serif", Font.BOLD, 15);

        JLabel title = new JLabel("FOOD UPB");
        title.setBounds(35, 0, 500, 100);
        title.setFont(new Font("Serif", Font.BOLD, 45));

        JLabel usuario = new JLabel("USER");
        usuario.setBounds(35, 50, 500, 100);
        usuario.setFont(fontTitulo);

        JLabel contrasena = new JLabel("PASSWORD");
        contrasena.setBounds(35, 110, 500, 100);
        contrasena.setFont(fontTitulo);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(35, 110, 230, 20); // Establecer ubicación y tamaño (x, y, ancho, alto)
        campoUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Agregar borde
        Font fontCampoUsuario = campoUsuario.getFont();
        int fontSize = (int) (campoUsuario.getHeight() * 0.6); // Tamaño de fuente en función del alto del campo
        campoUsuario.setFont(new Font(fontCampoUsuario.getName(), Font.PLAIN, fontSize));

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(35, 170, 230, 20);
        campoContrasena.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Font fontCampoContrasena = campoContrasena.getFont();
        int fontSizeContrasena = (int) (campoContrasena.getHeight() * 0.6);
        campoContrasena.setFont(new Font(fontCampoContrasena.getName(), Font.PLAIN, fontSizeContrasena));

        botonIniciarSesion = new JButton("LOGIN"); // Botón para iniciar sesión
        botonIniciarSesion.setBounds(100, 210, 100, 20);
        botonIniciarSesion.setBackground(Color.lightGray);

        recuadro.add(title);
        recuadro.add(usuario);
        recuadro.add(contrasena);
        recuadro.add(campoUsuario);
        recuadro.add(campoContrasena);
        recuadro.add(botonIniciarSesion);
        add(recuadro);
        panelPrincipal.add(recuadro);
        add(panelPrincipal); // Agrega el panel principal a la ventana

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText(); // Obtiene el usuario ingresado
                char[] contrasenaChars = campoContrasena.getPassword(); // Obtiene la contraseña como arreglo de
                                                                        // caracteres
                String contrasena = new String(contrasenaChars); // Convierte el arreglo de caracteres a String

                if (validarUsuario(usuario, contrasena)) { // Si las credenciales son válidas
                    mostrarModuloUsuario(usuario); // Muestra la ventana del módulo de usuario
                } else {
                    JOptionPane.showMessageDialog(UserValidationApp.this, "Usuario o contraseña inválidos.");
                }
            }
        });
    }

    private boolean validarUsuario(String usuario, String contrasena) {
        String contrasenaAlmacenada = mapaUsuarios.get(usuario); // Obtiene la contraseña almacenada
        return contrasenaAlmacenada != null && contrasenaAlmacenada.equals(contrasena); // Compara contraseñas
    }

    private void mostrarModuloUsuario(String usuario) {
        String tipoUsuario = obtenerTipoUsuario(usuario);

        if ("operador".equals(usuario)) {
            VistaOperador operatorWindow = new VistaOperador(usuario);
            operatorWindow.setVisible(true);
        } else if ("banco".equals(usuario)) {
            VistaBanco bancoWindow = new VistaBanco(usuario);
            bancoWindow.setVisible(true);
        } else if ("ticket".equals(usuario)) {
            VistaTicket ticketWindow = new VistaTicket(usuario);
            ticketWindow.setVisible(true);
        }
        // Cierra la ventana principal
       dispose();
    }

    private String obtenerTipoUsuario(String usuario) {
        if ("operador".equals(usuario)) {
            return "Operador";
        } else if ("banco".equals(usuario)) {
            return "Banco";
        } else if ("ticket".equals(usuario)) {
            return "Ticket";
        }
        return "Desconocido";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserValidationApp app = new UserValidationApp(); // Crea la instancia de la aplicación
            app.setVisible(true); // Hace visible la ventana de la aplicación
        });
    }
}
