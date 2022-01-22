package BaseClases;

public class ItemsClass {
    private int price;
    private String  id,size,imagePath,title,discription,category;
    private boolean isAvaliable=true;

    public ItemsClass() {
    }

    public ItemsClass(String id, int price, String size, String imagePath, String title, String discription, String category) {
        this.id = id;
        this.price = price;
        this.size=size;
        this.imagePath = imagePath;
        this.title = title;
        this.discription = discription;
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvaliable() {
        return isAvaliable;
    }

    public void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }
}
