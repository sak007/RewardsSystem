package driver.brands;

import driver.dao.*;
import driver.object.*;
import driver.object.RewardInstance;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RewardTypesHelper {
    public static void display(String tier_type, String brand_id){
        Scanner scanner = new Scanner(System.in);
        String display_string = "";

        //Choose a Reward from the available list
        List<Reward> rewards = RewardDAO.getList();
        int display_i = 0;
        int reward_index = 0;

        display_string = "Choose from one of the options below:\n";
        for(Reward rew:rewards){
            display_string = display_string + (display_i + 1) + ") " + rew.getName() + "\n";
            display_i++;
        }
        display_string = display_string + (display_i + 1) + ") Go Back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        scanner.nextLine();


        String lpGiftCode;

        //Go back
        if(input == display_i + 1) {
            if (tier_type == "Regular") {
                RegularLoyaltyProgramHelper.display(brand_id);
            } else {
                TieredLoyaltyProgramHelper.display(brand_id);
            }
        }
        else{
            display_string = "Enter Quantity for the reward:";
            System.out.println(display_string);
            Integer quantity = scanner.nextInt();
            scanner.nextLine();

            //Change: IF it is not Go back then ask this
            display_string = "Enter value for the chosen reward option\n";
            System.out.println(display_string);
            String reward_value = scanner.next();

            reward_index = input - 1;
            Reward reward = rewards.get(reward_index);
            LoyaltyProgram loyaltyProgram = LoyaltyProgramDAO.loadByBrandId(brand_id);
            if (loyaltyProgram == null){
                // Ideally should never come here
                System.out.println("Loyalty Program Not found!");
            }

            RewardsForLoyaltyProgram rewardLPGift = new RewardsForLoyaltyProgram();
            lpGiftCode = UUID.randomUUID().toString().replace("-","");

            rewardLPGift.setReward_lp_map_id(lpGiftCode);
            rewardLPGift.setReward_category_code(reward.getCode());
            rewardLPGift.setReward_count(quantity);
            rewardLPGift.setReward_value(reward_value);
            rewardLPGift.setLoyalty_program_code(loyaltyProgram.getLpId());

            RewardsForLoyaltyProgramDAO.saveData(rewardLPGift);

            RewardTypesHelper.display(tier_type, brand_id);
        }
    }
}
