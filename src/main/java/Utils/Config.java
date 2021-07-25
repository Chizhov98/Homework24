package Utils;

import Dao.DefaultDao;
import lombok.Getter;

public class Config {
    @Getter
    public   static DefaultDao dao = new DefaultDao();

}
