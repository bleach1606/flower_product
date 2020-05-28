package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean active;

    public Users() {
    }

    private String username;

    private String password;

    private String role;


    @OneToOne
    @JoinColumn(name = "people_id", nullable = false,
        foreignKey = @ForeignKey(name = "user_people"))
    private People people;

}
