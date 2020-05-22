package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orderbill")
@Data
public class OrderBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private int status;

    private Date oderDate;

    private int users_id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", insertable = false, updatable = false)
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id", insertable = false, updatable = false)
    private List<Cart> cartList;

    public OrderBill() {
        cartList = new ArrayList<>();
    }
}
