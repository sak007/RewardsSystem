package driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SignUp {
    public static void run() throws SQLException {
        System.out.println("**********************************************************************************");
        System.out.println("******************************   SIGNUP   ****************************************");
        System.out.println("**********************************************************************************");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Brand\n2. Customer\n");
        Integer option = scanner.nextInt();
        UserType type;
        switch(option) {
            case 1:
                type = UserType.BRAND;
                break;
            case 2:
                type = UserType.CUSTOMER;
                break;
        }
        System.out.println("1. SignUp\n2. Go Back\n");
        option = scanner.nextInt();
        switch(option) {
            case 1:

                break;
            case 2:
                MainMenu.run();
                break;
        }
    }
}
