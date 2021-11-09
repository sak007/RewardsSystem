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

                System.out.println("Enter an unique rule code for Re Rule");
                String re_rule_code = scanner.nextLine();
                reRule.setReRuleCode(re_rule_code);

                //Change: Verify if the activity is mapped?
                System.out.println("Please enter the Activity Category Code:");
                String activityCategoryCode = scanner.nextLine();
                reRule.setActivityCategoryCode(activityCategoryCode);

                System.out.println("Enter the points that you would want to assign for this activity:");
                Integer activityPoints = scanner.nextInt();
                reRule.setNumPoints(activityPoints);

                reRule.setVersion(1);

                reRule.setStatus("E");

                reRule.setLpCode(loyaltyProgram.getLpId());

                RERuleDAO.saveData(reRule);

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
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();
        switch(selected_option){
            case 1:
                RERule reRule = new RERule();

                System.out.println("Enter the Brand RE rule code");
                String RrrCode = scanner.nextLine();
                reRule.setReRuleCode(RrrCode);

                System.out.println("Enter the Activity Category Code for the Rule you want to update: ");
                String activityCategoryCode = scanner.nextLine();
                reRule.setActivityCategoryCode(activityCategoryCode);


                System.out.println("Enter the points that you want to replace the existing points with:");
                Integer points = scanner.nextInt();
                reRule.setNumPoints(points);

                //Find using re_rule_code and status = 'E' and disable it
                RERuleDAO.updateReRule(reRule,loyaltyProgram.getLpId());
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
