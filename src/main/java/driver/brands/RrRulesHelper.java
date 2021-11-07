package driver.brands;

import driver.dao.RRRuleDAO;
import driver.object.RRRule;

import java.util.Scanner;
import java.util.UUID;

public class RrRulesHelper {
//    public static void display(){
//
//    }

    public static void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operation that you would like to perform:\n1) Add RRRule\n2) Go Back\n");
        Integer selected_option = scanner.nextInt();
        scanner.nextLine();
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
                System.out.println("Enter the count of instances that you would like to provide for this reward:\n");
                Integer instances = scanner.nextInt();
                scanner.nextLine();
                rrRule.setInstances(instances);
                RRRuleDAO.saveData(rrRule);
                break;
            case 2:
                BrandLandingPage.run();
                break;

        }
    }

    public static void update (String brandId) {
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
                System.out.println("Enter the updated count of the instances:");
                Integer instances = scanner.nextInt();
                scanner.nextLine();
                rrRule.setInstances(instances);
                RRRuleDAO.updateRRRule(rrRule,brandId);
                System.out.println("Update was successful..!!");
                /*if(updateCheck){
                    System.out.println("Update was successful..!!");
                }else{
                    System.out.println("There was an issue while updating the entry.");
                }*/
                break;
            case 2:
                BrandLandingPage.run();
                break;
        }

    }
}
