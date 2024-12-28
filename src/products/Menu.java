package Products;

import java.util.List;

public class Menu implements Products{
    private static int discount = 5; // Descuento se lee del fichero de cofiguracion
    private List<IndividualProduct> products;


    public IndividualProduct getProduct(int id){
        return products.get(id);
    }


    public int getNumProducts(){
        return products.size();
    }

    @Override
    public String getName(){
        String names = "";
        for (IndividualProduct p : products){
            if (!names.isEmpty()){
                names += ", ";
            }
            names += p.getName();
        }
        return names;
    }

    @Override
    public int getPrice(){
        int priceTotal = 0;
        for (IndividualProduct p : products){
            priceTotal += p.getPrice();
        }
        return priceTotal;
    }
}
