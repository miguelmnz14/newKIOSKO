/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Translator {
    private Map<String,String> palabras = new HashMap<>() {}; // Mapa con las traducciones

    // Constructor que carga el archivo .properties
    public Translator(String fileName) {
        try{
            Scanner iteradorFich = new Scanner(new File(fileName));
            
            while (iteradorFich.hasNextLine()){
                String linea = iteradorFich.nextLine();
                String [] cadenaYTraduccion = linea.split("=");
                palabras.put(cadenaYTraduccion[0], cadenaYTraduccion[1]);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("No se encuentra el fichero de idiomas" + fileName);
        }
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
        if(this.palabras.containsKey(key.replace("\n", "*"))){
            return palabras.get(key.replace("\n", "*")).replace("*","\n");
        }else{
            return key;
        }
    }
}
