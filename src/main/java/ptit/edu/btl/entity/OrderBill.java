package ptit.edu.btl.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_bill")
@Data
public class OrderBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private int status;

    private Date orderDate;

    private String receiverName;

    private String receiverTel;

    private String receiverAddress;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = true,
    foreignKey = @ForeignKey(name = "pay_bill"))
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false,
    foreignKey = @ForeignKey(name = "user_bill"))
    private Users users;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true,
    foreignKey = @ForeignKey(name = "order_add"))
    private Address address;

    @OneToMany
    @JoinColumn()
    private List<CartDetail> cartDetailList;

    public OrderBill() {
        cartDetailList = new ArrayList<>();
    }
}
