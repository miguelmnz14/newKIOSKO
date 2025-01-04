/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import manager.Context;
import manager.SimpleKiosk;
import products.MenuCardSection;

/**
 *
 * @author Miguel
 */
public class MenuScreen extends CarouselScreen{
    private int section;
    
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        this.section = context.getKioskNumber();
        int currentProduct = context.getMenuCard().getSection(section).getCurrent();
        System.out.println(section);
        
        MenuCardSection prod = context.getMenuCard().getSection(section);
        final int waitTime = 300;
        dispenser.clear();
        dispenser.getKiosk().setTitle(context.getTranslator().translate("order.title"));
        this.configureScreenButtons(dispenser, context);
        dispenser.getKiosk().setImage("C:/Users/Miguel/Desktop/Kiosko/build/classes/" + prod.getProduct(currentProduct).getImageFileName());
        dispenser.getKiosk().setDescription(context.getMenuCard().getSection(section).getProduct(currentProduct).getDescription());
        this.adjustCarruselButtons(currentProduct, context.getMenuCard().getSection(section).getNumberOfProducts(), dispenser);

        char respuestaInterfaz = dispenser.getKiosk().waitEvent(waitTime);
        
        switch (respuestaInterfaz){
            case 'E' -> {
                context.getOrder().addProduct(context.getMenuCard().getSection(this.section).getProduct(currentProduct));
                if (this.section + 1 >= context.getMenuCard().getNumberOfSections()){
                    OrderScreen os = new OrderScreen();
                    os.show(context);
                } else{
                    context.nextKiosk();
                    MenuScreen ms = new MenuScreen();
                    ms.show(context);
                }
                
            }
            case 'F' -> {
                //revisar
                if (this.section == 0){
                    OrderScreen os = new OrderScreen();
                    os.show(context);
                }
                this.section -= 1;
                MenuScreen ms1 = new MenuScreen();
                ms1.show(context);
            }
            case 'G' -> {
                context.getMenuCard().getSection(section).previousProd();
                MenuScreen ms2 = new MenuScreen();
                ms2.show(context);
            }
            case 'H' -> {
                context.getMenuCard().getSection(section).nextProd();
                MenuScreen ms3 = new MenuScreen();
                ms3.show(context);
            }
        }
        
        return this;
    }
    
    public void configureScreenButtons(SimpleKiosk disp, Context context){
        disp.getKiosk().setOption(4, context.getTranslator().translate("order.add_item"));
        disp.getKiosk().setOption(5, context.getTranslator().translate("menuOrder.go_back"));
    } 
    protected void adjustCarruselButtons(int currentElement, int numberOfElements, SimpleKiosk dispen){
        boolean first = currentElement == 0;
        boolean last = currentElement == numberOfElements -1;
        
        if (first){
            dispen.getKiosk().setOption(7, "&gt;");
        } else if(last){
            dispen.getKiosk().setOption(6, "&lt;");
        }else{
            dispen.getKiosk().setOption(6, "&lt;");
            dispen.getKiosk().setOption(7, "&gt;");
        }
    }
}
