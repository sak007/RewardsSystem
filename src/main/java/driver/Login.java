package driver;


import driver.admin.AdminLandingPage;
import driver.brands.BrandLandingPage;
import driver.dao.DBHelper;
import driver.object.Activity;
import driver.object.Brand;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import driver.customer.CustomerLandingPage;
import driver.dao.CustomerDAO;


import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public static void run() {
        System.out.println("**********************************************************************************");
        System.out.println("******************************   LOGIN   *****************************************");
        System.out.println("**********************************************************************************");

        displayLoginPage();
    }

    public static void displayLoginPage()  {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String usr = scanner.nextLine();
        System.out.print("Enter password: ");
        String pwd = scanner.nextLine();
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

    public static void redirectToPage(String usr)  {
        UserType type = Helper.getUserType(usr);
        switch (type) {
            case ADMIN:
                AdminLandingPage.run();
                break;
            case BRAND:
                Brand brand = new Brand();
                try {
                    String brandQuery = "select * from brand where user_name='" + usr + "'";
                    List<Object[]> rs = DBHelper.executeQueryUpdated(brandQuery);
                    for(Object[] object:rs) {
                        brand.setId((String) object[0]);
                    }
                }
                catch(SQLException e){
                    System.out.println("Unable to load Brand");
                    System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
                }
                BrandLandingPage.run(brand.getId());
                break;
            case CUSTOMER:
                String customerId= CustomerDAO.getCustomerIdByUserName(usr);
                CustomerLandingPage.run(customerId);
                break;
            default:
                System.out.println("Invalid user!");
                run();
                break;
        }
    }
}
