package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bill")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean fiActive;

    @OneToOne
    private Order order;

    @OneToOne
    private Payment payment;
}
