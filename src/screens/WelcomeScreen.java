package screens;

import javax.swing.*;
import manager.Context;
import manager.SimpleKiosk;
import sienens.BurgerSelfOrderKiosk;

public class WelcomeScreen implements KioskScreen{

    
    @Override
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        
        final int waitTime = 300;
        dispenser.clear();
        dispenser.getKiosk().setMenuMode();
        dispenser.getKiosk().setTitle(context.getTranslator().translate("app.title"));
        dispenser.getKiosk().setImage("Logo.png");
        this.configureScreenButtons(dispenser, context);
        char respuestaInterfaz = dispenser.getKiosk().waitEvent(waitTime);
        System.out.println(respuestaInterfaz);
        
        if (respuestaInterfaz == 'E'){
            IdiomScreen idiomScreen = new IdiomScreen();
            idiomScreen.show(context);
        }else if(respuestaInterfaz == 'B'){
            OrderScreen orderScreen = new OrderScreen();
            orderScreen.show(context);
        }
        
        return this;
    }
    
    
    
    private void configureScreenButtons(SimpleKiosk dispen, Context context) {
        dispen.getKiosk().setOption(1, context.getTranslator().translate("menu.new_order"));
        dispen.getKiosk().setOption(4, context.getTranslator().translate("menu.change_language"));
    }



}
