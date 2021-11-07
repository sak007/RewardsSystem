package driver;

import driver.SignUp;
import driver.brands.BrandLandingPage;
import driver.dao.ShowQueriesDAO;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu {

    public static void main(String[] args) throws SQLException {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("******************************   HOME   ******************************************");
        System.out.println("**********************************************************************************");
        String id= UUID.randomUUID().toString().replace("-","");

        System.out.println("1. Login\n2. Sign Up\n3.Show Queries\n4. Exit");
        Integer option = scanner.nextInt();
        switch(option) {
            case 1:
                Login.run();
                break;
            case 2:
                SignUp.run();
                break;
            case 3:
                ShowQueriesDAO.run();
                break;
            case 4:
                System.out.println("Exit");
                break;
            case 5:
                System.out.println("Go To Brand Landing Page");
                BrandLandingPage.run();
                break;
            default:
                System.out.println("Invalid option");
                run();
        }
    }

}



