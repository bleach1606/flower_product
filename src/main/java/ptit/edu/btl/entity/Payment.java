package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double money;
    private Time timeOfTransaction;
    private String kind;

    @Column(nullable = false)
    private Boolean fiActive;

    @OneToOne
    private Users users;
}
