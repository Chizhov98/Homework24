package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "albums_orders",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Album> albums;

    public Order() {
    }
}
