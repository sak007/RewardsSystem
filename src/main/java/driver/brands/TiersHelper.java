package driver.brands;

import driver.dao.LoyaltyProgramDAO;
import driver.dao.TierDAO;
import driver.object.LoyaltyProgram;
import driver.object.Tier;

import java.util.Scanner;

public class TiersHelper {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        String test_brand_id = "1";
        String display_string = "Enter number of tiers \n";
        System.out.println(display_string);
        Integer number_of_tiers = scanner.nextInt();
        Tier[] tier = new Tier[number_of_tiers];
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
        int i;

        //Get names of each tier
        String name_of_tier = "";
        for (i = 0; i < number_of_tiers; i++) {      //Low to High
            display_string = "Enter name of  of tier:" + i + 1 + " \n";
            System.out.println(display_string);
            name_of_tier = scanner.next();
            tier[i].setLp_program_id(loyaltyProgram.getLpId());
            tier[i].setName(name_of_tier);
        }

        //Get points required for each tier
        Integer pointsRequired;
        for (i = 0; i < number_of_tiers; i++) {      //Low to High
            display_string = "Enter points required for tier:" + i + 1 + " \n";
            System.out.println(display_string);
            pointsRequired = scanner.nextInt();
            tier[i].setPoints(pointsRequired);
        }

        //Get multiplier for each tier
        Integer multiplier;
        for (i = 0; i < number_of_tiers; i++) {      //Low to High
            display_string = "Enter multiplier for tier:" + i + 1 + " \n";
            System.out.println(display_string);
            multiplier = scanner.nextInt();
            tier[i].setMultiplier(multiplier);
        }

        //Save all the tiers created for this TieredLP
        for(i = 0; i < number_of_tiers; i++) {
            TierDAO.saveData(tier[i]);
        }
    }
}
