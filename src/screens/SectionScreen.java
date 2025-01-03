/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import java.net.URL;
import manager.Context;
import manager.SimpleKiosk;


/**
 *
 * @author Miguel
 */
public class SectionScreen extends CarouselScreen{
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        
        final int waitTime = 300;
        dispenser.clear();
        dispenser.getKiosk().setTitle(context.getTranslator().translate("order.title"));
        dispenser.getKiosk().setImage("C:/Users/Miguel/Desktop/Kiosko/build/classes/" + context.getMenuCard().getSection(context.getKioskNumber()).getImageFileName());
        dispenser.getKiosk().setDescription(context.getMenuCard().getSection(context.getKioskNumber()).getSectionName());
        super.show(context);
        this.configureScreenButtons(dispenser, context);
        char respuestaInterfaz = dispenser.getKiosk().waitEvent(waitTime);
        System.out.println(respuestaInterfaz);
        
        
        switch (respuestaInterfaz){
            case 'E':
                ProductScreen ps = new ProductScreen();
                ps.show(context);
            case 'F':
                OrderScreen os = new OrderScreen();
                os.show(context);
            case 'G':
                context.previousKiosk();
                SectionScreen sc = new SectionScreen();
                sc.show(context);
            case 'H':
                context.nextKiosk();
                SectionScreen sc1 = new SectionScreen();
                sc1.show(context);
        }
        return this;
    }
    
    public void configureScreenButtons(SimpleKiosk disp, Context context){
        disp.getKiosk().setOption(4, context.getTranslator().translate("order.advance"));
        disp.getKiosk().setOption(5, context.getTranslator().translate("order.cancel"));
    }
    
    
}
