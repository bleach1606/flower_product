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


}
