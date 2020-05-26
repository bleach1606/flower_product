package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orderbill")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private int status;

    private Date oderDate;


    @OneToOne
    private Users users;

    @OneToOne
    private Cart cart;
}
