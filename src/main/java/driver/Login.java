package driver;


import driver.admin.AdminLandingPage;
import driver.brands.BrandLandingPage;
import driver.customer.CustomerLandingPage;
import driver.dao.CustomerDAO;


import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public static void run() throws SQLException {
        System.out.println("**********************************************************************************");
        System.out.println("******************************   LOGIN   *****************************************");
        System.out.println("**********************************************************************************");

        displayLoginPage();
    }

    public static void displayLoginPage() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String usr = scanner.next();
        System.out.print("Enter password: ");
        String pwd = scanner.next();
        System.out.println("1. Sign In\n2. Go Back\n");
        Integer option = scanner.nextInt();
        switch(option) {
            case 1:
                Boolean isValid = Helper.validate(usr, pwd);
                if (isValid) {
                    redirectToPage(usr);
                } else {
                    System.out.println("Invalid username/password. Try again");
                    displayLoginPage();
                }
                break;
            case 2:
                System.out.println("Go Back");
                MainMenu.run();
                break;
        }
    }

    public static void redirectToPage(String usr) throws SQLException {
        UserType type = Helper.getUserType(usr);
        switch (type) {
            case ADMIN:
                AdminLandingPage.run();
                break;
            case BRAND:
                BrandLandingPage.run();
                break;
            case CUSTOMER:
                String customerId= CustomerDAO.getCustomerIdByUserName(usr);
                CustomerLandingPage.run(customerId);

                break;
        }
    }
}
