package BaseClases;

import java.util.ArrayList;
import java.util.List;

public class CategoryClass {
    private String  id,imagePath,title;


    public CategoryClass() {
    }

    public CategoryClass(String id, String imagePath, String title) {
        this.id = id;
        this.imagePath = imagePath;
        this.title = title;

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


}
