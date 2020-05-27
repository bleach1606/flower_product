package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flower_products")
@Data
public class FlowerProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private String name;

    private int price;

    private String description;

    private String avatar;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false,
        foreignKey = @ForeignKey(name = "flower_products_category"))
    private Category category;
}
