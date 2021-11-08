package driver.dao;

import driver.object.ActivitiesForLoyaltyProgram;
import driver.object.RewardsForLoyaltyProgram;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RewardsForLoyaltyProgramDAO {
    public static void saveData(RewardsForLoyaltyProgram rewardLp){
        try {
            String query = "Insert into rewards_for_loyalty_program" + rewardLp.getMeta() + " values" + rewardLp.toString();
            System.out.println(query);
            DBHelper.executeUpdate(query);
            System.out.println("The chosen reward has been mapped to the Loyalty Program!");
        } catch (SQLException e) {
            System.out.println("Unable to map reward to the Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
        }
    }

    public static List<RewardsForLoyaltyProgram> loadByLpIdAndRewardCategory(String lpId, String reward) {
        try {
            String loadCategoryCode = "(Select id from reward_category where reward_name='" + reward + "')";
            String query = "select * from rewards_for_loyalty_program where loyalty_program_code='" + lpId
                    + "' and reward_category_code=" + loadCategoryCode;
            List<Object[]> objectList = DBHelper.executeQueryUpdated(query);
            List<RewardsForLoyaltyProgram> rewardsForLoyaltyProgramList = new ArrayList<>();
            objectList.forEach(obj -> {
                RewardsForLoyaltyProgram rewardsForLoyaltyProgram = new RewardsForLoyaltyProgram();
                rewardsForLoyaltyProgram.setReward_lp_map_id((String)obj[0]);
                rewardsForLoyaltyProgram.setLoyalty_program_code((String)obj[1]);
                rewardsForLoyaltyProgram.setReward_category_code((String)obj[2]);
                rewardsForLoyaltyProgram.setReward_count((Integer)obj[3]);
                rewardsForLoyaltyProgram.setReward_value((String)obj[4]);
                rewardsForLoyaltyProgramList.add(rewardsForLoyaltyProgram);
            });
            return rewardsForLoyaltyProgramList;
        } catch (SQLException e) {
            System.out.println("Unable to map reward to the Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }

    public static RewardsForLoyaltyProgram loadById(String id) {
        try {
            String query = "select * from rewards_for_loyalty_program where reward_lp_map_id='" + id + "'";
            Object[] obj = DBHelper.executeQueryUpdated(query).get(0);

            RewardsForLoyaltyProgram rewardsForLoyaltyProgram = new RewardsForLoyaltyProgram();
            rewardsForLoyaltyProgram.setReward_lp_map_id((String)obj[0]);
            rewardsForLoyaltyProgram.setLoyalty_program_code((String)obj[1]);
            rewardsForLoyaltyProgram.setReward_category_code((String)obj[2]);
            rewardsForLoyaltyProgram.setReward_count((Integer)obj[3]);
            rewardsForLoyaltyProgram.setReward_value((String)obj[4]);

            return rewardsForLoyaltyProgram;
        } catch (SQLException e) {
            System.out.println("Unable to map reward to the Loyalty Program!");
            System.out.println("Caught SQLException " + e.getErrorCode() + "/" + e.getSQLState() + " " + e.getMessage());
            return null;
        }
    }
}
