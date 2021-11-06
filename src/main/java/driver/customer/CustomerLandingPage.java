package driver.customer;

import driver.MainMenu;
import driver.admin.ActivityHelper;
import driver.admin.BrandHelper;
import driver.admin.CustomerHelper;
import driver.admin.RewardHelper;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class CustomerLandingPage {
    public static void run(String customerId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("***************************   Customer HOME   ***************************************");
        System.out.println("**********************************************************************************");

        System.out.println("1. Enroll in Loyalty Program\n2. Reward Activities\n3. View Wallet\n4. Redeem Points\n5. Logout");
        Integer option = scanner.nextInt();
        switch(option) {
            case 1:
                CustomerLoyaltyHelper.run(customerId);
                break;
            case 2:
                CustomerHelper.run();
                break;
            case 3:
                WalletHelper.run(customerId);
                break;
            case 4:
                System.out.print("Enter Customer Id: ");
                //Long customerId = scanner.nextLong();
                //CustomerHelper.show(customerId);
                break;
            case 5:
                MainMenu.run();
                break;

        }
    }
}
