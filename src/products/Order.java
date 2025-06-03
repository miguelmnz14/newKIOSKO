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
        List<Product> listAux = new ArrayList<>();
        if (this.products == null){
            return 0;
        }
        int contprod;
        for (Product p: this.products){
            if (!listAux.contains(p)){
                listAux.add(p);
                totalAmount += p.getPrice();
                               
            }else{
                contprod = numSamePro(p,listAux);
                for (int i=0; i < contprod; i++){
                    totalAmount += p.getPrice()/2;
                }
                
            }
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
    public boolean isSameProduct(Product p1, Product p2){
        return(p1.getName().equals(p2.getName()));
    }
    public int numSamePro(Product p1, List<Product> aux){
        int cont =0;
        for (Product p2 : aux){
            if (isSameProduct(p1,p2)){
                cont ++;
            }
        }
        return cont;
    }
    
    


}
