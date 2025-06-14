package screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import manager.Context;
import manager.SimpleKiosk;

public class IdiomScreen implements KioskScreen{
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        int numIdioms = context.getTranslator().getNumIdioms();
        final int waitTime = 300;
        dispenser.clear();
        dispenser.getKiosk().setMenuMode();
        dispenser.getKiosk().setTitle(context.getTranslator().translate("app.title"));
        dispenser.getKiosk().setImage("Logo.png");
        this.configureScreenButtons(dispenser, context, context.getTranslator().getNumIdioms());
        char respuestaInterfaz = dispenser.getKiosk().waitEvent(waitTime);
        System.out.println(respuestaInterfaz);
        int indice = respuestaInterfaz - 'A';
        
        context.getTranslator().setCurrentIdiom(context.getTranslator().getIdioms().get(indice));
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.show(context);
        return this;
    }

    
    public void configureScreenButtons(SimpleKiosk dispen, Context contexto, int numIdiomas) {
        for(int i = 0; i <= numIdiomas-1; i++){
            dispen.getKiosk().setOption(i, contexto.getTranslator().getIdioms().get(i));
        }
    }
}


