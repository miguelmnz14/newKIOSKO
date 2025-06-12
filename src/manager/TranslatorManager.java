/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.File;
import manager.Translator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author Miguel
 */
public class TranslatorManager {
    private Translator currentDictionary;
    private Map<String, Translator> dictionaries = new HashMap<>();
    private int numIdioms;
    
    public TranslatorManager(String rutaDirectorio){
        File directorio = new File(rutaDirectorio);
        
        if(directorio.exists() && directorio.isDirectory()){
            File [] ficheros = directorio.listFiles();
            for(File fich: ficheros){
                Translator nuevoTranslator = new Translator(fich.getPath());
                
                String nombreDelDic = fich.getName().substring(0, fich.getName().length()-4);
                dictionaries.put(nombreDelDic, nuevoTranslator);
                this.numIdioms++;
            }
        }
    
    }
    
    public void setCurrentIdiom(String idiom){
        this.currentDictionary = this.dictionaries.get(idiom);
    }

    public Translator getCurrentDictionary() {
        return currentDictionary;
    }

    public int getNumIdioms() {
        return numIdioms;
    }
    
    
    
    public List<String> getIdioms(){
        return new ArrayList<> (this.dictionaries.keySet());
    }
    
    public String translate(String word){
        return this.currentDictionary.translate(word);
    }
}
