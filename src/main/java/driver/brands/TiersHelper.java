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
    public static void display(String brand_id) {
        Scanner scanner = new Scanner(System.in);
        String display_string = "Enter number of tiers (Max 3 allowed)\n";
        System.out.println(display_string);
        String uniqId;
        Integer number_of_tiers = scanner.nextInt();
        scanner.nextLine();
        if(number_of_tiers < 0 || number_of_tiers > 3){
            System.out.println("Incorrect number of tiers entered. Retry");
            TiersHelper.display(brand_id);
        }
        else {
            Integer input;
            List<Tier> tier = new ArrayList<>(number_of_tiers);
            LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
            int i;

            System.out.println("NUMBER OF TIERS: " + number_of_tiers);
            //Get names of each tier
            String name_of_tier = "";
            for (i = 0; i < number_of_tiers; i++) {      //Low to High
                display_string = "Enter name of  of tier:" + i + 1 + " \n";
                System.out.println(display_string);
                name_of_tier = scanner.next();
                Tier t = new Tier();
                uniqId = UUID.randomUUID().toString().replace("-", "");
                t.setId(uniqId);
                t.setLp_program_id(loyaltyProgram.getLpId());
                t.setName(name_of_tier);
                tier.add(t);
            }

            //Get points required for each tier
            Integer pointsRequired;
            for (i = 0; i < number_of_tiers; i++) {      //Low to High
                if (i == 0) {
                    System.out.println("Base tier points set to 0 by default\n");
                    tier.get(i).setPoints(0);
                    continue;
                }
                display_string = "Enter points required for tier:" + i + 1 + " \n";
                System.out.println(display_string);
                pointsRequired = scanner.nextInt();
                scanner.nextLine();
                tier.get(i).setPoints(pointsRequired);
            }

            //Get multiplier for each tier
            Integer multiplier;
            for (i = 0; i < number_of_tiers; i++) {      //Low to High
                if (i == 0) {
                    System.out.println("Base multiplier set to 1 by default\n");
                    tier.get(i).setMultiplier(1);
                    continue;
                }
                display_string = "Enter multiplier for tier:" + i + 1 + " \n";
                System.out.println(display_string);
                multiplier = scanner.nextInt();
                scanner.nextLine();
                tier.get(i).setMultiplier(multiplier);
            }

            display_string = "Choose an option from below: \n 1)Setup\n2)Go Back";
            System.out.println(display_string);
            input = scanner.nextInt();
            scanner.nextLine();

            //Save all the tiers created for this TieredLP
            switch (input) {
                case 1:
                    for (i = 0; i < number_of_tiers; i++) {
                        TierDAO.saveData(tier.get(i));
                    }
                    TieredLoyaltyProgramHelper.display(brand_id);
                    break;
                case 2:
                    TieredLoyaltyProgramHelper.display(brand_id);
                    break;
            }
        }
    }
}
