/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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
    
    

    public void nextOrderFile(){
        int orderNumber;
                String ruta = "test/OrderNumber.txt";
                try {
                    // Leer el número del archivo
                    String content = new String(Files.readAllBytes(Paths.get(ruta)));
                    orderNumber = Integer.parseInt(content.trim());
                    orderNumber++;

                    // Escribir el nuevo número
                    Files.write(
                        Paths.get(ruta),
                        String.valueOf(orderNumber).getBytes(),
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.CREATE // Añade CREATE por si el archivo no existe
                    );
                } catch (IOException | NumberFormatException e) {
                    System.out.println("Error al leer o escribir el número: " + e.getMessage());
                    e.printStackTrace(); // Esto te dará más información del error
                }
    }
    
    
    
}
