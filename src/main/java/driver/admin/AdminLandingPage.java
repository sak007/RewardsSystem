package driver.admin;

import driver.MainMenu;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminLandingPage {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("***************************   ADMIN HOME   ***************************************");
        System.out.println("**********************************************************************************");

        System.out.println("1. Add Brand\n2. Add Customer\n3. Show Brand's Info\n4. Show Customer's Info\n5. Add Activity Type\n6. Add Reward Type\n7. Logout");
        Integer option = scanner.nextInt();
        scanner.nextLine();
        switch(option) {
            case 1:
                BrandHelper.run();
                break;
            case 2:
                CustomerHelper.run();
                break;
            case 3:
                System.out.print("Enter Brand Id: ");
                String brandId = scanner.nextLine();
                BrandHelper.show(brandId);
                break;
            case 4:
                System.out.print("Enter Customer Id: ");
                String customerId = scanner.nextLine();
                CustomerHelper.show(customerId);
                break;
            case 5:
                ActivityHelper.run();
                break;
            case 6:
                RewardHelper.run();
                break;
            case 7:
                MainMenu.run();
        }
    }
}
