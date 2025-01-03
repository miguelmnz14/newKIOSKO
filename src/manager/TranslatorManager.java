/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

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
    private Map<String, Translator> dictionaries;
    
    private Locale currentLocale = new Locale("es", "ES"); // Idioma por defecto: Español
    private ResourceBundle messages = ResourceBundle.getBundle("demo.messages", currentLocale);
    
    private void loadLanguage(String languageCode, String countryCode) {
        currentLocale = new Locale(languageCode, countryCode);
        messages = ResourceBundle.getBundle("messages", currentLocale);
    }
    
    public TranslatorManager(){
        dictionaries = new HashMap<>();
        dictionaries.put("Espanol", new Translator("demo/messages_es.properties"));
        dictionaries.put("Ingles", new Translator("demo/messages_en.properties"));
        dictionaries.put("Aleman", new Translator("demo/messages_ge.properties"));
        currentDictionary = new Translator("demo/messages_es.properties");

    }
    
    public void setCurrentIdiom(String idiom){
        currentDictionary = dictionaries.get(idiom);
        if (currentDictionary == null){
            throw new IllegalArgumentException("Idioma no encontrado: " + idiom);
        }
    }

    public Translator getCurrentDictionary() {
        return currentDictionary;
    }
    
    
    public List<String> getIdioms(){
        return new ArrayList<> (this.dictionaries.keySet());
    }
    
    public String translate(String word){
        return this.currentDictionary.translate(word);
    }
}
