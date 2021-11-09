package driver.brands;

import driver.dao.DBHelper;
import driver.dao.LoyaltyProgramDAO;
import driver.object.Brand;
import driver.object.LoyaltyProgram;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LoyaltyProgramHelper {
    public static void add(String brand_id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the Loyalty Program\n");
        String input_lp_name = scanner.nextLine();
        String display_string = "Choose the type of the Loyalty Program\n 1) Regular\n" + "2) Tier\n" + "3) Go Back\n";
        System.out.println(display_string);
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
                loyaltyProgramreg.setBrandId(brand_id);
                loyaltyProgramreg.setTierType("Regular");
                loyaltyProgramreg.setState("INACTIVE");

                LoyaltyProgramDAO.saveData(loyaltyProgramreg); // Re RR Rules still not added.

                RegularLoyaltyProgramHelper.display(brand_id);
                break;
            case 2:
                //Tiered - Go to Brand Tier Page
                uniqId = UUID.randomUUID().toString().replace("-","");
                LoyaltyProgram loyaltyProgramtier = new LoyaltyProgram();
                loyaltyProgramtier.setLpId(uniqId);
                loyaltyProgramtier.setProgramName(input_lp_name);
                loyaltyProgramtier.setBrandId(brand_id);
                loyaltyProgramtier.setTierType("Tier");
                loyaltyProgramtier.setState("INACTIVE");

                //Insert the Loyalty Program with basic values
                LoyaltyProgramDAO.saveData(loyaltyProgramtier);
                System.out.println("Loyalty Program inserted");
                TieredLoyaltyProgramHelper.display(brand_id);
                break;
            case 3:
                // Go Back
                BrandLandingPage.run(brand_id);
        }
    }

    public static void validate(String brand_id){
        String display_string = "Choose one option from below:\n1) Validate\n2) Go back\n";
        Scanner scanner = new Scanner(System.in);
        Integer re_rule_count = 0, rr_rule_count = 0, tier_count = 0, valid = 1;
        System.out.println();
        String query;
        String error_string = "";
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
        // Check for atleast 1 re rule and rr rule
        try {
            query = "";

            query = "select count(*) from re_rule where lp_code = '" + loyaltyProgram.getLpId() + "' and status = 'E' ";
            List<Object[]> rs_re = DBHelper.executeQueryUpdated(query);
            re_rule_count = ((BigDecimal)rs_re.get(0)[0]).intValueExact();
            System.out.println(query);

            query = "select count(*) from rr_rule where lp_code = '" + loyaltyProgram.getLpId() + "' and status = 'E' ";
            System.out.println(query);
            List<Object[]> rs_rr = DBHelper.executeQueryUpdated(query);
            rr_rule_count = ((BigDecimal)rs_rr.get(0)[0]).intValueExact();

            if (re_rule_count == 0) {
                error_string = error_string + "Loyalty Program does not have atleast one re_rule\n";
                valid = 0;
            }
            if (rr_rule_count == 0) {
                error_string = error_string + "Loyalty Program does not have atleast one rr_rule\n";
                valid = 0;
            }

            if (loyaltyProgram.getTierType() == "Tier") {
                query = "select count(*) from tier where lp_program_id = '" + loyaltyProgram.getLpId() + "'";
                System.out.println(query);
                List<Object[]> rs_tier = DBHelper.executeQueryUpdated(query);
                tier_count = (Integer)rs_tier.get(0)[0];
                if(tier_count == 0){
                    error_string = error_string + "No tiers assigned for this Loyalty Program";
                    valid = 0;
                }
            }

            if(valid == 1){
                //Update loyalty_program state to ACTIVE
                query = "Update loyalty_program SET state ='ACTIVE' where id = '" + loyaltyProgram.getLpId() +"'";
                DBHelper.executeUpdate(query);
                System.out.println("Validation SUCCESS. Loyalty Program is set to ACTIVE");
            }
            else{
                query = "Update loyalty_program SET state ='ACTIVE' where id = '" + loyaltyProgram.getLpId() +"'";
                DBHelper.executeUpdate(query);
                System.out.println("Validation failed. Loyalty Program is set to INACTIVE. Errors:\n");
                System.out.println(error_string);
            }
        }
        catch (SQLException e){
            System.out.println("Error while validating Loyalty Program. Retry\n");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            LoyaltyProgramHelper.validate(brand_id);
        }
    }
}
