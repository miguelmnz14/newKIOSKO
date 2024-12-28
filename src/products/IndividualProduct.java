package Products;

public class IndividualProduct implements Products{
    private String name;
    private String description;
    private String imageFileName;
    private int price;


    public IndividualProduct(String name, String description, String imageFileName, int price) {
        this.name = name;
        this.description = description;
        this.imageFileName = imageFileName;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
