import Dao.DefaultDao;
import Entity.Order;
import InicialiseDB.FirstInit;
import Utils.Config;

import java.util.List;

public class Start {

    private static DefaultDao dao = Config.getDao();
    public static void main(String[] args) {
        List<Order> orders = new FirstInit().initAllDb(5);
        Config.getNavigator().mainMenu();
    }


}
