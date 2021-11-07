package driver.brands;

import driver.dao.DBHelper;
import driver.dao.LoyaltyProgramDAO;
import driver.dao.RERuleDAO;
import driver.object.LoyaltyProgram;
import driver.object.RERule;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class ReRulesHelper {

    public static void add(String brand_id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operation that you would like to perform:\n1) Add RERule\n2) Go Back\n");
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();
        String mapping_query;
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
        switch(selected_option){
            case 1:
                RERule reRule = new RERule();
                reRule.setReRuleCode(UUID.randomUUID().toString().replace("-",""));
                System.out.println("Please enter the Activity Category Code:");
                String activityCategoryCode = scanner.nextLine();
                reRule.setActivityCategoryCode(activityCategoryCode);
                System.out.println("Enter the points that you would want to assign for this activity:");
                Integer activityPoints = scanner.nextInt();
                reRule.setNumPoints(activityPoints);
                reRule.setVersion(1);
                reRule.setStatus("E");
                RERuleDAO.saveData(reRule);

                //Add it in the mapping
                try {
                    mapping_query = "insert into re_rule_for_lp values(" + "'"+loyaltyProgram.getLpId()+"'"+ "," + "'"+reRule.getReRuleCode()+"'"+")";

                    System.out.print(mapping_query);
                    DBHelper.executeUpdate(mapping_query);
                    System.out.println("New ReRule mapped successfully to the Loyalty Program");
                } catch (SQLException e) {
//                  mapping_query = "delete from re_rules where re_rule_code = " +
                    System.out.println("Unable to map the created ReRule with Loyalty Program!");
                    System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
                }

                ReRulesHelper.add(brand_id);
                break;
            case 2:
                BrandLandingPage.run(brand_id);
                break;

        }

    }

    public static void update(String brand_id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operation that you would like to perform:\n1) Update RERule\n2) Go Back\n");
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();
        switch(selected_option){
            case 1:
                RERule reRule = new RERule();
                System.out.println("Enter the Activity Category Code for the Rule you want to update: ");
                String activityCategoryCode = scanner.nextLine();
                reRule.setActivityCategoryCode(activityCategoryCode);
                System.out.println("Enter the points that you want to replace the existing points with:");
                Integer points = scanner.nextInt();
                reRule.setNumPoints(points);
                RERuleDAO.updateReRule(reRule,brand_id);
                /*if(updateCheck){
                    System.out.println("Update was successful..!!");
                }else{
                    System.out.println("There was an issue while updating the entry.");
                }*/
                System.out.println("Update was successful..!!");
                ReRulesHelper.update(brand_id);
                break;
            case 2:
                BrandLandingPage.run(brand_id);
                break;
        }
    }
}
