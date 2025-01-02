package products;

import java.util.List;

public class Menu implements Product{
    private static int discount = 5; // Descuento se lee del fichero de cofiguracion
    private List<IndividualProduct> productos;


    public IndividualProduct getProduct(int id){
        return productos.get(id);
    }


    public int getNumProducts(){
        return productos.size();
    }

    @Override
    public String getName(){
        String names = "";
        for (IndividualProduct p : productos){
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
        for (IndividualProduct p : productos){
            priceTotal += p.getPrice();
        }
        return priceTotal;
    }
}
