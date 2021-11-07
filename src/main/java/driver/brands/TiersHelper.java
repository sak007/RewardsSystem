package driver.brands;

import driver.dao.LoyaltyProgramDAO;
import driver.dao.TierDAO;
import driver.object.LoyaltyProgram;
import driver.object.Tier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TiersHelper {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        String test_brand_id = "4";
        String display_string = "Enter number of tiers \n";
        System.out.println(display_string);
        String uniqId;
        Integer number_of_tiers = scanner.nextInt();
        Integer input;
        List<Tier> tier = new ArrayList<>(number_of_tiers);
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
        int i;

        System.out.println("NUMBER OF TIERS: "+ number_of_tiers);
        //Get names of each tier
        String name_of_tier = "";
        for (i = 0; i < number_of_tiers; i++) {      //Low to High
            display_string = "Enter name of  of tier:" + i + 1 + " \n";
            System.out.println(display_string);
            name_of_tier = scanner.next();
            Tier t = new Tier();
            uniqId = UUID.randomUUID().toString().replace("-","");
            t.setId(uniqId);
            t.setLp_program_id(loyaltyProgram.getLpId());
            t.setName(name_of_tier);
            tier.add(t);
        }

        //Get points required for each tier
        Integer pointsRequired;
        for (i = 0; i < number_of_tiers; i++) {      //Low to High
            display_string = "Enter points required for tier:" + i + 1 + " \n";
            System.out.println(display_string);
            pointsRequired = scanner.nextInt();
            tier.get(i).setPoints(pointsRequired);
        }

        //Get multiplier for each tier
        Integer multiplier;
        for (i = 0; i < number_of_tiers; i++) {      //Low to High
            display_string = "Enter multiplier for tier:" + i + 1 + " \n";
            System.out.println(display_string);
            multiplier = scanner.nextInt();
            tier.get(i).setMultiplier(multiplier);
        }

        display_string = "Choose an option from below: \n 1)Setup\n2)Go Back";
        System.out.println(display_string);
        input = scanner.nextInt();

        //Save all the tiers created for this TieredLP
        switch (input) {
            case 1:
                for (i = 0; i < number_of_tiers; i++) {
                    TierDAO.saveData(tier.get(i));
                }
                TieredLoyaltyProgramHelper.display();
                break;
            case 2:
                TieredLoyaltyProgramHelper.display();
                break;
        }
    }
}
