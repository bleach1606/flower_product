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
    private Boolean fiActive;

    private String name;

    private int price;

    private String description;

    private String avatar;

    @OneToOne
    private Category category;

//    @OneToMany
//    @JoinColumn(name = "flower_products_id", unique = false)
//    private List<Comment> commentList;
}
