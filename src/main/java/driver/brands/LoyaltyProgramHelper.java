package driver.brands;

import driver.dao.LoyaltyProgramDAO;
import driver.object.Brand;
import driver.object.LoyaltyProgram;

import java.util.Scanner;
import java.util.UUID;

public class LoyaltyProgramHelper {
    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the Loyalty Program\n");
        String input_lp_name = scanner.nextLine();
        String display_string = "Choose the type of the Loyalty Program\n 1) Regular\n" + "2) Tier\n" + "Go Back\n";
        System.out.println(display_string);
        String test_brand_id = "3";
        Integer input_lp_type = scanner.nextInt();
        String uniqId;
        switch (input_lp_type){
            case 1:
                //Regular - Go to Brand Regular Page

                //Set loyaltyProgram values
                LoyaltyProgram loyaltyProgramreg = new LoyaltyProgram();
                uniqId = UUID.randomUUID().toString().replace("-","");
                loyaltyProgramreg.setLpId(uniqId);
                loyaltyProgramreg.setProgramName(input_lp_name);
                loyaltyProgramreg.setBrandId(test_brand_id);
                loyaltyProgramreg.setTierType("Regular");
                loyaltyProgramreg.setState("INACTIVE");

                //Find the ReRuleCode and RrRuleCod
//                loyaltyProgramreg.setReRuleCode();
//                loyaltyProgramreg.setRrRuleCode();

                //Insert the Loyalty Program with basic values
                LoyaltyProgramDAO.saveData(loyaltyProgramreg); // Re RR Rules still not added.

                RegularLoyaltyProgramHelper.display();
                break;
            case 2:
                //Tiered - Go to Brand Tier Page
                uniqId = UUID.randomUUID().toString().replace("-","");
                LoyaltyProgram loyaltyProgramtier = new LoyaltyProgram();
                loyaltyProgramtier.setLpId(uniqId);
                loyaltyProgramtier.setProgramName(input_lp_name);
                loyaltyProgramtier.setBrandId(test_brand_id);
                loyaltyProgramtier.setTierType("Tier");
                loyaltyProgramtier.setState("INACTIVE");

                //Insert the Loyalty Program with basic values
                LoyaltyProgramDAO.saveData(loyaltyProgramtier);
                System.out.println("Loyalty Program inserted");
                TieredLoyaltyProgramHelper.display();
                break;
            case 3:
                // Go Back
                BrandLandingPage.run();
        }
    }

    public static void validate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
    }
}
