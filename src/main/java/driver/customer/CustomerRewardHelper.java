package driver.customer;

import driver.MainMenu;
import driver.admin.CustomerHelper;
import driver.dao.RewardDAO;
import driver.dao.WalletDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRewardHelper {
    public static void run(String customerId) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> rewards= RewardDAO.fetchApplicableRewards(customerId);
        int count=1;
        for(List<String> reward:rewards) {
            System.out.println(count + " " + reward.get(0) + "  " + reward.get(1));
            count++;
        }
        System.out.println("1. Redeem Rewards\n2. Go Back");
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Please Enter the Redeem Reward");
                int ans=scanner.nextInt();
                count=1;String rewardName="";String lpProgramName="";
                for(List<String> reward:rewards) {
                    if(count==ans){
                        rewardName=reward.get(0);
                        lpProgramName=reward.get(1);
                        break;
                    }
                    count++;
                }
                int result=redeemReward(customerId,rewardName,lpProgramName);
                if(result>0) {
                    System.out.println("Redeem activity saved successfully!");
                }
                else
                    System.out.println("Unable to save redeem activity!");
                break;
            case 2:
                CustomerLandingPage.run(customerId);
                break;

        }
    }
    public static int redeemReward(String customerId,String rewardName,String lpProgramName){
        int availablePoints=RewardDAO.fetchAvailablePoints(customerId,lpProgramName);
        List<Integer> list=RewardDAO.fetchActualRewardDetails(customerId,rewardName,lpProgramName);
        if(list.get(0)>=1 && list.get(1)<=availablePoints)
            return RewardDAO.insertRedeemActivity(customerId, rewardName, lpProgramName, list.get(1));
        else
            System.out.println("Customer does not have enough points in wallet to redeem");
        return -1;
    }

}
