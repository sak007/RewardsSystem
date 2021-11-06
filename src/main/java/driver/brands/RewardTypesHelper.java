package driver.brands;

import driver.dao.*;
import driver.object.*;
import driver.object.RewardInstance;

import java.sql.Date;
import java.util.Scanner;

public class RewardTypesHelper {
    public static void display(){
        Scanner scanner = new Scanner(System.in);

        String display_string = "Enter Quantity for the chosen option:";
        System.out.println(display_string);
        Integer quantity = scanner.nextInt();

        display_string = "Choose from one of the options below:\n" + "1)Gift Card\n" +
                "2)Free Product\n" + "3) Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        String test_brand_id = "2";
        int iterator;
        RewardInstance reward_instance = new RewardInstance();
        // CREATE INSTANCES OF THE CHOSEN REWARD TYPE
        switch (input){
            case 1:
                //Find the reward category obj for with Gift Card as name.
                //Find the loyalty program of this brand
                Reward reward_gift = RewardDAO.loadByName("Gift Card");
                System.out.print("Got the reward gift obj: " + reward_gift.getCode()+reward_gift.getName() +" \n");
                LoyaltyProgram loyaltyProgramGift = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
                System.out.print("Got the LP obj: " + loyaltyProgramGift.getProgramName()+" \n");

                //Add it's ID as the code in the mapping table object1
                RewardsForLoyaltyProgram rewardLPGift = new RewardsForLoyaltyProgram();
                rewardLPGift.setReward_category_code(reward_gift.getCode());
                rewardLPGift.setLoyalty_program_code(loyaltyProgramGift.getLpId());
                System.out.println("Mapping chosen reward to the LP\n");
                RewardsForLoyaltyProgramDAO.saveData(rewardLPGift);
                System.out.println("Mapped chosen reward to the LP\n");

                //Create quantity number of instances of this reward for this loyalty Program

                for(iterator = 0; iterator < quantity; iterator++){
                    reward_instance.setReward_id(reward_gift.getCode());
                    reward_instance.setBrand_id(test_brand_id);
//                    reward_instance.setExpiryDate(); Default value of 1 year
                    reward_instance.setValue(quantity);
                    //Save reward instance object
                    RewardInstanceDAO.saveData(reward_instance);
                }

                break;
            case 2:
                //Find the reward category obj for with Gift Card as name.
                //Find the loyalty program of this brand
                Reward reward_free = RewardDAO.loadByName("Free Product");
                LoyaltyProgram loyaltyProgramFree = LoyaltyProgramDAO.loadByBrandId(test_brand_id);

                //Add it's ID as the code in the mapping table object
                RewardsForLoyaltyProgram rewardLPFree = new RewardsForLoyaltyProgram();
                rewardLPFree.setReward_category_code(reward_free.getCode());
                rewardLPFree.setLoyalty_program_code(loyaltyProgramFree.getLpId());

                RewardsForLoyaltyProgramDAO.saveData(rewardLPFree);

                //Create quantity number of instances of this reward for this loyalty Program
                for(iterator = 0; iterator < quantity; iterator++){
                    reward_instance.setReward_id(reward_free.getCode());
                    reward_instance.setBrand_id(test_brand_id);
//                    reward_instance.setExpiryDate(); Default value of 1 year
                    reward_instance.setValue(quantity);
                }
                break;
            case 3:
                // Go Back
                break;
        }
    }
}
