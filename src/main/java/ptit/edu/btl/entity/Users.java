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
    private Boolean fiActive;

    public Users() {
    }

    public Boolean getFiActive() {
        return fiActive;
    }

    public void setFiActive(Boolean fiActive) {
        this.fiActive = fiActive;
    }

    private String username;

    private String password;

    private String role;

//    private int people_id;

    @OneToOne
//    @JoinColumn(columnDefinition = "people_id", referencedColumnName = "id", insertable = false, updatable = false)
    private People people;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Comment> listComment;

}
