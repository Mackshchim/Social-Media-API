package tatar.mackshchim.sm.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Enumerated(value = EnumType.STRING)
    private RelationType relationType;

    @ManyToOne(fetch = FetchType.EAGER)
    private User relatingUser;


    @ManyToOne(fetch = FetchType.EAGER)
    private User relatedUser;

    public enum RelationType {
        FOLLOWING,
        FRIEND,
        RELATION_DELETED
    }

    public boolean hasNoRelation() {
        return relationType.equals(RelationType.RELATION_DELETED);
    }

    public boolean hasRelation() {
        return !hasNoRelation();
    }

}
