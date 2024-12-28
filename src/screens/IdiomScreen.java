package screens;

import javax.swing.*;
import java.awt.*;

public class IdiomScreen implements Screen{
    public void show(){
        // Crear el marco principal (ventana)
        JFrame frame = new JFrame("Select Idiom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        JPanel panel = new JPanel();
        frame.add(panel);
        colocarComponentes(panel, frame);

        frame.setVisible(true);
    }

    @Override
    public void colocarComponentes(JPanel panel, JFrame frame) {
        panel.setLayout(null); // Diseño absoluto

        JLabel label = new JLabel("Selecciona un idioma:");
        label.setBounds(50, 20, 200, 25);
        panel.add(label);

        // Botón para Español
        JButton botonEspanol = new JButton("Español");
        botonEspanol.setBounds(50, 60, 100, 25);
        botonEspanol.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Idioma seleccionado: Español");
            frame.dispose(); // Cerrar la pantalla actual
            new WellcomeScreen().show(); // Regresar a la pantalla principal
        });
        panel.add(botonEspanol);

        // Botón para Inglés
        JButton botonIngles = new JButton("English");
        botonIngles.setBounds(200, 60, 100, 25);
        botonIngles.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Idioma seleccionado: English");
            frame.dispose(); // Cerrar la pantalla actual
            new WellcomeScreen().show(); // Regresar a la pantalla principal
        });
        panel.add(botonIngles);
    }
}


