package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "flower_products")
@Data
public class FlowerProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean fiActive;

    private String name;

    private int price;

    private String description;

    private String avatar;

    @Column(name = "category_id")
    private int categoryId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id", insertable = false, updatable = false)
    private List<Cart> cartList;

}
