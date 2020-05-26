package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;

    private int numOfStar;
    private long time;

    @Column(nullable = false)
    private Boolean fiActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_x"))
    private Users users;

    @ManyToOne
    @JoinColumn(name = "flower_products_id", nullable = false)
    private FlowerProducts flowerProducts;
}
