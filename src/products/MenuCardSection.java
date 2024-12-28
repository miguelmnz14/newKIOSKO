package Products;

import java.util.List;

/**
 *
 * @author Sergio
 */
public class MenuCardSection {
    private String sectionName;
    private String imageFileName;
    private List<IndividualProduct> productList;
    
    
    public IndividualProduct getProduct(int i){ // Funcion que devuelve el productoIndividual de mi lista 
        if ( i < 0 || i >= productList.size())
        {
            throw new IndexOutOfBoundsException("Indice invalido: "+ i);
        }
        return productList.get(i);
        
    }
    public String getSectionName(){
        return sectionName;
    }
    public int getNumberOfProducts(){
     if (this.productList == null)
     {
        return 0;
     }
     else
     {
         return this.productList.size();
     }
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public MenuCardSection(String sectionName, String imageFileName, List<IndividualProduct> productList) {
        this.sectionName = sectionName;
        this.imageFileName = imageFileName;
        this.productList = productList;
    }
    
    
    
    
        
}
