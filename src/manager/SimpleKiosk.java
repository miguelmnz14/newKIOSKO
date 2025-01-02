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
    private BurgerSelfOrderKiosk kiosk;
    
    
    private void clear() {
        kiosk.setTitle(null);//OCULTA EL TITULO
        kiosk.setImage(null);//OCULTA LA IAMGEN
        kiosk.setDescription(null);//OCULTA LA DESCRIPCION
        
        for (int cont = 0; cont < 8; cont++) //OCULTA TODOS LOS BOTONES DEL INTERFAZ
            kiosk.setOption(cont, null);//LIMPIA EL BOTON DE LA POSICIÓN cont
    }
}
