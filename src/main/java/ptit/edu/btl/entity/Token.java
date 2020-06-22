package ptit.edu.btl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String token;

    private Date expiresAt;

    @Transient
    @JsonIgnore
    public boolean isExpires() {
        //Compare date to expiresAt
        return false;
    }
}
