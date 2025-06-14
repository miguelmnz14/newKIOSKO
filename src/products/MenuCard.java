/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package products;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Sergio
 */


public class MenuCard {
    private List<MenuCardSection> sectionList;
    private int current;
    
    
    //Método para obtener el MenuCardSection de la seccion que pasamos por parametro
    public MenuCardSection getSection(int index){
        if (index < 0 || index >= this.sectionList.size())
        {
            throw new IndexOutOfBoundsException("Error al obtener el menu");
        }
        return sectionList.get(index);
        
    }
    
    //Método para obtener el numero de secciones
    public int getNumberOfSections(){
        if (sectionList == null)
        {
            return 0;
        }
        else
        {
            return sectionList.size();
        }
    }
    //Método para deserializar el xml 
    public static MenuCard deserializeMenuCard() {
        MenuCard menu = null;
        try {
            XMLDecoder decoder = new XMLDecoder(
                                                new FileInputStream("test/Catalog.xml"));
            menu = (MenuCard) decoder.readObject();
            decoder.close();
            
        } catch(FileNotFoundException fileNotFound){
            System.out.println(fileNotFound.getMessage());
            
        }
        return menu;
        
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    
    //Método constructor
    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
    
    
    
    
}
