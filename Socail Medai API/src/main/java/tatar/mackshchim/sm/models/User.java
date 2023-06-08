package tatar.mackshchim.sm.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true)
    private String email;
    private String hashPassword;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL)
    private List<Post> usersPosts;

    @OneToMany(mappedBy = "relatingUser",
            cascade = CascadeType.ALL)
    private List<UserRelation> relations;

    @OneToMany(mappedBy = "sender",
    cascade = CascadeType.ALL)
    private List<Message> messages;
}
