package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.naming.CommunicationException;
import sienens.BurgerSelfOrderKiosk;
import urjc.UrjcBankServer;

/**
 *
 * @author jvelez 
*/
class BurgerSelfKioskNew {
    BurgerSelfOrderKiosk dispenser = new BurgerSelfOrderKiosk();
    
    UrjcBankServer bank = new UrjcBankServer();
    int mode = 0;
    
    private Locale currentLocale = new Locale("es", "ES"); // Idioma por defecto: Español
    private ResourceBundle messages = ResourceBundle.getBundle("demo.messages", currentLocale);

    
    private void clear() {
        dispenser.setTitle(null);//OCULTA EL TITULO
        dispenser.setImage(null);//OCULTA LA IAMGEN
        dispenser.setDescription(null);//OCULTA LA DESCRIPCION
        
        for (int cont = 0; cont < 8; cont++) //OCULTA TODOS LOS BOTONES DEL INTERFAZ
            dispenser.setOption(cont, null);//LIMPIA EL BOTON DE LA POSICIÓN cont
    }
    
    private void loadLanguage(String languageCode, String countryCode) {
        currentLocale = new Locale(languageCode, countryCode);
        messages = ResourceBundle.getBundle("demo.messages", currentLocale);
    }
    
    char showPrincipal(){
        final int waitTime = 300;
        clear();
        dispenser.setMenuMode();
        dispenser.setTitle(messages.getString("app.title"));
        dispenser.setImage("Logo.png");
        dispenser.setOption(1, messages.getString("menu.new_order"));
        dispenser.setOption(4, messages.getString("menu.change_language"));
        char respuestaInterfaz = dispenser.waitEvent(waitTime);
        System.out.println("Evento recibido" + respuestaInterfaz);
        return respuestaInterfaz;
    }
    
    
    void run() {
        
        final int waitTime = 300;
        
        
        while(true) {
            // Interfaz principal
            char respuestaInterfaz = showPrincipal();
            
            if (respuestaInterfaz == 'E') { // Si selecciona "Cambiar idioma"
                clear();
                dispenser.setImage("Logo.png");
                dispenser.setTitle(messages.getString("menu.select_language"));
                dispenser.setOption(0, "Español");
                dispenser.setOption(1, "English");
                dispenser.setOption(2, "Deutsch");
                dispenser.setDescription(messages.getString("menu.language_description"));
                respuestaInterfaz = dispenser.waitEvent(waitTime);
                System.out.println("evento recibido" + respuestaInterfaz);

                // Cambiar idioma según selección
                if (respuestaInterfaz == 'A') {
                    loadLanguage("es", "ES");
                    
                } else if (respuestaInterfaz == 'B') {
                    loadLanguage("en", "EN");
                    showPrincipal();
                } else if (respuestaInterfaz == 'C') {
                    loadLanguage("ge", "GE");
                }
            }else if (respuestaInterfaz == 'B') {//SI HAN METIDO UNA TARJETA DE CREDITO
                dispenser.retainCreditCard(false);//SE RETIENE LA TARJETA PARA HACER LAS COMPROBACIONES

                try {
                    long numeroTajetaIntroducida = dispenser.getCardNumber();//SE CONSULTA AL INTERFAZ QUE NUMERO DE TARGEJETA SE HA INTRODUCIDO
                    System.out.println(numeroTajetaIntroducida);
                    int cantidadAPagar = 200;//SE INVENTA QUE LA CANTIDAD A PAGAR SON 200 
                    boolean ok = bank.doOperation(numeroTajetaIntroducida,cantidadAPagar);//SE PREGUNTA AL BANCO SI ESA TARJETA PUEDE PAGAR ESA CANTIDAD
                    if (ok) {
                        dispenser.setMessageMode();
                        dispenser.setTitle("Proceso de pago exitoso");
                        dispenser.setDescription("Ya puedes recoger tu tarjeta\nTu número de pedido es 33\nRecoge el ticket\nTe rogamos que permanezcas atento a las pantallas");
                        int tiempoEsperaRetiradaTarjeta = 30;
                        dispenser.expelCreditCard(tiempoEsperaRetiradaTarjeta);//SE LIBERA LA TARJETA DURANTE UN TIMPO PARA QUE LA RECOJA EL USAIRO SI NO SE LA TRAGARA
                        
                        //SE CONTRUYE EL MENSAJE QUE SE IMPRIMIRA EN EL TICKET CON EL RESUMEN DEL PEDIDO Y EL PAGO
                        ArrayList <String> ticketText = new ArrayList<>();
                        ticketText.add("Artículos comprados");
                        ticketText.add("=====================");
                        ticketText.add("Menú - 200€");
                        ticketText.add("=====================");
                        ticketText.add("Total: 200€");
                        ticketText.add("");
                        ticketText.add("Número de pedido: 33");
                        dispenser.print(ticketText);//SE GENERA UN TIQUECK CON EL RESULTADO DEL PAGO
                        
                    } else {
                        dispenser.setMessageMode();//SE PONE EL INTERFAZ EN MODO MENSAJE PARA INFORMAR DEL ERROR
                        dispenser.setTitle("Problemas en el proceso de pago");
                        dispenser.setDescription("El banco dice que no tienes dinero. Prueba con otra tarjeta.");            
                        dispenser.expelCreditCard(waitTime);
                    }
                } catch(CommunicationException ex) {//A LA HORA DE PGAR EL SISTEMA BANCARIO PUEDE DAR ERRORES (SIMULA UN SERVIDOR BANCARIO REAL)
                    dispenser.setMessageMode();//SE PONE EL INTERFAZ EN MODO MENSAJE PARA INFORMAR DEL ERROR
                    dispenser.setTitle("Problemas de comunicación en el proceso de pago");
                    dispenser.setDescription("Reintentando");
                }
            }
            
          
            
        }
    }
    

}