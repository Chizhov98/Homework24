package InicialiseDB;

import Dao.DefaultDao;
import Entity.*;
import Utils.Config;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class FirstInit {
    private DefaultDao dao = Config.getDao();
    private Random random = new Random();
    private int tracksCount = 30;
    private int albumsTrack = 10;

    public List<Order> initAllDb(int count) {
        List<Track> tracks = initTracks(tracksCount);
        List<Album> albums = createAlbums(albumsTrack, tracks);
        Artist[] artists = createArtists();
        createArtistAlbumLink(albums, artists);
        Customer[] customers = createCustomers();


        return initOrders(customers, albums, count);
    }

    private void createArtistAlbumLink(List<Album> albums, Artist[] artists) {

        for (int i = 0, temp; i < albums.size(); i++) {
            temp = random.nextInt(artists.length);
            albums.get(i).getArtists().add(artists[temp]);
            artists[temp].getAlbums().add(albums.get(i));
        }
        for (int i = 0; i < albums.size(); i++) {
            dao.update(albums.get(i));
        }
        for (int i = 0; i < artists.length; i++) {
            dao.update(artists[i]);
        }

    }

    private Artist[] createArtists() {
        Artist[] artists = new Artist[5];
        for (int i = 0; i < artists.length; i++) {
            artists[i] = new Artist();
            artists[i].setAlbums(new ArrayList<>());
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
        for (int i = 0; i < artists.length; i++) {
            dao.create(artists[i]);
        }
        return artists;
    }

    private List<Order> initOrders(Customer[] customers, List<Album> albums, int count) {
        List<Order> orders = new ArrayList<>();
        Order order;
        for (int i = 0; i < count; i++) {
            order = new Order();
            order.setAlbums(new ArrayList<>());
            for (int j = 0; j < random.nextInt(5); j++) {
                order.getAlbums().add(albums.
                        get(random.nextInt(albums.size())));
            }
            order.setCustomerId(customers
                    [random.nextInt(customers.length)].getId());
            customers[order.getCustomerId() - 1].getOrders().add(order);
            orders.add(order);
            dao.create(order);
            dao.update(customers[order.getCustomerId()-1]);
        }
        return orders;
    }

    private Customer[] createCustomers() {

        Customer[] customers = new Customer[5];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer();
            customers[i].setOrders(new ArrayList<>());
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

        for (int i = 0; i < customers.length; i++) {
            dao.create(customers[i]);
        }

        return customers;
    }

    private List<Track> initTracks(int count) {
        List<Track> tracks = new ArrayList<>();
        Track track;
        for (int i = 0; i < count; i++) {
            track = new Track();
            track.setId(i + 1);
            track.setName("Track" + i);
            dao.create(track);
            tracks.add(track);
        }
        return tracks;
    }

    private List<Album> createAlbums(int count, List<Track> tracks) {
        ArrayList<Album> albums = new ArrayList<>();
        Album album;

        for (int i = 1; i < count + 1; i++) {
            album = new Album();
            album.setId(i);
            album.setName("some_name_" + i);
            album.setPrice(random.nextDouble() * 100);

            album.setTracks(new ArrayList<>());
            for (int j = 0, temp; j < random.nextInt(6); j++)
                if (tracks.size() > 0) {
                    temp = random.nextInt(tracks.size());
                    album.getTracks().add(tracks.get(temp));
                    tracks.remove(temp);
                }
            album.setArtists(new ArrayList<>());
            albums.add(album);
            dao.create(album);
        }
        return albums;
    }
}
