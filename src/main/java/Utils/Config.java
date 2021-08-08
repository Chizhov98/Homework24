package Utils;

import Dao.DefaultDao;
import UserInteface.Menu.Console.ConsoleMenu;
import UserInteface.Menu.Menu;
import UserInteface.Navigator.Console.ConsoleMenuNavigator;
import UserInteface.Navigator.MenuNavigator;
import lombok.Getter;
import lombok.Setter;

public class Config {
    @Getter
    private   static DefaultDao dao = new DefaultDao();
    @Getter
    @Setter
    private static Menu menu = new ConsoleMenu();
    @Getter
    @Setter
    private MenuNavigator navigator = new ConsoleMenuNavigator();

}
