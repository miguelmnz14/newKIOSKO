/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Products;

import java.util.List;

/**
 *
 * @author Sergio
 */
public class MenuCard {
    private List<MenuCardSection> sectionList;
    
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
    
    public static MenuCard loadFromDisk(){
        
    }

    public MenuCard(List<MenuCardSection> sectionList) {
        this.sectionList = sectionList;
    }
    
    
}
