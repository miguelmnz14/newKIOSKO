/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package screens;

import java.util.List;
import manager.Context;
import manager.SimpleKiosk;
import products.IndividualProduct;
import products.MenuCardSection;
import products.Product;

/**
 *
 * @author Miguel
 */
public class ProductScreen extends CarouselScreen{
    private int section; //variable para saber en la seccion en la que nos encontramos
    
    
    public KioskScreen show(Context context){
        SimpleKiosk dispenser = context.getKiosk();
        this.section = context.getKioskNumber();
        final int waitTime = 300;
        int currentProduct = context.getMenuCard().getSection(section).getCurrent();
        
        
        dispenser.clear();
        MenuCardSection prod = context.getMenuCard().getSection(section);
        this.adjustCarruselButtons(currentProduct, context.getMenuCard().getSection(section).getNumberOfProducts(), dispenser);
        this.configureScreenButtons(dispenser, context);
        dispenser.getKiosk().setImage("C:/Users/Miguel/Desktop/Kiosko/build/classes/" + prod.getProduct(currentProduct).getImageFileName());
        dispenser.getKiosk().setDescription(context.getMenuCard().getSection(section).getProduct(currentProduct).getDescription());
        char respuestaInterfaz = dispenser.getKiosk().waitEvent(waitTime);
        
        switch (respuestaInterfaz){
            case 'E':
                context.getOrder().addProduct(context.getMenuCard().getSection(section).getProduct(currentProduct));
                OrderScreen os1 = new OrderScreen();
                os1.show(context);
            case 'F':
                OrderScreen os = new OrderScreen();
                os.show(context);
            case 'G':
                context.getMenuCard().getSection(section).previousProd();
                ProductScreen sc = new ProductScreen();
                sc.show(context);
            case 'H':
                context.getMenuCard().getSection(section).nextProd();
                ProductScreen sc1 = new ProductScreen();
                sc1.show(context);
        }
        
        
        
        
        return this;
    }

    
    //Configuracion de los botones de la pantalla
    public void configureScreenButtons(SimpleKiosk disp, Context context){
        disp.getKiosk().setOption(4, context.getTranslator().translate("order.add_item"));
        disp.getKiosk().setOption(5, context.getTranslator().translate("order.cancel"));
    }
    
    
    //Configuracion de los botones del carrusel
    @Override
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
