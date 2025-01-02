package screens;

import javax.swing.*;
import java.awt.*;
import manager.Context;
import manager.SimpleKiosk;

public class IdiomScreen implements KioskScreen{
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

        // Cambiar idioma según selección
        if (respuestaInterfaz == 'A') {
            context.getTranslator().setCurrentIdiom("Espanol");
        } else if (respuestaInterfaz == 'B') {
            context.getTranslator().setCurrentIdiom("Ingles");
        } else if (respuestaInterfaz == 'C') {
            context.getTranslator().setCurrentIdiom("Aleman");
        }
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.show(context);
        return this;
    }

    
    public void configureScreenButtons(SimpleKiosk dispen, Context contexto) {
        dispen.getKiosk().setOption(0, "Español");
        dispen.getKiosk().setOption(1, "English");
        dispen.getKiosk().setOption(2, "Deutsch");
    }
}


