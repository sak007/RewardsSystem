package driver.brands;

import driver.MainMenu;
import driver.admin.ActivityHelper;
import driver.admin.BrandHelper;
import driver.admin.CustomerHelper;
import driver.admin.RewardHelper;

import java.util.Scanner;

public class LandingPage {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("***************************   BRAND HOME   ***************************************");
        System.out.println("**********************************************************************************");

        String input_options = "1. Add Loyalty Program\n2. Add RE rules\n3. Update RE rules\n" +
                         "4. Add RR rules\n5. Update RR rules\n6. Validate Loyalty Program\n7. Logout\n";

        System.out.println(input_options);
        Integer chosen_option = scanner.nextInt();
        switch(chosen_option) {
            case 1:
                LoyaltyProgramHelper.add();
                break;
            case 2:
                ReRulesHelper.add();
                break;
            case 3:
//                System.out.print("Enter Brand Id: ");
//                Long brandId = scanner.nextLong();
//                ReRulesHelper.update(brandId);
                break;
            case 4:
//                System.out.print("Enter Customer Id: ");
//                Long customerId = scanner.nextLong();
//                RrRulesHelper.add(customerId);
                break;
            case 5:
//                RrRulesHelper.update();
                break;
            case 6:
//                LoyaltyProgramHelper.update();
                break;
            case 7:
//                MainMenu.run();
        }

    }
}
