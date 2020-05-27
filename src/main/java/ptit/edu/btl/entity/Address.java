package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private boolean active;
    private String nation;
    private String province;
    private String district;
    private String town;
    private String road;
    private int section;
//    @ManyToOne
//    @JoinColumn(name = "order_bill_id", nullable = false,
//       foreignKey = @ForeignKey(name = "address_order_bill"))
//    private OrderBill orderBill;
}
