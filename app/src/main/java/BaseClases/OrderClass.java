package BaseClases;

import java.util.List;

public class OrderClass {
    private int totalAmount;
    private String date, time;
    private List<ShopingCardClass> shopingCardClasses;

    public OrderClass() {
    }

    public OrderClass(int totalAmount, String date, String time, List<ShopingCardClass> shopingCardClasses) {
        this.totalAmount = totalAmount;
        this.date = date;
        this.time = time;
        this.shopingCardClasses = shopingCardClasses;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ShopingCardClass> getShopingCardClasses() {
        return shopingCardClasses;
    }

    public void setShopingCardClasses(List<ShopingCardClass> shopingCardClasses) {
        this.shopingCardClasses = shopingCardClasses;
    }
}
