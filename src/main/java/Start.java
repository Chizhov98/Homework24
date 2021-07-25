import Dao.DefaultDao;
import Entity.Customer;
import Entity.Order;
import InicialiseDB.FirstInit;
import Utils.Config;

import java.util.List;

public class Start {

    private static DefaultDao dao = Config.dao;
    public static void main(String[] args) {
        List<Order> orders = new FirstInit().initAllDb(1000000);

        for (int i = 0; i < orders.size() ; i++) {
            dao.create(orders.get(i));
        }
        System.out.println();
    }

}
