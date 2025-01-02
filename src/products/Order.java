package products;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNumber;
    private List<Product> products;


    public int getOrderNumber() {
        return orderNumber;
    }

    public String getOrderText(){ // Funcion que muestra el nombre de los productos que tiene un pedido
        if (products == null || products.isEmpty()){
            return "Order number: " + orderNumber + "- No products in this order";
        }
        StringBuilder productList = new StringBuilder();
        for (Product p : products){
            if (productList.length()>0){
                productList.append(",");
            }
            productList.append(p.getName());
            
        }
        return "Order number: "+ orderNumber + " -Products: " + productList;
    }

    public int getTotalAmount(){
        int totalAmount = 0;
        for (Product p : products){
            totalAmount += p.getPrice();
        }
        return totalAmount;
    }
    public void addProduct(Product p){
        if (this.products == null)
        {
            this.products = new ArrayList<Product>();
        }
        
        this.products.add(p);
        
    }


}
