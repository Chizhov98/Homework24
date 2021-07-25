package InicialiseDB;

import Dao.DefaultDao;
import Entity.*;
import Utils.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class FirstInit {
    private DefaultDao dao = Config.getDao();
    private Random random = new Random();

    public List<Order> initAllDb(int count) {
        Artist[] artists = createArtists();
        Customer[] customers = createCustomers();
        List<Album> albums = createAlbums(100);
        initTracks(15000, artists, albums);
        return initOrders(customers, albums, count);
    }

    private Artist[] createArtists() {
        Artist[] artists = new Artist[5];
        for (int i = 0; i < artists.length; i++) {
            artists[i] = new Artist();
            artists[i].setTracks(new HashSet<>());
            artists[i].setAlbums(new HashSet<>());
            artists[i].setId(i + 1);
        }
        artists[0].setFirstName("Klavdia");
        artists[0].setLastName("Koka");
        artists[1].setFirstName("Elvis");
        artists[1].setLastName("Presley");
        artists[2].setFirstName("Bob");
        artists[2].setLastName("Dilan");
        artists[3].setFirstName("Chuck");
        artists[3].setLastName("Berry");
        artists[4].setFirstName("James");
        artists[4].setLastName("Brown");
        return artists;
    }

    private List<Order> initOrders(Customer[] customers, List<Album> albums, int count) {
        List<Order> orders = new ArrayList<>();
        Order order;
        for (int i = 0; i < count; i++) {
            order = new Order();
            order.setAlbums(new ArrayList<Album>());
            order.getAlbums().add(albums.
                    get(random.nextInt(albums.size())));
            order.setCustomers(customers
                    [random.nextInt(customers.length)]);
            order.getCustomers().getOrders().add(order);
            orders.add(order);
        }
        return orders;
    }

    private Customer[] createCustomers() {

        Customer[] customers = new Customer[5];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer();
            customers[i].setOrders(new HashSet<>());
            customers[i].setId(i + 1);
        }
        customers[0].setFirstName("Artem");
        customers[0].setLastName("Chizhov");
        ;
        customers[1].setFirstName("Morgan");
        customers[1].setLastName("Freeman");
        ;
        customers[2].setFirstName("Jorg");
        customers[2].setLastName("Washington");
        ;
        customers[3].setFirstName("Bill");
        customers[3].setLastName("Gates");
        ;
        customers[4].setFirstName("Elisabet");
        customers[4].setLastName("Turner");

        return customers;
    }

    private void initTracks(int count, Artist[] artists, List<Album> albums) {
        Track[] tracks = new Track[count];
        for (int i = 0; i < tracks.length; i++) {
            tracks[i] = new Track();
            tracks[i].setId(i + 1);
            tracks[i].setArtist(artists
                    [random.nextInt(artists.length)]);
            tracks[i].getArtist().getTracks().
                    add(tracks[i]);
            tracks[i].setName("track_" + i + 1);
            tracks[i].setAlbum(albums.get(
                    random.nextInt(albums.size())));
            tracks[i].getAlbum().getTracks()
                    .add(tracks[i]);
            tracks[i].getAlbum().getArtists()
                    .add(tracks[i].getArtist());
        }

    }

    private List<Album> createAlbums(int count) {
        ArrayList<Album> albums = new ArrayList<>();
        Album album;

        for (int i = 1; i < count + 1; i++) {
            album = new Album();
            album.setId(i);
            album.setName("some_name_" + i);
            album.setPrice(random.nextDouble() + 50.5);

            album.setTracks(new HashSet<>());
            album.setArtists(new HashSet<>());
            albums.add(album);
        }
        return albums;
    }
}
