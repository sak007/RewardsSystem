package driver;


import driver.admin.AdminLandingPage;
import driver.brands.BrandLandingPage;

import java.util.Scanner;

public class Login {
    public static void run() {
        System.out.println("**********************************************************************************");
        System.out.println("******************************   LOGIN   *****************************************");
        System.out.println("**********************************************************************************");

        displayLoginPage();
    }

    public static void displayLoginPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String usr = scanner.next();
        System.out.print("Enter password: ");
        String pwd = scanner.next();
        System.out.println("1. Sign In\n2. Go Back\n");
        Integer option = scanner.nextInt();
        switch(option) {
            case 1:
                System.out.println("Sign In");
                Boolean isValid = Helper.validate(usr, pwd);
                if (isValid) {
                    redirectToPage(usr);
                } else {
                    System.out.print("Invalid username/password. Try again");
                    displayLoginPage();
                }
                break;
            case 2:
                System.out.println("Go Back");
                MainMenu.run();
                break;
        }
    }

    public static void redirectToPage(String usr) {
        UserType type = Helper.getUserType(usr);
        switch (type) {
            case ADMIN:
                AdminLandingPage.run();
                break;
            case BRAND:
                BrandLandingPage.run();
                break;
            case CUSTOMER:
                // TODO: 10/29/21
                break;
        }
    }
}