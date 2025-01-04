/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import manager.Context;
import manager.SimpleKiosk;
import urjc.UrjcBankServer;

/**
 *
 * @author Sergio
 */
public class PurchaseScreen implements KioskScreen{
    public KioskScreen show(Context context){
        SimpleKiosk sk = context.getKiosk();
        
        sk.getKiosk().setMenuMode();
        sk.clear();
        final int waitTime = 300;
        this.configureScreenButtons(sk, context);
        long cardnum = sk.getKiosk().getCardNumber();
        UrjcBankServer bank = new UrjcBankServer();
        if (bank.comunicationAvaiable()){
            String desc = context.getOrder().getOrderText();
            int cant = context.getOrder().getTotalAmount();
            sk.getKiosk().setDescription(String.format("Introduzca tarjeta para comprar %s por %d €", desc, cant));
            char RespuestaInterfaz = sk.getKiosk().waitEvent(waitTime);
            System.out.println(RespuestaInterfaz);
            
            
            switch (RespuestaInterfaz){
                case '1' -> {
                    this.writerOrderToFile(context);
                    this.incrementOrderNumber(context);
                    System.out.println(context.getOrder().getOrderText() + " - Total " + context.getOrder().getTotalAmount() + " €");
                try {
                    bank.doOperation(cardnum, cant);
                } catch (CommunicationException ex) {
                    Logger.getLogger(PurchaseScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                sk.getKiosk().expelCreditCard(2);
                context.newOrder();
                WelcomeScreen ws = new WelcomeScreen();
                ws.show(context);
                }
            }
        }
        
        return this;
    }
    
    private void configureScreenButtons(SimpleKiosk s, Context context){
        s.getKiosk().setOption(1, context.getTranslator().translate("order.cancel"));
        s.getKiosk().setOption(2, context.getTranslator().translate("payment.cancel"));
    }
    
    private void incrementOrderNumber(Context context){
        context.nextOrder();
    }
    
    private synchronized void writerOrderToFile(Context context){
        File file = new File("C:/Users/Miguel/Desktop/Kiosko/order_file.txt");
        
        try{
            
            if (!file.exists()){
                throw new IOException("El archivo no existe: " + file.getAbsolutePath());
            }
            
            long lastMod = file.lastModified();
            Date lastModDate = new Date(lastMod);
            
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            int lastModifiedHour = Integer.parseInt(timeFormat.format(lastModDate));
            int currentHour = Integer.parseInt(timeFormat.format(new Date()));
            
            //entra si se ha modificado el dia anterior y si estamos en un nuevo dia
            if (lastModifiedHour < 5 && currentHour > 5){
                //Cambiamos el nombre al archivo
                String newFileName = "order_file_" + dateFormat.format(lastModDate) + ".txt";
                File renamedFile = new File(file.getParent(), newFileName);
                
                //Creamos el nuevo archivo
                File newFile = new File(file.getParent(), "order_file.txt");
                
                //Creamos el writer para escribir en el nuevo archivo                
                try (FileWriter writer = new FileWriter(newFile)) {
                    writer.write(context.getOrderNumber() + " - "+ context.getOrder().getOrderText() + " - " + context.getOrder().getTotalAmount() + " € ");
                }
                
            //Entra si el archivo ha sido modificado por ultima vez hoy    
            }else{
                
                //Creamos el writer para escribir en nuestro archivo
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(context.getOrderNumber() + " - "+ context.getOrder().getOrderText() + " - " + context.getOrder().getTotalAmount() + " € ");
                }
                
                
            }
            
        }catch (IOException e){
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
    
}
