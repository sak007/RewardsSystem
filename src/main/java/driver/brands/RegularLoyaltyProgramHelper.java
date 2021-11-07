package driver.brands;

import driver.dao.ActivitiesForLoyaltyProgramDAO;
import driver.dao.ActivityDAO;
import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.LoyaltyProgram;

import java.util.Scanner;

public class RegularLoyaltyProgramHelper {
    public static void display(String brand_id) {
        Scanner scanner = new Scanner(System.in);

        String display_string = "Choose from one of the options below:\n" + "1)Activity Types\n" +
                                "2)Reward Types\n" + "3)Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        switch (input){
            case 1:
                ActivityTypesHelper.display("Regular", brand_id);
                break;
            case 2:
                RewardTypesHelper.display("Regular", brand_id);
                break;
            case 3:
                //Go back
                LoyaltyProgramHelper.add(brand_id);
                break;
        }

    }
}
