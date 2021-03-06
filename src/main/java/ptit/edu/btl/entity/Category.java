package ptit.edu.btl.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String avatar;

    @Column(nullable = false)
    private Boolean fiActive;

    public Boolean getFiActive() {
        return fiActive;
    }

    public void setFiActive(Boolean fiActive) {
        this.fiActive = fiActive;
    }

    public Category() {
        flowerProductsList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CATEGORY_ID")
    private List<FlowerProducts> flowerProductsList;
}
