/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import manager.Translator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miguel
 */
public class TranslatorManager {
    private Translator currentDictionary;
    private Map<String, Translator> dictionaries;
    
    public TranslatorManager(){
        dictionaries = new HashMap<>();
        dictionaries.put("Espanol", new Translator("demo/messages_es.properties"));
        dictionaries.put("Ingles", new Translator("demo/messages_en.properties"));
        dictionaries.put("Aleman", new Translator("demo/messages_ge.properties"));

    }
    
    public void setCurrentIdiom(String idiom){
        currentDictionary = dictionaries.get(idiom);
        if (currentDictionary == null){
            throw new IllegalArgumentException("Idioma no encontrado: " + idiom);
        }
    }
    
    public List<String> getIdioms(){
        return new ArrayList<> (this.dictionaries.keySet());
    }
    
    public String translate(String word){
        return this.currentDictionary.translate(word);
    }
}
