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
    @JoinColumn(name = "flower_products_id", nullable = false,
        foreignKey = @ForeignKey(name = "cart_detail_fp"))
    private FlowerProducts flowerProduct;

//    @Column(nullable = false)
//    @JoinColumn(name = "order_bill_id", referencedColumnName = "id", table = "order_bill",
//    foreignKey = @ForeignKey(name = "__"))
//    @JoinColumn()

    @JoinColumn(name = "order_bill_id", nullable = false,
            referencedColumnName = "id", table = "order_bill",
            foreignKey = @ForeignKey(name = "___"))
    private int orderBillId;
}
