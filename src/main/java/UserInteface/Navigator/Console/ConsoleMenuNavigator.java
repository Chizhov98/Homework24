package UserInteface.Navigator.Console;

import Entity.*;
import UserInteface.Menu.Menu;
import UserInteface.Navigator.MenuNavigator;
import Utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuNavigator implements MenuNavigator {
    private final Scanner in = new Scanner(System.in);
    private final Menu menu = Config.getMenu();

    private int scanInt() {
        int num = in.nextInt();
        in.nextLine();
        return num;
    }

    private String scanLine() {
        return in.nextLine().trim();
    }

    public void mainMenu() {
        menu.mainMenu();
        switch (scanInt()) {
            case 1 -> createMenu();
            case 2 -> readMenu();
            case 3 -> updateMenu();
            case 4 -> deleteMenu();
            case 0 -> exit();
            default -> {
                System.out.println("Incorrect chose, try again");
                mainMenu();
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void createMenu() {
        menu.createMenu();
        switch (scanInt()) {
            case 1 -> {
                Config.getDao().create(createAlbum());
                menu.success();
            }
            case 2 -> {
                Config.getDao().create(createArtist());
                menu.success();
            }
            case 3 -> {
                Config.getDao().create(createCustomer());
                menu.success();
            }
            case 4 -> {
                Config.getDao().create(createOrder());
                menu.success();
            }
            case 5 -> {
                Config.getDao().create(createTrack());
                menu.success();
            }
            case 0 -> mainMenu();
            default -> {
                System.out.println("Incorrect chose, try again");
                mainMenu();
            }
        }
        mainMenu();
    }

    @Override
    public void updateMenu() {
        menu.updateMenu();
        int choseObj = scanInt();
        menu.enterId();
        int id = scanInt();
        switch (choseObj) {
            case 1:
                Album album = Config.getDao().read(new Album(), id);
                Config.getDao().update(updateAlbum(album));
                break;
            case 2:
                Artist artist = Config.getDao().read(new Artist(), id);
                Config.getDao().update(updateArtist(artist));
                break;
            case 3:
                Customer customer = Config.getDao().read(new Customer(), id);
                Config.getDao().update(updateCustomer(customer));
                break;
            case 4:
                Order order = Config.getDao().read(new Order(), id);
                Config.getDao().update(updateOrder(order));
                break;
            case 5:
                Track track = Config.getDao().read(new Track(), id);
                Config.getDao().update(updateTrack(track));
                break;
            case 0:
                mainMenu();
                break;

            default:
                System.out.println("Incorrect chose, try again");
                mainMenu();
        }
    }

    @Override
    public void deleteMenu() {
        menu.deleteMenu();
        int choseObj = scanInt();
        menu.enterId();
        int id = scanInt();
        switch (choseObj) {
            case 1 -> Config.getDao().delete(new Album(), id);
            case 2 -> Config.getDao().delete(new Artist(), id);
            case 3 -> Config.getDao().delete(new Customer(), id);
            case 4 -> Config.getDao().delete(new Order(), id);
            case 5 -> Config.getDao().delete(new Track(), id);
            case 0 -> mainMenu();
            default -> {
                System.out.println("Incorrect chose, try again");
                mainMenu();
            }
        }
    }

    @Override
    public void readMenu() {
        menu.readMenu();
        int choseObj = scanInt();
        menu.enterId();
        int id = scanInt();
        switch (choseObj) {
            case 1 -> System.out.println(Config.getDao().read(new Album(), id).toString());
            case 2 -> System.out.println(Config.getDao().read(new Artist(), id).toString());
            case 3 -> System.out.println(Config.getDao().read(new Customer(), id).toString());
            case 4 -> System.out.println(Config.getDao().read(new Order(), id).toString());
            case 5 -> System.out.println(Config.getDao().read(new Track(), id).toString());
            case 0 -> mainMenu();
            default -> {
                System.out.println("Incorrect chose, try again");
                mainMenu();
            }
        }
    }

    private Album createAlbum() {
        Album album = new Album();
        menu.createAlbum(1);
        album.setName(scanLine());
        menu.createAlbum(2);
        album.setPrice(Double.parseDouble(scanLine()));
        menu.createAlbum(3);
        List<Artist> artists = new ArrayList<>();
        for (int id : transformIdLineToArray(scanLine())) {
            if (id != 0) {
                artists.add(Config.getDao().read(new Artist(), id));
            }
        }
        album.setArtists(artists);
        menu.createAlbum(4);
        List<Track> tracks = new ArrayList<>();
        for (int id : transformIdLineToArray(scanLine())) {
            if (id != 0) {
                tracks.add(Config.getDao().read(new Track(), id));
            }
        }
        album.setTracks(tracks);

        return album;
    }

    private int[] transformIdLineToArray(String line) {
        String[] arr = line.trim().split(",");
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    private Artist createArtist() {
        Artist artist = new Artist();
        menu.createArtist(1);
        artist.setFirstName(scanLine());
        menu.createArtist(2);
        artist.setLastName(scanLine());
        artist.setAlbums(new ArrayList<>());
        return artist;
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        menu.createCustomer(1);
        customer.setFirstName(scanLine());
        menu.createCustomer(2);
        customer.setLastName(scanLine());
        customer.setOrders(new ArrayList<>());
        return customer;
    }

    private Order createOrder() {
        Order order = new Order();
        menu.createOrder(1);
        order.setCustomerId(scanInt());
        menu.createOrder(2);
        List<Album> albums = new ArrayList<>();
        for (int id : transformIdLineToArray(scanLine())) {
            if (id != 0) {
                albums.add(Config.getDao().read(new Album(), id));
            }
        }
        order.setAlbums(albums);
        return order;
    }

    private Track createTrack() {
        Track track = new Track();
        menu.createTrack();
        track.setName(scanLine());
        return track;
    }

    private Album updateAlbum(Album album) {
        menu.updateAlbum();
        switch (scanInt()) {
            case 1 -> {
                menu.enterNewValue();
                album.setName(scanLine());
            }
            case 2 -> {
                menu.enterNewValue();
                album.setPrice(Double.parseDouble(scanLine()));
            }
            case 3 -> {
                menu.enterNewValue();
                album.getOrders().add(Config.getDao().read(new Order(), scanInt()));
            }
            case 4 -> {
                menu.enterNewValue();
                album.getArtists().add(Config.getDao().read(new Artist(), scanInt()));
            }
            case 5 -> {
                menu.enterNewValue();
                album.getTracks().add(Config.getDao().read(new Track(), scanInt()));
            }
            case 0 -> {
                return album;
            }
            default -> System.out.println("Incorrect chose, try again");
        }
        return updateAlbum(album);
    }

    private Artist updateArtist(Artist artist) {
        menu.updateArtist();
        switch (scanInt()) {
            case 1 -> {
                menu.enterNewValue();
                artist.setFirstName(scanLine());
            }
            case 2 -> {
                menu.enterNewValue();
                artist.setLastName(scanLine());
            }
            case 0 -> {
                return artist;
            }
            default -> System.out.println("Incorrect chose, try again");
        }
        return updateArtist(artist);
    }

    private Customer updateCustomer(Customer customer) {
        menu.updateArtist();
        switch (scanInt()) {
            case 1 -> {
                menu.enterNewValue();
                customer.setFirstName(scanLine());
            }
            case 2 -> {
                menu.enterNewValue();
                customer.setLastName(scanLine());
            }
            case 3 -> {
                menu.enterId();
                int id = scanInt();
                customer.getOrders().add(Config.getDao().read(new Order(), id));
            }
            case 0 -> {
                return customer;
            }
            default -> System.out.println("Incorrect chose, try again");
        }
        return updateCustomer(customer);
    }

    private Order updateOrder(Order order) {
        menu.updateArtist();
        switch (scanInt()) {
            case 1 -> {
                menu.enterNewValue();
                order.setCustomerId(scanInt());
            }
            case 2 -> {
                menu.enterNewValue();
                order.getAlbums().add(Config.getDao().read(new Album(), scanInt()));
            }
            case 0 -> {
                return order;
            }
            default -> System.out.println("Incorrect chose, try again");
        }
        return updateOrder(order);
    }

    private Track updateTrack(Track track) {
        menu.updateArtist();
        switch (scanInt()) {
            case 1 -> {
                menu.enterNewValue();
                track.setName(scanLine());
            }
            case 0 -> {
                return track;
            }
            default -> System.out.println("Incorrect chose, try again");
        }
        return updateTrack(track);
    }
}

