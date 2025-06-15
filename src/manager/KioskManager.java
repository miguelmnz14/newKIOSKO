/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;
import products.MenuCard;
import products.Order;
import screens.*;

/**
 *
 * @author Miguel
 */
public class KioskManager {
    
    public void run(){
        MenuCard menuCard = null;
        SimpleKiosk kiosko = new SimpleKiosk();
        Context contexto = new Context(
            kiosko,
            new TranslatorManager("test/idioms/"),
            new Order(),
            menuCard.deserializeMenuCard()
        );
        contexto.getTranslator().setCurrentIdiom("espanol");
        WelcomeScreen screen = new WelcomeScreen();
        screen.show(contexto);
        
    }
    
}
