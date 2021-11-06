package driver.brands;

import driver.dao.*;
import driver.object.*;
import driver.object.RewardInstance;

import java.sql.Date;
import java.util.Scanner;
import java.util.UUID;

public class RewardTypesHelper {
    public static void display(String tier_type){
        Scanner scanner = new Scanner(System.in);

        String display_string = "Enter Quantity for the chosen option:";
        System.out.println(display_string);
        Integer quantity = scanner.nextInt();

        display_string = "Choose from one of the options below:\n" + "1)Gift Card\n" +
                "2)Free Product\n" + "3) Go back\n";
        System.out.println(display_string);
        Integer input = scanner.nextInt();
        String test_brand_id = "3";
        String uniqId;
        int iterator;
        RewardInstance reward_instance = new RewardInstance();
        // CREATE INSTANCES OF THE CHOSEN REWARD TYPE
        switch (input){
            case 1:
                //Find the reward category obj for with Gift Card as name.
                //Find the loyalty program of this brand
                String lpGiftCode;
                Reward reward_gift = RewardDAO.loadByName("Gift Card");
                System.out.print("Got the reward gift obj: " + reward_gift.getCode()+reward_gift.getName() +" \n");
                LoyaltyProgram loyaltyProgramGift = LoyaltyProgramDAO.loadByBrandId(test_brand_id);
                System.out.print("Got the LP obj: " + loyaltyProgramGift.getProgramName()+" \n");

                //Add it's ID as the code in the mapping table object1
                RewardsForLoyaltyProgram rewardLPGift = new RewardsForLoyaltyProgram();

                //Create reward LP mapping
                lpGiftCode = UUID.randomUUID().toString().replace("-","");
                rewardLPGift.setReward_lp_map_id(lpGiftCode);
                rewardLPGift.setReward_category_code(reward_gift.getCode());
                rewardLPGift.setLoyalty_program_code(loyaltyProgramGift.getLpId());
                RewardsForLoyaltyProgramDAO.saveData(rewardLPGift);

                //Create quantity number of instances of this reward for this loyalty Program
                for(iterator = 0; iterator < quantity; iterator++){
                    uniqId= UUID.randomUUID().toString().replace("-","");
                    reward_instance.setInstance_id(uniqId);
                    reward_instance.setReward_id(lpGiftCode);
                    reward_instance.setBrand_id(test_brand_id);
//                    reward_instance.setExpiryDate(); Default value of 1 year
                    reward_instance.setValue("100");
                    //Save reward instance object
                    RewardInstanceDAO.saveData(reward_instance);
                }
                RewardTypesHelper.display(tier_type);
                break;
            case 2:
                //Find the reward category obj for with Gift Card as name.
                //Find the loyalty program of this brand
                String FPcode;
                Reward reward_free = RewardDAO.loadByName("Free Product");
                LoyaltyProgram loyaltyProgramFree = LoyaltyProgramDAO.loadByBrandId(test_brand_id);

                //Add it's ID as the code in the mapping table object
                RewardsForLoyaltyProgram rewardLPFree = new RewardsForLoyaltyProgram();
                FPcode = UUID.randomUUID().toString().replace("-","");
                //Create reward LP mapping
                rewardLPFree.setReward_lp_map_id(FPcode);
                rewardLPFree.setReward_category_code(reward_free.getCode());
                rewardLPFree.setLoyalty_program_code(loyaltyProgramFree.getLpId());
                RewardsForLoyaltyProgramDAO.saveData(rewardLPFree);

                //Create quantity number of instances of this reward for this loyalty Program
                for(iterator = 0; iterator < quantity; iterator++){
                    uniqId= UUID.randomUUID().toString().replace("-","");
                    reward_instance.setInstance_id(uniqId);
                    reward_instance.setReward_id(FPcode);
                    reward_instance.setBrand_id(test_brand_id);
//                    reward_instance.setExpiryDate(); Default value of 1 year
                    reward_instance.setValue("Product Name X");
                    //Save reward instance object
                    RewardInstanceDAO.saveData(reward_instance);
                }
                RewardTypesHelper.display(tier_type);
                break;
            case 3:
                // Go Back to tier or regular
                if (tier_type == "Regular"){
                    RegularLoyaltyProgramHelper.display();
                }
                else{
                    TieredLoyaltyProgramHelper.display();
                }
                break;
        }
    }
}
