/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import manager.Context;
import manager.SimpleKiosk;

/**
 *
 * @author Miguel
 */
public class OrderScreen implements KioskScreen{
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        
        final int waitTime = 300;
        dispenser.clear();
        dispenser.getKiosk().setTitle(context.getTranslator().translate("order.title"));
        dispenser.getKiosk().setImage("Pedido.png");
        dispenser.getKiosk().setDescription(context.getTranslator().translate("order.description") + " " + context.getOrderNumber() + " " + context.getOrder().getOrderText() + "\n" + context.getTranslator().translate("total_amount") + " " + context.getOrder().getTotalAmount());
        this.configureScreenButtons(dispenser, context); 
        char respuestaInterfaz = dispenser.getKiosk().waitEvent(waitTime);
        System.out.println(respuestaInterfaz);
        
        switch (respuestaInterfaz){
            case 'B' -> {
                MenuScreen ms = new MenuScreen();
                ms.show(context);
            }
            case 'C' -> {
                SectionScreen sect = new SectionScreen();
                sect.show(context);
            }
            case 'D' -> {
                PurchaseScreen ps = new PurchaseScreen();
                ps.show(context);
            }
            case 'E' -> {
                context.nextOrderFile();
                context.newOrder();
                WelcomeScreen welcome = new WelcomeScreen();
                welcome.show(context);
            }
        }
                
        
        return this;
    }
    //Configuracion de los botones de la pantalla
    public void configureScreenButtons(SimpleKiosk dispen, Context context){
        dispen.getKiosk().setOption(1, context.getTranslator().translate("order.add_menu"));
        dispen.getKiosk().setOption(2, context.getTranslator().translate("order.add_item"));
        dispen.getKiosk().setOption(3, context.getTranslator().translate("order.finish"));
        dispen.getKiosk().setOption(4, context.getTranslator().translate("order.cancel"));
    }
}
