package tatar.mackshchim.sm.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 128)
    private String header;

    private String text;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private Date postTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToMany(mappedBy = "posts",
    cascade = CascadeType.PERSIST,
    fetch = FetchType.EAGER)
    private List<Image> images;

    public enum State {
        POSTED,
        DELETED
    }

}
