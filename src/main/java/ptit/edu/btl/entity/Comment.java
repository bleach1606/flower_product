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
    @Column(nullable = true)
    private int numOfStar;
    private long time;

    @Column(nullable = false)
    private Boolean fiActive;

    @ManyToOne
    private Users users;

    @ManyToOne
    private FlowerProducts flowerProducts;
}
