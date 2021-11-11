package driver.brands;

import driver.dao.DBHelper;
import driver.dao.LoyaltyProgramDAO;
import driver.dao.RERuleDAO;
import driver.object.Activity;
import driver.object.LoyaltyProgram;
import driver.object.RERule;

import java.sql.SQLException;
import java.util.List;
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
                String display_string = "Please choose from one of the mapped activities:\n";
                int display_i = 0;
                List<Activity> activities = RERuleDAO.getMappedActivities(loyaltyProgram.getLpId());
                for(Activity act:activities){
                    display_string = display_string + (display_i + 1) + ") " + act.getName() + "\n";
                    display_i++;
                }
                System.out.println(display_string);
                int activityCategoryOption = scanner.nextInt();
                scanner.nextLine();
                Activity chosenActivity = activities.get(activityCategoryOption - 1);
                reRule.setActivityCategoryCode(chosenActivity.getCode());

                System.out.println("Enter the points that you would want to assign for this activity:");
                Integer activityPoints = scanner.nextInt();
                scanner.nextLine();
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

                System.out.println("Enter an unique rule code for Re Rule");
                String RrrCode = scanner.nextLine();
                reRule.setReRuleCode(RrrCode);

                String display_string = "Please choose from one of the mapped activities:\n";
                int display_i = 0;
                List<Activity> activities = RERuleDAO.getMappedActivities(loyaltyProgram.getLpId());
                for(Activity act:activities){
                    display_string = display_string + (display_i + 1) + ") " + act.getName() + "\n";
                    display_i++;
                }
                System.out.println(display_string);
                int activityCategoryOption = scanner.nextInt();
                scanner.nextLine();
                Activity chosenActivity = activities.get(activityCategoryOption - 1);
                reRule.setActivityCategoryCode(chosenActivity.getCode());


                System.out.println("Enter the points that you want to replace the existing points with:");
                Integer points = scanner.nextInt();
                scanner.nextLine();
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
