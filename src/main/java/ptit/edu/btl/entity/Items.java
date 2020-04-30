package ptit.edu.btl.entity;

import java.io.Serializable;

public class Items implements Serializable {

    private String name;
    private int price;
    private String description;
    private String avatar;

    public Items(String name, int price, String description, String avatar) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.avatar = avatar;
    }

    public Items() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
