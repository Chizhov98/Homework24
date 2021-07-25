package Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;


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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "albums_orders",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    HashSet<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    HashSet<Artist> artists;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    HashSet<Track> tracks;

    public Album() {
    }
}
