package ptit.edu.btl.entity;

import javax.persistence.*;

@Entity
@Table(name = "flower_products")
public class FlowerProducts extends Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean fiActive;

    public FlowerProducts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlowerProducts(String name, int price, String description, String avatar, int id, Boolean fiActive) {
        super(name, price, description, avatar);
        this.id = id;
        this.fiActive = fiActive;
    }

    public Boolean getFiActive() {
        return fiActive;
    }

    public void setFiActive(Boolean fiActive) {
        this.fiActive = fiActive;
    }
}
