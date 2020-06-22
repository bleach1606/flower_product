package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "people")
@Data
public class People implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    private String firstName;

    private String lastName;

    private String address;

    private Date birthday;

    private String sex;

    private String job;

    private String email;

    private String phoneNumber;

    private String avatar;

}
