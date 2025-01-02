/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Translator {
    private Map<String, String> palabras; // Mapa con las traducciones

    // Constructor que carga el archivo .properties
    public Translator(String fileName) {
        palabras = new HashMap<>();
        loadTranslations(fileName);
    }

    // Método para cargar las traducciones desde un archivo .properties
    private void loadTranslations(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Archivo no encontrado: " + fileName);
            }
            Properties properties = new Properties();
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                palabras.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo: " + fileName, e);
        }
    }

    // Método para traducir una palabra
    public String translate(String key) {
        return palabras.getOrDefault(key, "Clave no encontrada: " + key);
    }
}
