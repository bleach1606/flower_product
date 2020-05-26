package ptit.edu.btl.entity;

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

    @OneToOne
    private Payment payment;

    @ManyToOne
    private Users users;

    @Transient
    private Address address;

    @Transient
    private List<CartDetail> cartDetailList;

    public OrderBill() {
        cartDetailList = new ArrayList<>();
    }



}
