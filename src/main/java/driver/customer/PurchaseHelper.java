package driver.customer;

import driver.dao.CustomerActivityDAO;
import driver.dao.RewardsForLoyaltyProgramDAO;
import driver.object.CustomerActivity;
import driver.object.RewardsForLoyaltyProgram;

import java.util.List;
import java.util.Scanner;

public class PurchaseHelper {
    static Scanner scanner = new Scanner(System.in);

    public static void run(CustomerActivity customerActivity, String lpId) {
        List<RewardsForLoyaltyProgram> rewardsForLoyaltyProgramList = RewardsForLoyaltyProgramDAO.loadByLpIdAndRewardCategory(lpId, "Gift Card");
        if (!rewardsForLoyaltyProgramList.isEmpty()) {
            System.out.println("Select Gift Cards to use");
            for (int i=1; i<=rewardsForLoyaltyProgramList.size(); i++) {
                RewardsForLoyaltyProgram r = rewardsForLoyaltyProgramList.get(i);
                System.out.println(i + ". Gift Card " + i + ": " + r.getReward_value());
            }
            System.out.println(rewardsForLoyaltyProgramList.size() + 1 + ". None");
            Integer option = scanner.nextInt();
            if (option <= rewardsForLoyaltyProgramList.size()) {
                RewardsForLoyaltyProgram selectedReward = rewardsForLoyaltyProgramList.get(option - 1);
                customerActivity.setRewardLpId(selectedReward.getReward_lp_map_id());
                customerActivity.setPoints(Long.parseLong(selectedReward.getReward_value()));
            }
        }
        CustomerActivityDAO.saveData(customerActivity);
    }
}
