package BaseClases;

public class MainModels {
    private Integer picture,id;
    String title,description;

    public MainModels(Integer picture, String description) {
        this.picture = picture;
        this.title = description;
    }

    public MainModels(Integer picture) {
        this.picture = picture;
    }

    public Integer getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }
}
