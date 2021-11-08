package driver.brands;

import driver.dao.DBHelper;
import driver.dao.LoyaltyProgramDAO;
import driver.dao.RRRuleDAO;
import driver.object.LoyaltyProgram;
import driver.object.RRRule;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class RrRulesHelper {
//    public static void display(){
//
//    }

    public static void add(String brand_id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operation that you would like to perform:\n1) Add RRRule\n2) Go Back\n");
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();
        String mapping_query;
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
        switch (selected_option) {
            case 1:
                RRRule rrRule = new RRRule();
                rrRule.setRrRuleCode(UUID.randomUUID().toString().replace("-",""));
                rrRule.setVersion(1);
                rrRule.setStatus("E");
                System.out.println("Please enter the reward Name: \n");
                String rewardName = scanner.nextLine();
                rrRule.setReward(rewardName);
                System.out.println("Enter the points for this reward\n");
                Integer points = scanner.nextInt();
                scanner.nextLine();
                rrRule.setNumPoints(points);
                RRRuleDAO.saveData(rrRule);

//                try {
//                    mapping_query = "insert into rr_rule_for_lp values(" + "'"+loyaltyProgram.getLpId()+"'"+ "," + "'"+rrRule.getRrRuleCode()+"'"+")";
//                    System.out.print(mapping_query);
//                    DBHelper.executeUpdate(mapping_query);
//                    System.out.println("New RrRule mapped successfully to the Loyalty Program");
//                } catch (SQLException e) {
//                    System.out.println("Unable to map the created RrRule with Loyalty Program!");
//                    System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
//                }
//
//                RrRulesHelper.add(brand_id);
                //Add it in the mapping

                try {
                    mapping_query = "insert into rr_rule_for_lp values(" + "'"+loyaltyProgram.getLpId()+"'"+ "," + "'"+rrRule.getRrRuleCode()+"'"+")";

                    System.out.print(mapping_query);
                    DBHelper.executeUpdate(mapping_query);
                    System.out.println("New RrRule mapped successfully to the Loyalty Program");
                } catch (SQLException e) {

                    try {
                        mapping_query = "delete from rr_rules where rr_rule_code = '" + rrRule.getRrRuleCode() + "'";
                        DBHelper.executeUpdate(mapping_query);
                        System.out.println("Unable to map the created RrRule with Loyalty Program! Retry");
                        System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
                        RrRulesHelper.add(brand_id);
                    }
                    catch(SQLException e2){
                        System.out.println("Unable to remove rr_rule after the failing to map the rr_rule to Loyalty Program. Retry\n");
                        System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
                        RrRulesHelper.add(brand_id);
                    }
                }

                break;
            case 2:
                BrandLandingPage.run(brand_id);
                break;

        }
    }

    public static void update (String brand_id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operation that you would like to perform:\n1) Update RRRule\n2) Go Back\n");
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();
        switch (selected_option) {
            case 1:
                RRRule rrRule = new RRRule();
                /*System.out.println("Enter the rule code which you want to update:\n");
                String rrRuleCode = scanner.nextLine();
                rrRule.setRrRuleCode(rrRuleCode);*/
                System.out.println("Enter the name of the reward that needs to be updated: ");
                String rewardName = scanner.nextLine();
                rrRule.setReward(rewardName);
                System.out.println("Enter the points that you want to replace with the current points:");
                Integer points = scanner.nextInt();
                scanner.nextLine();
                rrRule.setNumPoints(points);
                RRRuleDAO.updateRRRule(rrRule,brand_id);
                System.out.println("Update was successful..!!");
                /*if(updateCheck){
                    System.out.println("Update was successful..!!");
                }else{
                    System.out.println("There was an issue while updating the entry.");
                }*/
                RrRulesHelper.update(brand_id);
                break;
            case 2:
                BrandLandingPage.run(brand_id);
                break;
        }

    }
}
