package products;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNumber;
    private List<Product> products;


    public int getOrderNumber() {
        return orderNumber;
    }
    
    //Método para obtener la descripcion de cada pedido
    public String getOrderText(){ 
        if (products == null || products.isEmpty()){
            return "\n- No products in this order\n";
        }
        StringBuilder productList = new StringBuilder();
        for (Product p : products){
            if (productList.length()>0){
                productList.append(",");
            }
            productList.append(p.getName());
            
        }
        return " \n-Products: " + productList;
    }
    
    //Método para obtener el coste de cada pedido
    public int getTotalAmount(){
        int totalAmount = 0;
        if (this.products == null){
            return 0;
        }
        for (Product p : products){
            totalAmount += p.getPrice();
        }
        return totalAmount;
    }
    
    //Método para agregar un producto al array
    public void addProduct(Product p){
        if (this.products == null)
        {
            this.products = new ArrayList<Product>();
        }
        
        this.products.add(p);
        
    }


}
