package driver.brands;

import driver.dao.RERuleDAO;
import driver.object.RERule;

import java.util.Scanner;
import java.util.UUID;

public class ReRulesHelper {

    public static void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operation that you would like to perform:\n1) Add RERule\n2) Go Back\n");
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();

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
                RERuleDAO.saveData(reRule);
                break;
            case 2:
                BrandLandingPage.run();
                break;

        }


    }

    public static void update(String brandId){
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
                boolean updateCheck = RERuleDAO.updateReRule(reRule,brandId);
                if(updateCheck){
                    System.out.println("Update was successful..!!");
                }else{
                    System.out.println("There was an issue while updating the entry.");
                }
                break;
            case 2:
                BrandLandingPage.run();
                break;
        }
    }
}
