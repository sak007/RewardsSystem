package driver;

import driver.dao.UserDAO;
import driver.object.User;

import java.sql.SQLException;
import java.util.List;

public class Helper {

    public static Boolean validate(String usr, String pwd) {
        return UserDAO.validate(usr, pwd);
    }

    public static UserType getUserType(String usr) {
        return(UserDAO.getType(usr));
    }
}
