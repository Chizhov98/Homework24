package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;



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
    HashSet<Track> tracks;

    @ManyToMany(mappedBy = "artists")
    private HashSet<Album> albums;

    public Artist() {
    }

}
