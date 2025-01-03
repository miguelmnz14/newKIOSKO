/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import manager.Context;
import manager.SimpleKiosk;

/**
 *
 * @author Miguel
 */
public class CarouselScreen implements KioskScreen {
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        this.adjustCarruselButtons(context.getKioskNumber(), context.getMenuCard().getNumberOfSections(), dispenser);
        
        return this;
    }
    
    protected void adjustCarruselButtons(int currentElement, int numberOfElements, SimpleKiosk dispen){
        boolean first = currentElement == 0;
        boolean last = currentElement == numberOfElements -1;
        
        if (first){
            dispen.getKiosk().setOption(7, "&gt;");
        } else if(last){
            dispen.getKiosk().setOption(6, "&lt;");
        }else{
            dispen.getKiosk().setOption(6, "&lt;");
            dispen.getKiosk().setOption(7, "&gt;");
        }
    }
}
