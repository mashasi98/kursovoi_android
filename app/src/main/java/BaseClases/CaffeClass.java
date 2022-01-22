package BaseClases;

public class CaffeClass {
    private String  imagePath,adress,time,contacts,id;
    public CaffeClass() {
    }

    public CaffeClass(String id,String imagePath, String adress, String time, String contacts) {
        this.imagePath = imagePath;
        this.adress = adress;
        this.time = time;
        this.contacts = contacts;
        this.id=id;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
