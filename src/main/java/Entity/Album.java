package Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToMany(mappedBy = "albums")
    List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    List<Artist> artists;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    List<Track> tracks;

    public Album() {
    }
}
