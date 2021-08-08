package UserInteface.Menu.Console;

import UserInteface.Menu.Menu;

public class ConsoleMenu implements Menu {

    @Override
    public void mainMenu() {
        System.out.println("Hello, please enter number of option\n" +
                "1. Create some object in DB\n" +
                "2. Read data from DB\n" +
                "3. Update some data in DB\n" +
                "4. Delete data  from DB\n" +
                "0. Exit");
    }

    @Override
    public void createMenu() {
        System.out.println("What object you want create?");
        choseEntityMenu();
    }

    @Override
    public void createArtist(int field) {
        switch (field) {
            case 1 -> System.out.println("Enter first name:");
            case 2 -> System.out.println("Enter last name:");
        }
    }

    @Override
    public void createAlbum(int field) {
        switch (field) {
            case 1 -> System.out.println("Enter name:");
            case 2 -> System.out.println("Enter price:");
            case 3 -> System.out.println("Enter id of artists( in format: (id,id,id,...,id))");
            case 4 -> System.out.println("Enter id of track( in format: (id,id,id,...,id))");
        }
    }

    @Override
    public void createOrder(int field) {
        switch (field) {
            case 1 -> System.out.println("Enter id of customer( only one id)");
            case 2 -> System.out.println("Enter id of albums(in format: (id,id,id,...,id))");
        }
    }

    @Override
    public void createTrack() {
        System.out.println("Enter name");
    }

    @Override
    public void createCustomer(int field) {
        switch (field) {
            case 1 -> System.out.println("Enter first name:");
            case 2 -> System.out.println("Enter last name:");
        }
    }

    @Override
    public void deleteMenu() {
        System.out.println("What object you want delete?");
        choseEntityMenu();
    }

    @Override
    public void updateMenu() {
        System.out.println("What object you want update?");
        choseEntityMenu();
    }

    @Override
    public void choseEntityMenu() {
        System.out.println("1: Album\n" +
                "2: Artist\n" +
                "3: Customer\n" +
                "4. Order\n" +
                "5. Track");
    }

    @Override
    public void readMenu() {
        System.out.println("What object you want get from DB?");
        choseEntityMenu();

    }

    public void enterId() {
        System.out.println("Enter id( only one id)");
    }

    @Override
    public void updateAlbum() {
        System.out.println("what do you want update?\n" +
                "1. Name\n" +
                "2. Price\n" +
                "3. Orders\n" +
                "4. Artists\n " +
                "5. Tracks"+
                "0.Return to main menu");
    }

    @Override
    public void updateArtist() {
        System.out.println("what do you want update?\n" +
                "1. First name\n" +
                "2. Last name\n" +
                "3. Albums"+
                "0.Return to main menu");
    }

    @Override
    public void updateCustomer() {
        System.out.println("what do you want update?\n" +
                "1. First name\n" +
                "2. Last name\n"+
                "0.Return to main menu");
    }

    @Override
    public void updateOrder() {
        System.out.println("what do you want update?\n" +
                "1.CustomerId\n" +
                "2. Albums\n"+
                "0.Return to main menu");
    }

    @Override
    public void updateTrack() {
        System.out.println("what do you want update?\n" +
                "1. Name\n" +
                "0.Return to main menu");
    }

    @Override
    public void enterNewValue() {
        System.out.println("Enter new value");
    }

    @Override
    public void success() {
        System.out.println("Success!!!");
    }
}
