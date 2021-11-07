package driver.brands;

import driver.dao.DBHelper;
import driver.dao.LoyaltyProgramDAO;
import driver.object.Brand;
import driver.object.LoyaltyProgram;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LoyaltyProgramHelper {
    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the Loyalty Program\n");
        String input_lp_name = scanner.nextLine();
        String display_string = "Choose the type of the Loyalty Program\n 1) Regular\n" + "2) Tier\n" + "3) Go Back\n";
        System.out.println(display_string);
        String test_brand_id = "4";
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
        String display_string = "Choose one option from below:\n1) Validate\n2) Go back\n";
        Scanner scanner = new Scanner(System.in);
        Integer re_rule_count = 0, rr_rule_count = 0, valid = 1;
        System.out.println();
        String test_brand_id = "4";
        String query;
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
        // Check for atleast 1 re rule and rr rule
        try {
            query = "SELECT COUNT(*) from re_rule_for_lp GROUPBY LP_CODE HAVING LP_CODE=" + loyaltyProgram.getLpId() + ";";
            List<Object[]> rs_re = DBHelper.executeQueryUpdated(query);
            re_rule_count = (Integer)rs_re.get(0)[0];
            System.out.println(query);

            query = "SELECT COUNT(*) from rr_rule_for_lp GROUPBY LP_CODE HAVING LP_CODE=" + loyaltyProgram.getLpId() + ";";
            System.out.println(query);
            List<Object[]> rs_rr = DBHelper.executeQueryUpdated(query);
            rr_rule_count = (Integer)rs_rr.get(0)[0];
            if (re_rule_count == 0) {
                valid = 0;
            }
            if (rr_rule_count == 0) {
                valid = 0;
            }

            if (valid == 1 && loyaltyProgram.getTierType() == "Tier") {
                //Check if it has tiers

            }
        }
        catch (SQLException e){
            System.out.println("Unable to load Brand");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }
}
