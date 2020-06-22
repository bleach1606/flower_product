package ptit.edu.btl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private Date date;

    private String avatar;

    private boolean isCheck;

    @ManyToOne
    @JoinColumn( name = "user_id", foreignKey = @ForeignKey(name = "notification_user"))
    private Users users;

}
