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

    @ManyToMany(mappedBy = "albums", cascade = CascadeType.ALL)
    List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "albums_artists",
            joinColumns = @JoinColumn(name = "albums_id"),
            inverseJoinColumns = @JoinColumn(name = "artists_id")
    )
    List<Artist> artists;

    @OneToMany(cascade = CascadeType.ALL)
    List<Track> tracks;

    public Album() {
    }
}
