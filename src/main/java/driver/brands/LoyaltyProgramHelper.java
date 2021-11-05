package driver.brands;

import driver.dao.LoyaltyProgramDAO;
import driver.object.Brand;
import driver.object.LoyaltyProgram;

import java.util.Scanner;

public class LoyaltyProgramHelper {
    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the Loyalty Program\n");
        String input_lp_name = scanner.nextLine();
        String display_string = "Choose the type of the Loyalty Program\n 1) Regular\n" + "2) Tier\n" + "Go Back\n";
        System.out.println(display_string);
        String test_brand_id = "1";
        Integer input_lp_type = scanner.nextInt();
        switch (input_lp_type){
            case 1:
                //Regular - Go to Brand Regular Page

                //Set loyaltyProgram values
                LoyaltyProgram loyaltyProgramreg = new LoyaltyProgram();
                loyaltyProgramreg.setProgramName(input_lp_name);
                loyaltyProgramreg.setBrandId(test_brand_id);
                loyaltyProgramreg.setTierType("Regular");
                loyaltyProgramreg.setState("INACTIVE");

                //Insert the Loyalty Program with basic values
                LoyaltyProgramDAO.saveData(loyaltyProgramreg);

                RegularLoyaltyProgramHelper.display();
                break;
            case 2:
                //Tiered - Go to Brand Tier Page

                LoyaltyProgram loyaltyProgramtier = new LoyaltyProgram();
                loyaltyProgramtier.setProgramName(input_lp_name);
                loyaltyProgramtier.setBrandId(test_brand_id);
                loyaltyProgramtier.setTierType("Tier");
                loyaltyProgramtier.setState("INACTIVE");

                //Insert the Loyalty Program with basic values
                LoyaltyProgramDAO.saveData(loyaltyProgramtier);

                TieredLoyaltyProgramHelper.display();
                break;
        }
    }

    public static void validate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
    }
}
