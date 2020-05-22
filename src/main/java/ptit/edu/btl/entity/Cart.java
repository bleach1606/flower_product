package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private int number;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "flowerproducts_id")
    private int flowerProductsId;
}
