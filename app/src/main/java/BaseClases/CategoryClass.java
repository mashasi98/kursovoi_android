package BaseClases;

import java.util.ArrayList;

public class CategoryClass {
    private String  id,imagePath,title;
    private ArrayList<ItemsClass> category_items;

    public CategoryClass() {
    }

    public CategoryClass(String id, String imagePath, String title, ArrayList<ItemsClass> category_items) {
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;
        this.category_items = category_items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<ItemsClass> getCategory_items() {
        return category_items;
    }

    public void setCategory_items(ArrayList<ItemsClass> category_items) {
        this.category_items = category_items;
    }
}
