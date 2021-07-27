package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    List<Track> tracks;

    @ManyToMany(mappedBy = "artists")
    private List<Album> albums;

    public Artist() {
    }

}
