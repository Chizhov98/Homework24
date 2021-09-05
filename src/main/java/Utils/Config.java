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
    private static DefaultDao dao = new DefaultDao();
    @Getter
    private static Menu menu = new ConsoleMenu();
    @Getter
    private static MenuNavigator navigator = new ConsoleMenuNavigator();

}
