package driver.brands;

import driver.dao.DBHelper;
import driver.dao.LoyaltyProgramDAO;
import driver.dao.RERuleDAO;
import driver.dao.RRRuleDAO;
import driver.object.Activity;
import driver.object.LoyaltyProgram;
import driver.object.RRRule;
import driver.object.Reward;

import java.sql.SQLException;
import java.util.List;
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

                System.out.println("Please enter the reward rule code: \n");
                String rrcode = scanner.nextLine();
                rrRule.setRrRuleCode(rrcode);

                rrRule.setVersion(1);
                rrRule.setStatus("E");
                rrRule.setLpCode(loyaltyProgram.getLpId());

                String display_string = "Please choose from one of the mapped rewards:\n";
                int display_i = 0;
                List<Reward> rewards = RRRuleDAO.getMappedRewards(loyaltyProgram.getLpId());
                for(Reward rew:rewards){
                    display_string = display_string + (display_i + 1) + ") " + rew.getName() + "\n";
                    display_i++;
                }
                System.out.println(display_string);
                int rewardCategoryOption = scanner.nextInt();
                scanner.nextLine();
                Reward chosenReward = rewards.get(rewardCategoryOption - 1);
                rrRule.setReward(chosenReward.getCode());

                System.out.println("Enter the points for this reward\n");
                Integer points = scanner.nextInt();
                scanner.nextLine();
                rrRule.setNumPoints(points);

                RRRuleDAO.saveData(rrRule);

                RrRulesHelper.add(brand_id);
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
        LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
        scanner.nextLine();
        switch (selected_option) {
            case 1:
                RRRule rrRule = new RRRule();

                System.out.println("Enter the Brand RR rule code");
                String RrrCode = scanner.nextLine();
                rrRule.setRrRuleCode(RrrCode);
                rrRule.setLpCode(loyaltyProgram.getLpId());


                String display_string = "Please choose from one of the mapped rewards:\n";
                int display_i = 0;
                List<Reward> rewards = RRRuleDAO.getMappedRewards(loyaltyProgram.getLpId());
                for(Reward rew:rewards){
                    display_string = display_string + (display_i + 1) + ") " + rew.getName() + "\n";
                    display_i++;
                }
                System.out.println(display_string);
                int rewardCategoryOption = scanner.nextInt();
                scanner.nextLine();
                Reward chosenReward = rewards.get(rewardCategoryOption - 1);
                rrRule.setReward(chosenReward.getCode());


                System.out.println("Enter the points that you want to replace with the current points:");
                Integer points = scanner.nextInt();
                scanner.nextLine();
                rrRule.setNumPoints(points);

                RRRuleDAO.updateRRRule(rrRule,loyaltyProgram.getLpId());

                System.out.println("Update was successful..!!");
                RrRulesHelper.update(brand_id);
                break;
            case 2:
                BrandLandingPage.run(brand_id);
                break;
        }

    }
}
