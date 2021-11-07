package driver.brands;

import driver.MainMenu;
import driver.admin.ActivityHelper;
import driver.admin.BrandHelper;
import driver.admin.CustomerHelper;
import driver.admin.RewardHelper;

import java.util.Scanner;

public class BrandLandingPage {
    public static void run(String brand_id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("***************************   BRAND HOME   ***************************************");
        System.out.println("**********************************************************************************");

        String input_options = "1. Add Loyalty Program\n2. Add RE rules\n3. Update RE rules\n" +
                         "4. Add RR rules\n5. Update RR rules\n6. Validate Loyalty Program\n7. Logout\n";

        System.out.println(input_options);
        Integer chosen_option = scanner.nextInt();
        scanner.nextLine();
        switch(chosen_option) {
            case 1:
                LoyaltyProgramHelper.add(brand_id);
                break;
            case 2:
                ReRulesHelper.add(brand_id);
                break;
            case 3:
                System.out.print("Enter Brand Id: \n");
                String brandId = scanner.nextLine();
                ReRulesHelper.update(brandId);
                break;
            case 4:
                RrRulesHelper.add(brand_id);
                break;
            case 5:
                System.out.print("Enter Brand Id: ");
                String brandIdRR = scanner.nextLine();
                RrRulesHelper.update(brandIdRR);
                break;
            case 6:
                LoyaltyProgramHelper.validate(brand_id);
                break;
            case 7:
                  MainMenu.run();
        }

    }
}
