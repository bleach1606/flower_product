package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_detail")
@Data
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private int number;

    @OneToOne
    private FlowerProducts flowerProduct;

    @ManyToOne
    @JoinColumn(name = "order_bill_id", nullable = false,
        foreignKey = @ForeignKey(name = "cart_detail_order_bill"))
    private OrderBill orderBill;
}
