package BaseClases;

import java.util.ArrayList;

public class UsersClass {
    private int id;
    private String phoneNumber,name;
    private ArrayList<ItemsClass> preferences;
    private ArrayList<PurchasesClass> purchases;

    public UsersClass(int id, String phoneNumber, String name) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ItemsClass> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<ItemsClass> preferences) {
        this.preferences = preferences;
    }

    public ArrayList<PurchasesClass> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<PurchasesClass> purchases) {
        this.purchases = purchases;
    }
}
