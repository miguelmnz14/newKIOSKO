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
    
    
    
    public MenuCardSection getSection(int index){
        if (index < 0 || index >= this.sectionList.size())
        {
            throw new IndexOutOfBoundsException("Error al obtener el menu");
        }
        return sectionList.get(index);
        
    }
    
    
    public int getNumberOfSections(){ // No se si este metodo hace lo que tiene que hacer que es devolver el numero de elementos que hay en sectionList
        if (sectionList == null)
        {
            return 0;
        }
        else
        {
            return sectionList.size();
        }
    }
    
    public static MenuCard deserializeMenuCard() {
        MenuCard menu = null;
        try {
            XMLDecoder decoder = new XMLDecoder(
                                                new FileInputStream("src/Catalog.xml"));
            menu = (MenuCard) decoder.readObject();
            decoder.close();
            
        } catch(FileNotFoundException fileNotFound){
            System.out.println(fileNotFound.getMessage());
            
        }
        return menu;
        
    }

    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
    
    
}
