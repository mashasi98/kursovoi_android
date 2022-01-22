package BaseClases;

public class NewsClass {
    private String  id,imagePath,title,discription;

    public NewsClass() {
    }

    public NewsClass(String  id, String imagePath, String title, String discription) {
        this.imagePath = imagePath;
        this.title = title;
        this.discription = discription;
        this.id = id;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
