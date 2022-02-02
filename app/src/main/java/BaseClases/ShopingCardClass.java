package BaseClases;


public class ShopingCardClass extends ItemsClass{

    private int amount;
    public ShopingCardClass() {
        super();
    }

    public ShopingCardClass(String id, int price, String size, String imagePath, String title, String discription, String category, Boolean isPopular, int amount) {
        super(id, price, size, imagePath, title, discription, category, isPopular);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean getPopular() {
        return super.getPopular();
    }

    @Override
    public void setPopular(boolean popular) {
        super.setPopular(popular);
    }

    @Override
    public String getSize() {
        return super.getSize();
    }

    @Override
    public void setSize(String size) {
        super.setSize(size);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price);
    }

    @Override
    public String getImagePath() {
        return super.getImagePath();
    }

    @Override
    public void setImagePath(String imagePath) {
        super.setImagePath(imagePath);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public String getDiscription() {
        return super.getDiscription();
    }

    @Override
    public void setDiscription(String discription) {
        super.setDiscription(discription);
    }

    @Override
    public String getCategory() {
        return super.getCategory();
    }

    @Override
    public void setCategory(String category) {
        super.setCategory(category);
    }

    @Override
    public boolean isAvaliable() {
        return super.isAvaliable();
    }

    @Override
    public void setAvaliable(boolean avaliable) {
        super.setAvaliable(avaliable);
    }
}
