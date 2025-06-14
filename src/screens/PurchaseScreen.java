/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private UrjcBankServer bank = new UrjcBankServer();
    
    @Override
    public KioskScreen show(Context context){
        SimpleKiosk sk = context.getKiosk();
        
        sk.getKiosk().setMenuMode();
        sk.clear();
        final int waitTime = 3000;
        this.configureScreenButtons(sk, context);
        long cardnum = sk.getKiosk().getCardNumber();
       

        
        if (this.bank.comunicationAvaiable()){
            String desc = context.getOrder().getOrderText();
            int cant = context.getOrder().getTotalAmount();
            sk.getKiosk().setDescription(String.format("Introduzca tarjeta para comprar %s por %d €", desc, cant));
            
            char RespuestaInterfaz = sk.getKiosk().waitEvent(waitTime);
            
            System.out.println(RespuestaInterfaz);
            
            
            switch (RespuestaInterfaz){  
                    case '1' -> {
                        this.writerOrderToFile(context);
                        this.incrementOrderNumber(context);

                        List <String> ticket = new ArrayList ();
                        ticket.add(context.getOrderNumber() + "\n" + context.getOrder().getOrderText() + " - Total " + context.getOrder().getTotalAmount() + " €");
                        sk.getKiosk().print(ticket);

                        try {
                            if (this.bank.doOperation(cardnum, cant)){
                                sk.getKiosk().expelCreditCard(120);
                            }
                        } catch (CommunicationException ex) {
                            Logger.getLogger(PurchaseScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        context.nextOrderFile();
                        context.newOrder();
                        WelcomeScreen ws = new WelcomeScreen();
                        ws.show(context);
                    }
                    case 'B' ->{
                        context.nextOrderFile();
                        context.newOrder();
                        OrderScreen os = new OrderScreen();
                        os.show(context);
                    }
                    case 'C'->{
                        context.nextOrderFile();
                        OrderScreen os = new OrderScreen();
                        os.show(context);
                    }
                    case 'D'->{
                        context.nextOrderFile();
                        List <String> ticket = new ArrayList ();
                        ticket.add(context.getOrder().getOrderText() + " - Total " + context.getOrder().getTotalAmount() + " €");
                        sk.getKiosk().print(ticket);
                        context.newOrder();
                        this.writerOrderToFile(context);
                        this.incrementOrderNumber(context);
                        WelcomeScreen ws = new WelcomeScreen();
                        ws.show(context);
                    }
                
            }
        }
        else{
            System.out.println("Comunicacion imposible");

        }
        
        return this;
    }
    //Configuracion de los botones de la pantalla
    private void configureScreenButtons(SimpleKiosk s, Context context){
        s.getKiosk().setOption(1, context.getTranslator().translate("order.cancel"));
        s.getKiosk().setOption(2, context.getTranslator().translate("payment.cancel"));
        s.getKiosk().setOption(3, context.getTranslator().translate("payment.cash"));
    }
    //Método para incrementar el numero de pedido
    private void incrementOrderNumber(Context context){
        context.nextOrder();
    }
    // Método para escribir en el fichero los pedidos realizados
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
            
            System.out.println(currentHour);
            
            //entra si se ha modificado el dia anterior y si estamos en un nuevo dia
            if (lastModifiedHour < 5 && currentHour > 5){
                
                System.out.println("Escribiendo en uno nuevo");
                //Cambiamos el nombre al archivo
                String newFileName = "order_file_" + dateFormat.format(lastModDate) + ".txt";
                File renamedFile = new File(file.getParent(), newFileName);
                
                //Creamos el nuevo archivo
                File newFile = new File(file.getParent(), "order_file.txt");
                
                //Creamos el writer para escribir en el nuevo archivo                
                try (FileWriter writer = new FileWriter(newFile)) {
                    writer.write(context.getOrderNumber() + " - "+ context.getOrder().getOrderText() + " - " + context.getOrder().getTotalAmount() + " € \n");
                }
                
            //Entra si el archivo ha sido modificado por ultima vez hoy    
            }else{
                System.out.println("Escribiendo en uno existente");

                
                //Creamos el writer para escribir en nuestro archivo
                try (FileWriter writer = new FileWriter(file, true)) {
                    writer.write(context.getOrderNumber() + " - "+ context.getOrder().getOrderText() + " - " + context.getOrder().getTotalAmount() + " € \n");
                }
                
                
            }
            
        }catch (IOException e){
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
    
}
