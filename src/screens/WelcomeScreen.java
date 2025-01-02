package screens;

import javax.swing.*;
import manager.Context;

public class WelcomeScreen implements KioskScreen{

    public KioskScreen show(Context context) {
        // Crear el marco principal (ventana)
        JFrame frame = new JFrame("Inicio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        // Crear un panel
        JPanel panel = new JPanel();
        frame.add(panel);
        colocarComponentes(panel, frame);

        // Hacer visible la ventana
        frame.setVisible(true);
        return this;
    }
    
    public void colocarComponentes(JPanel panel, JFrame frame) {
        panel.setLayout(null); // Diseño absoluto

        // Botón
        JButton boton = new JButton("Select Idiom");
        boton.setBounds(10, 80, 150, 25);
        boton.addActionListener(e -> {
            frame.dispose(); //cerrar la actual
            new IdiomScreen().show();
        });
        panel.add(boton);
    }



}
