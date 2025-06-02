/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import products.MenuCard;
import products.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.naming.CommunicationException;
import sienens.BurgerSelfOrderKiosk;
import urjc.UrjcBankServer;

/**
 *
 * @author Miguel
 */
public class Context {
    private SimpleKiosk kiosk;
    private TranslatorManager translator;
    private Order order;
    private MenuCard menuCard;
    private int orderNumber;
    private int kioskNumber; //para numero de seccion
    private int numberofKiosk;

    public Context(SimpleKiosk kiosk, TranslatorManager translator, Order order, MenuCard menuCard) {
        this.kiosk = kiosk;
        this.translator = translator;
        this.order = order;
        this.menuCard = menuCard;
    }

    public int getKioskNumber() {
        return kioskNumber;
    }
    
    public void previousKiosk(){
        this.kioskNumber -= 1;
    }
    
    public void originalKiosk(){
        this.kioskNumber = 0;
    }
    
    public void nextKiosk(){
        this.kioskNumber += 1;
    }
    
    public void nextOrder(){
        this.orderNumber += 1;
    }
    

    public SimpleKiosk getKiosk() {
        return kiosk;
    }

    public TranslatorManager getTranslator() {
        return translator;
    }

    public Order getOrder() {
        return order;
    }

    public MenuCard getMenuCard() {
        return menuCard;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getNumberofKiosk() {
        return numberofKiosk;
    }

    public void newOrder(){
        this.order = new Order();
    }
    
    public void newKiosk(){
        this.kiosk = new SimpleKiosk();
    }
    
    

    
    
    
    
}
