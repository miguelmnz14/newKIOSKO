/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import sienens.BurgerSelfOrderKiosk;


/**
 *
 * @author Miguel
 */
public class SimpleKiosk {
    private BurgerSelfOrderKiosk kioskDefault = new BurgerSelfOrderKiosk();  
    
    
    public void clear() {
        kioskDefault.setTitle(null);//OCULTA EL TITULO
        kioskDefault.setImage(null);//OCULTA LA IAMGEN
        kioskDefault.setDescription(null);//OCULTA LA DESCRIPCION
        
        for (int cont = 0; cont < 8; cont++) //OCULTA TODOS LOS BOTONES DEL INTERFAZ
            kioskDefault.setOption(cont, null);//LIMPIA EL BOTON DE LA POSICIÓN cont
    }

    public BurgerSelfOrderKiosk getKiosk() {
        return kioskDefault;
    }
    
    
}
