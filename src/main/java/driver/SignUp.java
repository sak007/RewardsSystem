package driver;

import driver.admin.BrandHelper;
import driver.admin.CustomerHelper;
import driver.dao.UserDAO;
import driver.object.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SignUp {
    public static void run(){
        System.out.println("**********************************************************************************");
        System.out.println("******************************   SIGNUP   ****************************************");
        System.out.println("**********************************************************************************");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Brand SignUp\n2. Customer SignUp\n3. Go Back\n");
        Integer option = scanner.nextInt();
        switch(option) {
            case 1:
                BrandHelper.signUp();
                break;
            case 2:
                CustomerHelper.signUp();
                break;
            case 3:
                MainMenu.run();
                break;
        }
    }
}
